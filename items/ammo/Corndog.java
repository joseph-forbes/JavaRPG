package items.ammo;

import items.Ammo;
import items.Food;

public class Corndog extends Food implements Ammo {

    public Corndog() {
        this(1);
    }
    public Corndog(int amt) {
        super("Corndog", amt, 3);
    }

    @Override
    public int getDamage() {
        return 1;
    }

    @Override
    public void shoot() {
        reduceUses();
    }

}
