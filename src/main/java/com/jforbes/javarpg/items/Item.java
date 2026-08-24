package com.jforbes.javarpg.items;

import com.jforbes.javarpg.entities.Entity;
import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.player.Inventory;
import com.jforbes.javarpg.util.Formatter;

public class Item extends Entity {
    protected String function;
    protected boolean isInInventory = false; // Game world

    public void pickup(Inventory inventory) {
        // Remove from game world
        isInInventory = true;

        inventory.contents.add(this);
    }
    public void removeFromInventory() {
        isInInventory = false;
    }

    public Item() {
        this("item");
    }
    public Item(String name) {
        this(name, "Just kind of takes up inventory space.");
    }
    public Item(String name, String function) {
        this.name = name;
        this.function = function;
        oDescription = "You see a" + Formatter.needsAn(name) + name.toLowerCase() + " lying on the ground.";
        oDetailedDescription = "The " + name + " is lying on the ground. It is small enough to fit in your pocket.";
        updateDescription();
    }

    public void use(Engine game) {
        game.render("You used the " + name + '.');
    }

    @Override
    public void interact(Engine game) {
        // If you are interacting with an item you probably want to pick it up
        pickup(game.getPlayer().getInventory());
        game.render("You pick the " + name + " up.");
    }
    protected void updateFunction() {
        this.function = "Just kind of takes up inventory space.";
    }
    public boolean isUsed() {
        return !isInInventory; // Inventory World
    }
    @Override
    public boolean isDead() {
        return isInInventory; // Real World
    }
    public String toString() {
        return isInInventory ? function : description; // If in inventory, print item function. Otherwise, print that the item description in-world
    }
}
