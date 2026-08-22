package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.util.LocationId;

public class House extends Entity {
    private final LocationId id;
    public House(String name, String detailedDescription, String description) {
        this(name, detailedDescription, description, new LocationId(name));
    }
    public House(String name, String detailedDescription, String description, LocationId id) {
        super(name, detailedDescription, description);
        this.id = id;
    }
    @Override
    public void interact(Engine game) {
        game.getPlayer().setLocation(id);
        game.render("You enter " + name.toLowerCase());
        game.executeCommand("look around");
    }

}
