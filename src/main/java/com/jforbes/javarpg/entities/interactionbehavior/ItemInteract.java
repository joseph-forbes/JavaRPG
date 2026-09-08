package com.jforbes.javarpg.entities.interactionbehavior;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.items.Item;

public class ItemInteract implements InteractionBehavior {
    private final Item item;

    public ItemInteract(Item item) {
        this.item = item;
    }
    @Override
    public void interact(Engine game) {
        item.pickup(game.getPlayer().getInventory());
        game.render("You pick the " + item.getName() + " up.");
    }
    
}
