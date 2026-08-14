package items.ammo;

import items.Ammo;
import items.Consumable;

public class Bullet extends Consumable implements Ammo {

    @Override
    public int getDamage() {
        return 5;
    }

    @Override
    public void shoot() {
        reduceUses();
    }
    
}
