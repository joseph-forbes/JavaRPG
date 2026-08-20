package com.jforbes.javarpg.commands;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.items.Item;
import com.jforbes.javarpg.player.Inventory;
import com.jforbes.javarpg.util.Formatter;
import com.jforbes.javarpg.util.commandutil.EntityFinder;
import com.jforbes.javarpg.util.returnsutil.EntityFindReturn;

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

            interact(game, item);
        } else {
            handleExceptions(game, output);
        }
    }

    protected void interact(Engine game, Item item) {
        item.use(game);
    }
    protected static void handleExceptions(Engine game, EntityFindReturn output) {
        // Possible exceptions: noitemprovided, nosuchitem
        String searchQuery = output.searchStr;
        switch (output.error) {
            case "noitemprovided":
                game.render("Please provide an item (i.e. \"pickup Corndog\").");
            break;
            case "nosuchitem":
                game.render("Could not find a" + Formatter.needsAn(searchQuery) + searchQuery.toLowerCase() + 
                " in your inventory. Check your spelling or type \"inventory\" to look in your inventory."
            );
            break;
            default:
                game.render("An unknown error occured. Please try again.");
                break;
        }
    }
}
