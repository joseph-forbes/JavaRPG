package entities;

import gameengine.Engine;
import util.enums.Die;
import util.enums.Stats;

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
        interactionText = "The " + name + " doesn't seem to be much of a talker.";

        setDescription();
    }
    public Creature() {}

    public void hit(Creature creature, Engine game) {
        // roll d20
        int roll = Die.D20.roll();
        if(roll + damageBonus >= creature.getStat(Stats.AC)) {
            creature.takeHit(damage);
            if(roll == 20) {
                game.render("A critical hit!");
                creature.takeHit(damage);
                game.render(name + " hit " + creature.name + " for " + damage * 2 + " damage.");
            } else {
                game.render(name + " hit " + creature.name + " for " + damage + " damage.");
            }
        } else if(roll != 1) {
            game.render(name + " missed.");
        } else {
            game.render("A critical failure!");
            hp -= damage;
            game.render(name + " hit itself for " + damage + " damage. " + name + " has " + creature.getStat(Stats.HP) + " hp remaining");
        }
    }

    public void takeHit(int damage) {
        hp -= damage;
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
            case AC:
                return ac;
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