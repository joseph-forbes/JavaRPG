package items;

import entities.Entity;
import gameengine.Engine;
import player.Player;
import util.Formatter;
import player.Inventory;

public class Item extends Entity {
    protected int uses = 0;
    protected int maxUses = 1;
    protected boolean consumable = false;
    protected boolean isGarbage = false;

    public void pickup(Engine game) {
        Player player = game.getPlayer();
        Inventory inventory = player.getInventory();

        // Remove from game world
        isGarbage = true;

        inventory.contents.add(this);
    }

    public Item() {}
    public Item(String name) {
        this.name = name;
        setDescription();
    }

    public void use(Engine game) {
        if(consumable) uses++;
    }

    @Override
    protected void setDescription() {
        description = "You see a" + Formatter.needsAn(name) + name.toLowerCase() + " lying on the ground.";
        detailedDescription = "The " + name + " is lying on the ground. It is small enough to fit in your pocket.";
    }
    public boolean isUsed() {
        return uses > maxUses;
    }
    @Override
    public boolean isDead() {
        return isGarbage;
    }
}
