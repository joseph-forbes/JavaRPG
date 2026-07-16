package items;

public class Food extends Consumable {
    protected int healingPower;
    public Food() {
        this("Food");
    }
    public Food(String name) {
        this(name, "Just kind of takes up inventory space.");
    }
    public Food(String name, String function) {
        this(name, function, 1);
    }
    public Food(String name, String function, int maxUses) {
        this(name, function, maxUses, 1);
    }
    public Food(String name, String function, int maxUses, int healingPower) {
        super(name, function, maxUses);
        this.healingPower = healingPower;
    }
}
