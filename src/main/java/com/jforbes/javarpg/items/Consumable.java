package com.jforbes.javarpg.items;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.util.Formatter;

public class Consumable extends Item {
    protected int usesRemaining;
    protected String itemFunction;

    public Consumable() {
        this("Consumable");
    }
    public Consumable(String name) {
        this(name, "Just kind of takes up inventory space.");
    }
    public Consumable(String name, String function) {
        this(name, function, 1);
    }
    public Consumable(String name, String function, int maxUses) {
        super(name);
        itemFunction = function;
        this.usesRemaining = maxUses;
        this.function = itemFunction + " Has " + usesRemaining + " uses remaining.";
    }

    @Override
    public void use(Engine game) {
        consume(game);
        usesRemaining--;
        function = itemFunction + " Has " + usesRemaining + " use" + Formatter.needsPlural(usesRemaining) +" remaining.";
    }
    public void reduceUses() {
        usesRemaining--; // Use without consuming
        function = itemFunction + " Has " + usesRemaining + " use" + Formatter.needsPlural(usesRemaining) +" remaining.";
    }
    protected void consume(Engine game) {

    }
    @Override
    protected void updateDescription() {
        description = "You see a" + Formatter.needsAn(name) + name.toLowerCase() + " lying on the ground.";
        detailedDescription = 
            usesRemaining > 1 ? 
            "The " + name.toLowerCase() + " appears to be small enough to fit in your pocket" : 
            "There are " + usesRemaining + " " + name.toLowerCase() + "s in a pile. You can probably fit all of them in your pocket.";

    }
    @Override
    public boolean isUsed() {
        return usesRemaining <= 0;
    }
}
