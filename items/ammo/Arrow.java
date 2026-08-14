package items.ammo;

import items.Ammo;
import items.Consumable;

public class Arrow extends Consumable implements Ammo {

    @Override
    public int getDamage() {
        return 3;
    }

    @Override
    public void shoot() {
        reduceUses();
    }
    
}
