package commands;

import gameengine.Engine;

public interface Command {
    abstract void execute(Engine game, String[] args);
    abstract String getMan();
}
