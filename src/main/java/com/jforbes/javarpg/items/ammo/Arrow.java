package com.jforbes.javarpg.items.ammo;

import com.jforbes.javarpg.items.Ammo;
import com.jforbes.javarpg.items.Consumable;

public class Arrow extends Consumable implements Ammo {

    @Override
    public int getDamage() {
        return 3;
    }

    @Override
    public void shoot() {
        reduceUses();
    }
    
}
