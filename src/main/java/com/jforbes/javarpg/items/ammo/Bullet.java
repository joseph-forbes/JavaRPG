package com.jforbes.javarpg.items.ammo;

import com.jforbes.javarpg.items.Ammo;
import com.jforbes.javarpg.items.Consumable;

public class Bullet extends Consumable implements Ammo {

    @Override
    public int getDamage() {
        return 5;
    }

    @Override
    public void shoot() {
        reduceUses();
    }
    
}
