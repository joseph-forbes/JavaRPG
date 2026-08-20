package com.jforbes.javarpg.commands;

import com.jforbes.javarpg.gameengine.Engine;

public interface Command {
    abstract void execute(Engine game, String[] args);
    abstract String getMan();
}
