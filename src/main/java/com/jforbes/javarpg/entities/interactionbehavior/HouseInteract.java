package com.jforbes.javarpg.entities.interactionbehavior;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.util.LocationId;

public class HouseInteract implements InteractionBehavior {
    private final String houseName;
    private final LocationId id;

    public HouseInteract(String houseName, LocationId id) {
        this.houseName = houseName.toLowerCase();
        this.id = id;
    }

    @Override
    public void interact(Engine game) {
        game.getPlayer().setLocation(id, game.getEvents());
        game.render("You enter " + houseName);
        game.executeCommand("look around");
    }
    
}
