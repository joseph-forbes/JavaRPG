package player;
import entities.Creature;
import items.Item;
import util.Position;
import util.enums.Positions;

public class Player extends Creature {
    private Position location = new Position(2, 2);
    private Inventory inventory = new Inventory();

    private int lvl = 1;
    private int xpToNextLvl = 10000;
    private int xp = 0;

    public Player(String name) {
        this.name = name;
        hp = 10; 
        MAX_HP = hp;
        ac = 10;
        damage = 1;
        damageBonus = 0;
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

    @Override
    public void updateLogic() {
        inventory.update();
    }
}
