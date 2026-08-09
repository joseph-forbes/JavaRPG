package player;
import entities.Creature;
import items.Item;
import items.gear.armor.Armor;
import items.gear.tools.Weapon;
import util.Position;
import util.enums.Positions;

public class Player extends Creature {
    private Position location = new Position(2, 2);
    private Inventory inventory = new Inventory();

    private int lvl;
    private int xpToNextLvl;
    private int xp;
    private Equipment equipment;

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
        equipment = new Equipment(new Armor("Leather Tunic", 0), new Weapon("Pair of Fists", 1));
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
        ac = armor.getAddedAC() + 10;
        inventory.contents.remove(armor);
    }
    public void setEquipment(Weapon weapon) {
        inventory.contents.add(equipment.getWeapon());
        equipment.setWeapon(weapon);
        if(!equipment.getWeapon().getName().equals("Fists"))
            inventory.contents.remove(weapon);

        damageBonus = weapon.getDamageBonus();
        damage = weapon.getDamage();
    }

    @Override
    public void updateLogic() {
        inventory.update();
    }
}
