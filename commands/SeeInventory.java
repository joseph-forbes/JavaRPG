package commands;

import util.Formatter;

import gameengine.Engine;
import items.Item;

public class SeeInventory implements Command {
    public String getMan() {
        return "Look at all items in your inventory.";
    }
    public void execute(Engine game, String[] args) {
        player.Inventory inventory = game.getPlayer().getInventory();

        if(inventory.contents.size() > 0) {
            game.render("You check your inventory. Inside, you see: ");
            for(Item item : inventory.contents) {
                System.out.println(
                    "You have a" + Formatter.needsAn(item.toString()) + item.getName().toLowerCase() +
                    ". It " + item.toString().toLowerCase()
            );
            }
        } else {
            game.render("Your inventory is empty.");
        }
    }
}
