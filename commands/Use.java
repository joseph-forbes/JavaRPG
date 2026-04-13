package commands;

import util.Formatter;

import gameengine.Engine;
import items.Item;
import player.Inventory;
import util.commandutil.EntityFinder;
import util.returnsutil.EntityFindReturn;

public class Use implements Command {
    public String getMan() {
        return "Use an item. Takes in an item in your inventory.";
    }
    public void execute(Engine game, String[] args) {
        // Find item in player inventory
        EntityFinder finder = new EntityFinder();
        Inventory inventory = game.getPlayer().getInventory();


        EntityFindReturn output = finder.find(args, inventory);

        if(output.entity instanceof Item) {
            Item item = (Item) output.entity;

            item.use();
        } else {
            handleExceptions(game, output);
        }
    }
    private static void handleExceptions(Engine game, EntityFindReturn output) {
        // Possible exceptions: noitemprovided, nosuchitem
        String searchQuery = output.searchStr;
        switch (output.error) {
            case "noitemprovided":
                
            break;
            case "nosuchitem":
                game.render("Could not find a" + Formatter.needsAn(searchQuery) + searchQuery.toLowerCase() + 
                ". Check your spelling."
            );
            break;
            default:
                game.render("An unknown error occured. Please try again.");
                break;
        }
    }
}
