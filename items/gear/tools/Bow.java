package items.gear.tools;

import items.Ammo;
import items.ammo.Arrow;
import player.Player;
import util.InventoryFinder;

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
    public int attack(Player player) {
        Ammo ammo = (Ammo) InventoryFinder.find(player.getInventory(), ammoType);

        if (ammo == null) {
            System.out.println("You don't have any " + ammoType.getSimpleName().toLowerCase() + "s.");
            return 0;
        } else {
            // Use the ammo
            ammo.shoot();
            return damage + ammo.getDamage();
        }

        // Perform attack...
        //System.out.println("Fired " + ammoType.getSimpleName());
    }
}
