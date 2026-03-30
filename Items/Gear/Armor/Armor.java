package items.Gear.armor;

import items.Item;

public class Armor extends Item {
    protected int addedAC;
    public Armor(String name, int addedAC) {
        this.name = name;
        this.addedAC = addedAC;

        setDescription();
    }
}
