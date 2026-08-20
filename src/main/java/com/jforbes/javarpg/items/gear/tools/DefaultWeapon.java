package com.jforbes.javarpg.items.gear.tools;

public class DefaultWeapon extends Weapon {
    public DefaultWeapon() {
        super("Pair of Fists", 1, 0);
    }
    @Override
    public boolean isUsed() {
        return true; // Should never exist in inventory
    }
}
