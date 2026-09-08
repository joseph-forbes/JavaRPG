package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.util.enums.Die;
import com.jforbes.javarpg.util.enums.Stats;

public class Creature extends Entity {
    protected int hp, MAX_HP, damage, damageBonus, ac, xpOnDeath;
    protected boolean isEnemy;
    public Creature(String name, int hp, int damage, int damageBonus, int ac, int xpOnDeath) {
        super(
            name, 
            "The " + name.toLowerCase() + " looks back at you.", 
            "You see a " + name.toLowerCase(), 
            "The " + name + " doesn't seem to be much of a talker."
        );
        this.hp = hp;
        this.damage = damage;
        this.damageBonus = damageBonus;
        this.ac = ac;
        this.xpOnDeath = xpOnDeath;
        isEnemy = false;

        updateDescription();
    }

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

    protected boolean becomeEnemy(Engine game) {
        // Engine needed to render if the creature gains aggression
        return false;
    }
    protected boolean becomeNeutral(Engine game) {
        // There may be some creatures which don't de-aggro on player leave, 
        // but by default they do for texting purposes
        return game.getPlayer().getLocation() != locationId;
    }
    @Override
    protected void updateLogic(Engine game) {
        if(becomeNeutral(game)) {
            isEnemy = false;
        }
        if(isEnemy && !isDead()) {
            game.render("The " + name.toLowerCase() + " took a swing at you.");
            hit(game.getPlayer(), game);
        }
        if(becomeEnemy(game)) {
            isEnemy = true;
        }
    }
    @Override
    protected void updateDescription() {
        description = oDescription;
        detailedDescription = oDetailedDescription
                            + " Has " + hp + " hp. " 
                            + (isEnemy ? "Not very friendly-looking. " : "Doesn't look too upset with you.");
    }

    @Override
    public boolean isDead() {
        if(hp <= 0) {
            removeFromWorld();
            return true;
        }
        return false;
    }
}