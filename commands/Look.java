package commands;
import entities.Entity;
import gameengine.Engine;
import util.Formatter;
import util.returnsutil.EntityFindReturn;
import worldmap.*;

public class Look extends WorldInteractor {
    

    public Look() {
        man = "Look at an entity. Takes the name of the entity. Type \"look around\" to see everything around you.";
        commandName = "look";
    }

    @Override
    protected void interact(Engine game, EntityFindReturn output) {
        // Good behavior
        System.out.println(output.entity);
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
            Location currentTile = game.getPlayer().getLocation();
            for(Entity e : currentTile.getContents()) {
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