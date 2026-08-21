package com.jforbes.javarpg.entities;

import java.util.ArrayList;
import java.util.List;

import com.jforbes.javarpg.gameengine.Engine;

public class NPC extends Entity {
    protected List<String> interactionTexts;
    private int textIndex;
    public NPC(String name, String detailedDescription, String description) {
        super(name, detailedDescription, description);
        interactionTexts = new ArrayList<String>();
        textIndex = 0;
    }

    @Override
    public void interact(Engine game) {
        game.render(getNextString());
    }

    public void add(String text) {
        interactionTexts.add(text);
    }
    private String getNextString() {
        textIndex++;
        if(textIndex <= interactionTexts.size()) { // <= because index needs to be incremented before returning the previous index value
            return interactionTexts.get(textIndex - 1);
        } else {
            textIndex = 1; // Start off with the second index since the first is called right here
            return interactionTexts.get(0);
        }
    }
}
