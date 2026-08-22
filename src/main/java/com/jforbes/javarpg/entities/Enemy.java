package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.gameengine.Engine;

public class Enemy extends Creature {
    public Enemy(String name, int hp, int damage, int damageBonus, int ac, int xpOnDeath) {
        super(name, hp, damage, damageBonus, ac, xpOnDeath);
        isEnemy = true;

        updateDescription();
    }

    @Override
    public void update(Engine game) {
        game.render("The " + name + " took a swing at you.");
        hit(game.getPlayer(), game);
    }
}
