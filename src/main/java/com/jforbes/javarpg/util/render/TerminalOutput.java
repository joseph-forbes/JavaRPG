package com.jforbes.javarpg.util.render;

public class TerminalOutput implements GameOutput {
    @Override
    public void render(String text) {
        System.out.println(text);
    }
}
