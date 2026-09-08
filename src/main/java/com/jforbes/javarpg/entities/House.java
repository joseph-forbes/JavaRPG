package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.entities.interactionbehavior.HouseInteract;
import com.jforbes.javarpg.util.LocationId;

public class House extends Entity {
    public House(String name, String detailedDescription) {
        this(name, detailedDescription, "You see " + name + ".");
    }
    public House(String name, String detailedDescription, String description) {
        this(name, detailedDescription, description, new LocationId(name));
    }
    public House(String name, String detailedDescription, String description, LocationId id) {
        super(name, detailedDescription, description);
        interactionBehavior = new HouseInteract(name, id);
    }

}
