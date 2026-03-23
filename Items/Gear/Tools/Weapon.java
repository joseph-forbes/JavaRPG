package Items.Gear.Tools;

import Items.Item;

public class Weapon extends Item {
    protected int damage;
    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }
}
