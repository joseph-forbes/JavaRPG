package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.entities.interactionbehavior.InteractionBehavior;
import com.jforbes.javarpg.entities.interactionbehavior.RenderInteract;
import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.util.LocationId;

public class Entity {

    protected String description;
    protected String detailedDescription;
    protected String name;
    protected String id;
    protected String interactionText;
    protected String oDescription;
    protected String oDetailedDescription;
    protected LocationId locationId;
    protected InteractionBehavior interactionBehavior;
    private boolean isDead;

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
        this(name, detailedDescription, description, new RenderInteract(interactionText));
    }
    public Entity(
        String name, 
        String detailedDescription, 
        String description, 
        InteractionBehavior behavior
    ) {
        this.name = this.id = name;
        oDescription = this.description = description;
        oDetailedDescription = this.detailedDescription = detailedDescription;
        interactionBehavior = behavior;
        isDead = false;
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
    // Reset description on world update
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
        interactionBehavior.interact(game);
    }

    public boolean isDead() {
        return isDead;
    }
    public void removeFromWorld() {
        isDead = true;
    }

    public String toString() {
        return name;
    }

    public void setLocation(LocationId id) {
        locationId = id;
    }

    public void overrideId(String newId) {
        id = newId;
    }
    public String getId() {
        return id;
    }
}
