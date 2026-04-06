package commands;

import gameengine.Engine;
import util.commandutil.EntityFinder;
import util.returnsutil.EntityFindReturn;

import util.Formatter;

import entities.Entity;
import worldmap.MapTile;

public class WorldInteractor implements Command {
    protected String man;
    protected EntityFinder finder;
    public String getMan() {
        return man;
    }
    public void execute(Engine game, String[] args) {
        MapTile currentTile = game.getMap().getCurrentMapTile();
        EntityFinder finder = new EntityFinder();
        EntityFindReturn output = finder.find(args, currentTile);

        if(output.entity instanceof Entity) {
            interact(game, output);
        } else {
            handleExceptions(game, output);
        }
    }

    protected void interact(Engine game, EntityFindReturn output) {

    }

    protected void handleExceptions(Engine game, EntityFindReturn output) {
         // Possible exceptions: noentityprovided, nosuchentity
        String name = output.searchStr;
         switch (output.error) {
            case "noentityprovided":
                game.render("Please provide a creature.");
            break;
            case "nosuchentity":
                game.render("Could not find a" + Formatter.needsAn(name) + name + 
                ". Check your spelling and try again."
            );
            break;
            default:
                game.render("An unknown error occured. Please try again.");
            break;
        }
    }

}
