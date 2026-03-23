package Items.Gear.Armor;

import Items.Item;

public class Armor extends Item {
    protected int addedAC;
    public Armor(String name, int addedAC) {
        this.name = name;
        this.addedAC = addedAC;
    }
}
