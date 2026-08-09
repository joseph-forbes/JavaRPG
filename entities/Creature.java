package entities;

import util.enums.Die;
import util.enums.Stats;
import util.returnsutil.CombatReturn;

public class Creature extends Entity {
    protected int hp, MAX_HP, damage, damageBonus, ac, xpOnDeath;
    //final int MAX_HP;
    public Creature(String name, int hp, int damage, int damageBonus, int ac, int xpOnDeath) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.damageBonus = damageBonus;
        this.ac = ac;
        this.xpOnDeath = xpOnDeath;

        setDescription();
    }
    public Creature() {}

    public CombatReturn takeHit(Creature creature) {
        // roll d20
        int roll = Die.D20.roll();
        if(ac <= roll + creature.getStat(Stats.DAMAGE)) {
            hp -= creature.getStat(Stats.DAMAGE);
            boolean didCrit = (roll == 20);
            if(didCrit) {
                // crit
                hp -= creature.getStat(Stats.DAMAGE);
                System.out.println("A critical hit!");
            }

            if(didCrit) {
                return new CombatReturn(creature.getStat(Stats.DAMAGE) * 2, name, hp);
            } else {
                return new CombatReturn(creature.getStat(Stats.DAMAGE), name, hp);
            }
        } else if(roll != 0) {
            return new CombatReturn(0, "opponent", hp);
        } else {
            System.out.println("A critical failure :(");
            creature.changeStat(Stats.HP, -damage);
            return new CombatReturn(damage, "opponent", hp);
        }
    }

    public void changeStat(Stats stat, int amt) {
        switch (stat) {
            case HP:
                hp += amt;
                break;
            case DAMAGE:
                damage += amt;
                break;
            default:
                throw new Error(stat + " is either non-existent or not modifiable.");
        }
    }
    public int getStat(Stats stat) {
        switch (stat) {
            case DAMAGE:
                return damage;
            case DAMAGE_BONUS:
                return damageBonus;
            case HP:
                return hp;
            case XP_ON_DEATH:
                return xpOnDeath;
            default:
                throw new Error("Unknown Stat Type");
        }
    }

    @Override
    protected void setDescription() {
        description = "You see a " + name.toLowerCase() + ". It looks back at you.";
        detailedDescription = "You look at the " + name.toLowerCase() 
                            + ". It has " + hp + " hp. " 
                            + (isEnemy ? "It does not look very friendly. " : "It does not look too upset with you.");
    }

    @Override
    public boolean isDead() {
        return hp <= 0;
    }
}