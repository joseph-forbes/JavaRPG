package com.jforbes.javarpg.entities;

import com.jforbes.javarpg.entities.interactionbehavior.NPCInteract;

public class NPC extends Entity {
    public NPC(String name, String detailedDescription, String description) {
        super(name, detailedDescription, description, new NPCInteract());
    }

    public void add(String text) {
        ((NPCInteract)interactionBehavior).add(text);
    }

    public void resetTextTree() {
        ((NPCInteract)interactionBehavior).resetTextTree();
    }
}
