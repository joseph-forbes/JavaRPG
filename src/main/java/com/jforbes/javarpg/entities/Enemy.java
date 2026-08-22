package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.gameengine.Engine;

public class Enemy extends Creature {

    public Enemy(String name, int MAX_HP, int damage, int damageBonus, int ac, int xpOnDeath) {
        super(name, MAX_HP, damage, damageBonus, ac, xpOnDeath);
    }

    @Override
    protected boolean becomeEnemy(Engine game) {
        if(game.getPlayer().getLocation() == locationId) {
            game.render("The " + name.toLowerCase() + " notices you and squares up.");
            return true;
        }
        return false;
    }
    @Override
    protected boolean becomeNeutral(Engine game) {
        // There may be some creatures which don't de-aggro on player leave, 
        // but by default they do for texting purposes
        return game.getPlayer().getLocation() != locationId;
    }

}
