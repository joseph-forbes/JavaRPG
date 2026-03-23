package Commands;

import Game.Adventure;

public interface Command {
    abstract void execute(Adventure game, String[] args);
}
