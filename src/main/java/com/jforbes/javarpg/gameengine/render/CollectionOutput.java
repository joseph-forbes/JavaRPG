package com.jforbes.javarpg.gameengine.render;

import java.util.ArrayList;
import java.util.List;

public class CollectionOutput implements GameOutput {

    private final List<String> messages = new ArrayList<>();

    @Override
    public void render(String text) {
        messages.add(text);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void clear() {
        messages.clear();
    }

    @Override
    public void render() {
        messages.add("<br>");
    }
}