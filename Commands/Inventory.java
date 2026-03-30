package commands;

import gameengine.Engine;

public class Inventory implements Command {
    public String getMan() {
        return "Look at all items in your inventory.";
    }
    public void execute(Engine game, String[] args) {

    }
}
