package items;

import gameengine.Engine;
import util.enums.Stats;

public class Food extends Consumable {
    protected int healingPower;
    public Food() {
        this("Food");
    }
    public Food(String name) {
        this(name, 1);
    }
    public Food(String name, int usesRemaining) {
        this(name, usesRemaining, 1);
    }
    public Food(String name, int usesRemaining, int healingPower) {
        super(name, "Can be eaten for " + healingPower + " health.", usesRemaining);
        this.healingPower = healingPower;
    }
    @Override
    protected void consume(Engine game) {
        game.getPlayer().changeStat(Stats.HP, healingPower);
        game.render("You ate the " + name + ". It healed you for " + healingPower + " hp.");
    }
}
