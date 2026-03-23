package Entities;

import util.CombatReturn;
import util.Dice;

public class Creature extends Entity {
    public boolean isEnemy = false;
    protected int hp, MAX_HP, damage, damageBonus, ac, xpOnDeath;
    protected Dice die = new Dice();
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
        int roll = die.roll(20);
        if(ac <= roll + creature.getStat("damageBonus")) {
            hp -= creature.getStat("damage");
            boolean didCrit = (roll == 20);
            if(didCrit) {
                // crit
                hp -= creature.getStat("damage");
                System.out.println("A critical hit!");
            }
            if(hp <= 0) {
                isDead = true;
            }

            if(didCrit) {
                return new CombatReturn(creature.getStat("damage"), name);
            } else {
                return new CombatReturn(creature.getStat("damage"), name);
            }
        } else if(roll != 0) {
            return new CombatReturn(0, "opponent");
        } else {
            System.out.println("A critical failure :(");
            creature.changeStat("hp", -damage);
            return new CombatReturn(damage, "opponent");
        }
    }

    public void changeStat(String stat, int amt) {
        switch (stat) {
            case "damage":
                damage += amt;
            case "hp":
                hp += amt;
            default:
                throw new Error("That stat is either non-existent or not modifiable.");
        }
    }
    public int getStat(String stat) {
        switch (stat) {
            case "damage":
                return damage;
            case "damageBonus":
                return damageBonus;
            case "hp":
                return hp;
            case "xpOnDeath":
                return xpOnDeath;
            default:
                throw new Error("Unknown Stat Type");
        }
    }
    @Override
    public void update() {
        setDescription();
    }

    private void setDescription() {
        description = "You see a " + name.toLowerCase() + ". It looks back at you.";
        detailedDescription = "You look at the " + name.toLowerCase() 
                            + ". It has " + hp + " hp. " 
                            + (isEnemy ? "It does not look very friendly. " : "It does not look too upset with you.");
    }
}