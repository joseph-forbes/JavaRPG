package commands;

import gameengine.Engine;
import util.commandutil.EntityFinder;
import util.returnsutil.EntityFindReturn;

import util.Formatter;

import entities.Entity;
import worldmap.Location;

public class WorldInteractor implements Command {
    protected String man;
    protected EntityFinder finder;
    protected String commandName;
    public String getMan() {
        return man;
    }
    public void execute(Engine game, String[] args) {
        Location currentTile = game.getCurrentLocation();
        EntityFinder finder = new EntityFinder();
        EntityFindReturn output = finder.find(args, currentTile);

        if(output.entity instanceof Entity) {
            interact(game, output.entity);
        } else {
            handleExceptions(game, output);
        }
    }

    protected void interact(Engine game, Entity entity) {

    }

    protected void handleExceptions(Engine game, EntityFindReturn output) {
         // Possible exceptions: noentityprovided, nosuchentity
        String searchQuery = output.searchStr;
         switch (output.error) {
            case "noentityprovided":
                handleNoEntityProvided(game, searchQuery);
            break;
            case "nosuchentity":
                handleNoSuchEntity(game, searchQuery);
            break;
            default:
                handleDefaultError(game, searchQuery);
            break;
        }
    }
    protected void handleNoEntityProvided(Engine game, String searchQuery) {
        game.render("Please provide a creature, i.e. " + commandName + " goblin.");
    }
    protected void handleNoSuchEntity(Engine game, String searchQuery) {
        game.render(
            "Could not find a" + 
            Formatter.needsAn(searchQuery) + (searchQuery.toLowerCase()) + 
            ". Check your spelling and try again."
        );
    }
    protected void handleDefaultError(Engine game, String searchQuery) {
        game.render("An unknown error occured. Please try again.");
    }

}
