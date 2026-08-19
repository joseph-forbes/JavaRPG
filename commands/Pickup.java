package commands;

import entities.Entity;
import gameengine.Engine;
import items.Item;
import player.Inventory;

public class Pickup extends WorldInteractor {
    public Pickup() {
        man = "Pick up an item. Takes in an item (i.e. \"pickup corndog\".";
        commandName = "pickup";
    }

    @Override
    protected void interact(Engine game, Entity entity) {
        if(entity instanceof Item) {
            // this is an item
            Item item = (Item) entity;
            Inventory inventory = game.getPlayer().getInventory();

            item.pickup(inventory);
            game.render("You pick the " + item.getName().toLowerCase() + " up.");
        } else {
            // this does not spark item
            game.render("The " + entity.getName().toLowerCase() + " is too big to fit in your pocket.");
        }
    }
}
