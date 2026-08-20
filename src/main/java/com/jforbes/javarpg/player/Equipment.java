package com.jforbes.javarpg.player;

import com.jforbes.javarpg.items.gear.armor.Armor;
import com.jforbes.javarpg.items.gear.tools.Weapon;

public class Equipment {
    private Armor armor;
    private Weapon weapon;

    public Equipment(Armor armor, Weapon weapon) {
        this.armor = armor;
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }
    public void setArmor(Armor armor) {
        this.armor = armor;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
