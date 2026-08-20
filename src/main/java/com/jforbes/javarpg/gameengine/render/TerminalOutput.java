package com.jforbes.javarpg.gameengine.render;

public class TerminalOutput implements GameOutput {
    @Override
    public void render(String text) {
        System.out.println(text);
    }

    @Override
    public void render() {
        System.out.println();
    }
}
