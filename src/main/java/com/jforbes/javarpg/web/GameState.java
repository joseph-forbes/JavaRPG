package com.jforbes.javarpg.web;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.util.Formatter;

public record GameState(
    boolean isInitialized, 
    boolean isGameOver,
    String[] entityNames,
    String[] itemNames,
    String[] validDirections
) {
    public GameState(Engine game) {
        this(
            game.isInitialized(),
            game.isGameOver(),
            game.isInitialized() ? Formatter.format(game.getCurrentLocation().getContents()) :  new String[0],
            game.isInitialized() ? Formatter.format(game.getPlayer().getInventory()) :  new String[0],
            game.isInitialized() ? game.getCurrentLocation().getValidDirs() :  new String[0]
        );
    }
    public GameState(boolean isInitialized, boolean isGameOver) {
        this(
            isInitialized, 
            isGameOver, 
            new String[0], new String[0], new String[0]
        );
    }
}
