package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.util.LocationId;

public class Entity {

    protected boolean isEnemy = false;
    protected String description;
    protected String detailedDescription;
    protected String name = "thing";
    protected String interactionText;
    protected String oDescription;
    protected String oDetailedDescription;
    protected LocationId locationId;

    public Entity() {
        this("Generic Thing");
    }
    public Entity(String name) {
        this(name, "There does not seem to be anything particularly special about the " + name.toLowerCase() + " and you get the vibe it exists purely as filler content.");
    }
    public Entity(String name, String detailedDescription) {
        this(name, detailedDescription, "You see a " + name.toLowerCase() + ".");
    }
    public Entity(String name, String detailedDescription, String description) {
        this(name, detailedDescription, description, "It doesn't do anything.");
    }
    public Entity(String name, String detailedDescription, String description, String interactionText) {
        this.name = name;
        oDescription = this.description = description;
        oDetailedDescription = this.detailedDescription = detailedDescription;
        this.interactionText = interactionText;
        updateDescription();
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String newDescription) {
        oDescription = newDescription;
    }
    public void setDetailedDescription(String newDetailedDescription) {
        oDetailedDescription = newDetailedDescription;
    }
    protected void updateDescription() {
        description = oDescription;
        detailedDescription = oDetailedDescription;
    }
    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void update(Engine game) {
        updateLogic(game);
        updateRender();
    }
    protected void updateLogic(Engine game) {

    }
    protected void updateRender() {
        updateDescription();
    }
    public void interact(Engine game) {
        game.render(interactionText);
    }

    public boolean isDead() {
        return false;
    }

    public String toString() {
        return name;
    }

    public void setLocation(LocationId id) {
        locationId = id;
    }
}
