package items;

public class Consumable extends Item {
    protected int uses;
    protected final int MAX_USES;

    public Consumable() {
        this("Consumable");
    }
    public Consumable(String name) {
        this(name, "");
        function = "Just kind of takes up inventory space. Has " + uses + " out of " + MAX_USES + "uses.";
    }
    public Consumable(String name, String function) {
        this(name, function, 1);
    }
    public Consumable(String name, String function, int MAX_USES) {
        this.MAX_USES = MAX_USES;
    }

    @Override
    public void use() {
        uses++;
    }
    @Override
    public boolean isUsed() {
        return uses > MAX_USES;
    }
}
