package items.gear.armor;

import items.Item;

public class Armor extends Item {
    protected int addedAC;
    public Armor(String name, int addedAC) {
        super(name);
        this.addedAC = addedAC;

        function = "provides a +" + addedAC + " bonus to your defense.";

        setDescription();
    }

    public int getAddedAC() {
        return addedAC;
    }
}
