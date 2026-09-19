package com.jforbes.javarpg.items.gear.tools;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.items.Ammo;
import com.jforbes.javarpg.items.ammo.Arrow;
import com.jforbes.javarpg.player.Player;
import com.jforbes.javarpg.util.InventoryFinder;

public class Bow extends Weapon {
    final private Class<? extends Ammo> ammoType;
    public Bow() {
        this("Bow");
    }
    public Bow(String name) {
        this(name, 1);
    }
    public Bow(String name, int damage) {
        this(name, damage, 0);
    }
    public Bow(String name, int damage, int damageBonus) {
        this(name, damage, damageBonus, Arrow.class);
    }
    public Bow(String name, int damage, int damageBonus, Class<? extends Ammo> ammoType) {
        super(name, damage, damageBonus);
        this.ammoType = ammoType;
    }

    public Class<? extends Ammo> getAmmoType() {
        return ammoType;
    }
    public int attack(Player player, Engine game) {
        Ammo ammo = (Ammo) InventoryFinder.find(player.getInventory(), ammoType);

        if (ammo == null) {
            game.render("You don't have any " + ammoType.getSimpleName().toLowerCase() + "s.");
            return 0;
        } else {
            // Use the ammo
            ammo.shoot();
            return damage + ammo.getDamage();
        }
    }
}
