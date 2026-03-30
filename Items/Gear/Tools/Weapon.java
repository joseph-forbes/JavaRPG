package items.gear.tools;

import items.Item;

public class Weapon extends Item {
    protected int damage, damageBonus;
    public Weapon(String name, int damage, int damageBonus) {
        this.name = name;
        this.damage = damage;
        this.damageBonus = damageBonus;

        setDescription();
    }
}
