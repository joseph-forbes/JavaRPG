package com.jforbes.javarpg.entities.interactionbehavior;

import com.jforbes.javarpg.gameengine.Engine;

public class RenderInteract implements InteractionBehavior {
    private final String text;
    public RenderInteract(String text) {
        this.text = text;
    }

    public void interact(Engine game) {
        game.render(text);
    }
}
