package items.gear.tools;

import gameengine.Engine;
import items.Consumable;

public class Bow extends Weapon {
    private Consumable ammo;
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
        this(name, damage, damageBonus, new Consumable("Arrow"));
    }
    public Bow(String name, int damage, int damageBonus, Consumable ammo) {
        super(name, damage, damageBonus);
        this.ammo = ammo;
    }

    public Consumable getammo() {
        return ammo;
    }
    @Override
    public void use(Engine game) {
        this.ammo.reduceUses();
    }
}
