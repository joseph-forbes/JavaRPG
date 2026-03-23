package Items;

import Player.Inventory;
import Entities.Entity;
import Game.Engine;
import Player.Player;

public class Item extends Entity {
    protected int uses = 0;
    protected int maxUses = 1;
    public boolean used = false;

    public void pickup(Engine game) {
        Player player = game.getPlayer();
        Inventory inventory = player.getInventory();

        inventory.contents.add(this);
        this.isDead = true; // Mark for removal from the world.
    }

    public Item() {}
    public Item(String name) {
        this.name = name;
    }

    public void use(Engine game) {
        uses++;
        if(uses >= maxUses) {
            used = true;
        }
    }

    @Override
    protected void setDescription() {
        description = "You see a" + (("aeiou".indexOf(name) != -1) ? "n" : "") + " lying on the ground.";
        detailedDescription = "The " + name + " appears to be on the ground. It is small enough to fit in your pocket.";
    }
}
