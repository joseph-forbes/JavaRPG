package commands;
import gameengine.Engine;
import util.Formatter;
import util.returnsutil.EntityFindReturn;
import worldmap.*;

public class Look extends WorldInteractor {
    public String getMan() {
        return "Look at an entity. Takes the name of the entity. Type \"look around\" to see everything around you.";
    }
    
    WorldMap worldMap;
    String playerName;

    @Override
    protected void interact(Engine game, EntityFindReturn output) {
        playerName = game.getPlayer().getName();
        String searchQuery = output.searchStr;

        if(searchQuery.equalsIgnoreCase(playerName) || searchQuery.equalsIgnoreCase("self")) {
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
        } else {
            System.out.println(output.entity);
        }
    }
    @Override
    protected void handleExceptions(Engine game, EntityFindReturn output) {
         switch (output.error) {
            case "noentityprovided":
                game.render("Please provide a creature.");
            break;
            case "nosuchentity":
                String searchQuery = output.searchStr;
                game.render("Could not find a" + 
                            Formatter.needsAn(searchQuery) + searchQuery + 
                            ". Check your spelling, or type \"look around\" to see if there is one nearby."
                );
            break;
            default:
                game.render("An unknown error occured. Please try again.");
            break;
        }
    }
}