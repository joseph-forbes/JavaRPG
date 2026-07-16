package items;

public class Consumable extends Item {
    protected int usesRemaining;
    protected String itemFunction;

    public Consumable() {
        this("Consumable");
    }
    public Consumable(String name) {
        this(name, "Just kind of takes up inventory space.");
    }
    public Consumable(String name, String function) {
        this(name, function, 1);
    }
    public Consumable(String name, String function, int maxUses) {
        super(name);
        itemFunction = function;
        this.usesRemaining = maxUses;
        this.function = itemFunction + " Has " + usesRemaining + " uses remaining.";
    }

    @Override
    public void use() {
        usesRemaining--;
        updateFunction();
    }
    @Override
    protected void updateFunction() {
        function = itemFunction + " Has " + usesRemaining + " uses remaining.";
    }
    @Override
    public boolean isUsed() {
        return uses > MAX_USES;
    }
}
