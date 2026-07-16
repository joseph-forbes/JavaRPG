package items;

public class Consumable extends Item {
    protected int remainingUses;

    public Consumable() {
        this("Consumable");
    }
    public Consumable(String name) {
        this(name, "");
        function = "Just kind of takes up inventory space. You currently have " + remainingUses + ".";
    }
    public Consumable(String name, String function) {
        this(name, function, 1);
    }
    public Consumable(String name, String function, int uses) {
        super(name, function);
        this.remainingUses = uses;
    }

    @Override
    public void use() {
        consume();
        remainingUses--;
    }
    public void reduceUses() {
        remainingUses--;
    }
    protected void consume() {

    }
    @Override
    public boolean isUsed() {
        return remainingUses <= 0;
    }
}
