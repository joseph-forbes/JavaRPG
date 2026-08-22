package com.jforbes.javarpg.commands;
import com.jforbes.javarpg.entities.Entity;
import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.util.Formatter;
import com.jforbes.javarpg.worldmap.*;

public class Look extends WorldInteractor {
    

    public Look() {
        man = "Look at an entity. Takes the name of the entity. Type \"look around\" to see everything around you.";
        commandName = "look";
    }

    @Override
    protected void interact(Engine game, Entity entity) {
        // Good behavior
        game.render(entity.getDetailedDescription());
    }
    
    @Override
    protected void handleNoSuchEntity(Engine game, String searchQuery) {
        if(searchQuery.equalsIgnoreCase("self") || searchQuery.equalsIgnoreCase(game.getPlayer().getName())) {
            game.render("   ----------       \n" + 
                        "  --        --      \n" + 
                        " --  .   .   --     \n" + 
                        " --          --     \n" + 
                        " --    u     --     \n" + 
                        "  --        --     This is you!\n" + 
                        "   ----------   You look at yourself.\n" + 
                        "       ||           \n" + 
                        "       ||/          \n" + 
                        "      /||           \n" + 
                        "       ||           \n" + 
                        "       ||           \n" + 
                        "      /  \\           ");
        } else if(searchQuery.equalsIgnoreCase("around")) {
            // "look around"
            Location currentLocation = game.getCurrentLocation();
            game.render(currentLocation.getDescription(game));
            game.render(currentLocation.getValidDirs());
            game.render();
            if(currentLocation.getContents().size() == 0) {
                game.render("There's nothing here...");
            }
            for(Entity e : currentLocation.getContents()) {
                game.render(e.getDescription());
            }
        } else {
            game.render("Could not find a" + 
                        Formatter.needsAn(searchQuery) + searchQuery.toLowerCase() + 
                        ". Check your spelling, or type \"look around\" to see if there is one nearby."
            );
        }
    }
}