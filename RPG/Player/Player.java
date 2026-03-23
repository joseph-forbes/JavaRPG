package Player;
import util.Position;
import Entities.Creature;
public class Player extends Creature {
    Position pos = new Position(2, 2);

    int lvl = 1;

    public Player(String name) {
        this.name = name;
        hp = 10; 
        MAX_HP = hp;
        ac = 10;
        damage = 1;
        damageBonus = 0;
    }

    public Position getPos() {
        return this.pos;
    }
    public void setPos(char pos, int value) {
        switch (pos) {
            case 'x':
                this.pos.x = value;
            break;
            case 'y':
                this.pos.y = value;
            break;
            default:
                throw new Error("Please provide only x or y");
        }
    }
    public void changePos(char pos, int amt) {
        switch (pos) {
            case 'x':
                this.pos.x += amt;
            break;
            case 'y':
                this.pos.y += amt;
            break;
            default:
                throw new Error("Please provide only x or y");
        }
    }
}
