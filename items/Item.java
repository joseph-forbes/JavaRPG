package items;

import entities.Entity;
import util.Formatter;
import player.Inventory;

public class Item extends Entity {
    protected String function;
    protected boolean isGarbage = false;

    public void pickup(Inventory inventory) {
        // Remove from game world
        isGarbage = true;

        inventory.contents.add(this);
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
        setDescription();
    }

    public void use() {
        
    }

    @Override
    protected void setDescription() {
        description = "You see a" + Formatter.needsAn(name) + name.toLowerCase() + " lying on the ground.";
        detailedDescription = "The " + name + " is lying on the ground. It is small enough to fit in your pocket.";
    }
    public boolean isUsed() {
        return false;
    }
    @Override
    public boolean isDead() {
        return isGarbage;
    }
    public String toString() {
        return isGarbage ? function : description;
    }
}
