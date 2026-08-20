package com.jforbes.javarpg.items.ammo;

import com.jforbes.javarpg.items.Ammo;
import com.jforbes.javarpg.items.Food;

public class Corndog extends Food implements Ammo {

    public Corndog() {
        this(1);
    }
    public Corndog(int amt) {
        super("Corndog", amt, 3);
    }

    @Override
    public int getDamage() {
        return 1;
    }

    @Override
    public void shoot() {
        reduceUses();
    }

}
