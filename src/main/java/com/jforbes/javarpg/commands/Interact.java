package com.jforbes.javarpg.commands;

import com.jforbes.javarpg.entities.Entity;
import com.jforbes.javarpg.gameengine.Engine;

public class Interact extends WorldInteractor {
    @Override
    public String getMan() {
        return "Interact with an entity. " + 
        "This could be opening a door, speaking with an NPC, petting a cat, etc. " +
        "Feel free to experiment.";
    }
    @Override
    public void interact(Engine game, Entity entity) {
        entity.interact(game);
    }

    @Override
    public void handleNoEntityProvided(Engine game, String searchQuery) {
        game.render("Please provide an entity i.e. \"interact merchant\"");
    }
}
