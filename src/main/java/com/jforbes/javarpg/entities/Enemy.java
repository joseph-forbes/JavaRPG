package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.gameengine.Engine;

public class Enemy extends Creature {

    public Enemy(String name, int MAX_HP, int damage, int damageBonus, int ac, int xpOnDeath) {
        super(name, MAX_HP, damage, damageBonus, ac, xpOnDeath);
    }

    @Override
    protected boolean becomeEnemy(Engine game) {
        if(game.getPlayer().getLocation() == locationId) {
            if(!isEnemy)
                game.render("The " + name.toLowerCase() + " notices you and squares up.");
            return true;
        }
        return false;
    }

}
