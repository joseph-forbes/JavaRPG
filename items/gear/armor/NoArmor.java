package items.gear.armor;

public class NoArmor extends Armor {
    public NoArmor() {
        super("Nothing", -5);
    }
    @Override
    public boolean isUsed() {
        return true; // Should never exist in inventory
    }
}
