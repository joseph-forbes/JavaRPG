package com.jforbes.javarpg.entities;

import java.util.ArrayList;
import java.util.List;

public class NPC extends Entity {
    protected List<String> interactionTexts;
    private int textIndex;
    public NPC(String name, String detailedDescription, String description) {
        super(name, detailedDescription, description);
        interactionTexts = new ArrayList<String>();
        textIndex = 0;
    }

    public void add(String text) {
        interactionTexts.add(text);
    }
    public String getNextString() {
        textIndex++;
        return interactionTexts.get(textIndex);
    }
}
