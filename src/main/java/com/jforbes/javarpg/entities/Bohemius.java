package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.entities.interactionbehavior.NPCInteract;
import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.items.gear.armor.Armor;

public class Bohemius extends Creature {
    public Bohemius() {
        super("Bohemius", 50, 23, 6, 18, 150);
        interactionBehavior = new NPCInteract();
        setDescription("You see Bohemius.");
        setDetailedDescription(
            "He goes by \"Bohemius McPhiddlesticks III\" " + 
            "even though his father is named Phil Jenkins. " + 
            "You've never understood why but as town sheriff " + 
            "you're too scared to question him."
        );
    }

    @Override
    protected boolean becomeEnemy(Engine game) {
        Armor armor = game.getPlayer().getArmor();
        if(
            game.getPlayer().getLocation() == locationId && 
            armor.getId().equals("bohemius-trousers")
        ) {
            if(!isEnemy)
                game.render("Bohemius recognizes your trousers and is not happy about it.");
            return true;
        }
        return false;
    }
    @Override
    protected void updateLogic(Engine game) {
        if(becomeNeutral(game)) {
            isEnemy = false;
        }
        if(isEnemy && !isDead()) {
            game.render("Bohemius took a shot at you.");
            hit(game.getPlayer(), game);
        }
        // Become an enemy under the right conditions
        isEnemy = becomeEnemy(game);
        // Stop becoming an enemy, usually only on the condidtion that the player leaves the map location
    }


    @Override
    public void interact(Engine game) {
        if(isEnemy) {
            game.render("Bohemius does not seem to be in a talking mood.");
        } else {
            interactionBehavior.interact(game);
        }
    }

    public void add(String text) {
        ((NPCInteract)interactionBehavior).add(text);
    }
    public void resetTextTree() {
        ((NPCInteract)interactionBehavior).resetTextTree();
    }

}
