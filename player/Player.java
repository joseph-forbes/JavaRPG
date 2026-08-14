package player;
import entities.Creature;
import items.Item;
import items.gear.armor.Armor;
import items.gear.armor.DefaultArmor;
import items.gear.armor.NoArmor;
import items.gear.tools.DefaultWeapon;
import items.gear.tools.Weapon;
import util.Position;
import util.enums.Positions;
import util.enums.Stats;
import util.enums.Die;

public class Player extends Creature {
    private Position location = new Position(2, 2);
    private Inventory inventory = new Inventory();
    private Equipment equipment;

    private int lvl;
    private int xpToNextLvl;
    private int xp;

    public Player(String name) {
        this.name = name;
        lvl = 1;
        xpToNextLvl = 10000;
        xp = 0;
        hp = 10; 
        MAX_HP = hp;
        ac = 10;
        damage = 1;
        damageBonus = 0;
        equipment = new Equipment(new DefaultArmor(), new DefaultWeapon());
    }

    public Position getPos() {
        return this.location;
    }
    public void setPos(Positions pos, int value) {
        location.setPos(pos, value);
    }
    public void changePos(Positions pos, int amt) {
        switch (pos) {
            case X:
                setPos(pos, location.x + amt);
            break;
            case Y:
                setPos(pos, location.y + amt);
            break;
            default:

            break;
        }
    }
        
    public int getLvl() {
        return lvl;
    }
    public int getXp() {
        return xp;
    }
    public void setXp(int amt) {
        xp = amt;
        if(xp > xpToNextLvl) {
            lvl++;
            xp -= xpToNextLvl;
            xpToNextLvl += 100;
        }
    }
    public void changeXp(int amt) {
        setXp(xp + amt);
    }

    public Inventory getInventory() {
        return inventory;
    }
    public void addToInventory(Item item) {
        inventory.contents.add(item);
    }

    public void setEquipment(Armor armor) {
        inventory.contents.add(equipment.getArmor());
        equipment.setArmor(armor);
        // If armor is in inventory, remove it
        if(inventory.contents.contains(armor)) {
            inventory.contents.remove(armor);
        }
        ac = armor.getAddedAC() + 10;
    }
    public void setEquipment(Weapon weapon) {
        inventory.contents.add(equipment.getWeapon());
        // Remove new weapon from inventory and add it to equipment
        equipment.setWeapon(weapon);
        // If weapon is in inventory, remove it
        if(inventory.contents.contains(weapon)) {
            inventory.contents.remove(weapon);
        }

        damageBonus = weapon.getDamageBonus();
        damage = weapon.getDamage();
    }
    public Weapon getWeapon() {
        return equipment.getWeapon();
    }
    public Armor getArmor() {
        return equipment.getArmor();
    }

    @Override
    public void updateLogic() {
        inventory.update();
        if(equipment.getArmor() instanceof NoArmor) {
            hp--;
            System.out.println("\nApparently clothes help warm you up. You take 1 cold damage.");
        }
    }

    @Override
    public void hit(Creature creature) {
        // Update stats
        damage = equipment.getWeapon().attack(this);
        


        // roll d20
        if(damage == 0) {
            return;
        }
        int roll = Die.D20.roll();
        if(roll + damageBonus >= creature.getStat(Stats.AC)) {
            creature.takeHit(damage);
            if(roll == 20) {
                System.out.println("A critical hit!");
                creature.takeHit(damage);
                System.out.println("You hit the " + creature.getName() + " for " + damage * 2 + " damage.");
                if(creature.isDead()) {
                    System.out.println("The " + creature.getName() + " died!");
                }
            } else {
                System.out.println("You hit the " + creature.getName() + " for " + damage + " damage.");
                if(creature.isDead()) {
                    System.out.println("The " + creature.getName() + " died!");
                }
            }
        } else if(roll != 1) {
            System.out.println("You missed.");
        } else {
            System.out.println("A critical failure :(");
            hp -= damage;
            System.out.println("You hit yourself for " + damage + "damage. You have " + hp + " hp remaining.");
        }    }
}
