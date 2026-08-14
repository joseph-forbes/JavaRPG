package items.ammo;

import items.Ammo;
import items.Food;

public class Corndog extends Food implements Ammo {

    public Corndog() {
        name = "Corndog";
        healingPower = 3;
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
