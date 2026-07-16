package items.gear.tools;

import items.Item;

public class Weapon extends Item {
    protected int damage, damageBonus;
    public Weapon() {
        this("Weapon");
    }
    public Weapon(String name) {
        this(name, 0);
    }
    public Weapon(String name, int damage) {
        this(name, damage, 0);
    }
    public Weapon(String name, int damage, int damageBonus) {
        super(name);
        this.damage = damage;
        this.damageBonus = damageBonus;

        function = "Deals " + damage + " attack damage and adds a +" + damageBonus + " bonus to accuracy rolls.";

        setDescription();
    }
    
    public int getDamage() {
        return damage;
    }
    public int getDamageBonus() {
        return damageBonus;
    }
}
