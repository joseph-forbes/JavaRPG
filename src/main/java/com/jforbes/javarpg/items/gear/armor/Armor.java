package com.jforbes.javarpg.items.gear.armor;

import com.jforbes.javarpg.items.Item;

public class Armor extends Item {
    protected int addedAC;
    public Armor(String name, int addedAC) {
        super(name);
        this.addedAC = addedAC;

        function = "provides a +" + addedAC + " bonus to your defense.";

        updateDescription();
    }
    public Armor() {
        this("", 0);
    }

    public int getAddedAC() {
        return addedAC;
    }
}
