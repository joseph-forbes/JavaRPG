package items.gear.armor;

public class NoArmor extends Armor {
    public NoArmor() {
        super("None", -1);
    }
    @Override
    public boolean isUsed() {
        return true; // Should never exist in inventory
    }
}
