package Entities;

import WorldMap.MapTile;
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

    public void takeDamage(int damage, int damageBonus, MapTile currentMapTile) {
        // roll d20
        int roll = die.roll(20);
        if(ac <= roll + damageBonus) {
            hp -= damage;
            if(roll == 20) {
                // crit
                hp -= damage;
                System.out.println("A critical hit!");
            }
            if(hp <= 0) {
                isDead = true;
            }
        } else if(roll == 0) {
            System.out.println("A critical failure :(");
        }
        setDescription();
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


    private void setDescription() {
        description = "You see a " + name.toLowerCase() + ". It looks back at you.";
        detailedDescription = "You look at the " + name.toLowerCase() 
                            + ". It has " + hp + " hp. " 
                            + (isEnemy ? "It does not look very friendly. " : "It does not look too upset with you.");
    }
}