package Commands;

import Game.Engine;

public interface Command {
    abstract void execute(Engine game, String[] args);
}
