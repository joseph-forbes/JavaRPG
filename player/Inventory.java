package player;

import java.util.ArrayList;
import java.util.List;

import gameengine.Engine;
import items.Item;

public class Inventory {
    public List<Item> contents;

    public Inventory(ArrayList<Item> contents) {
        this.contents = contents;
    }
    public Inventory() {
        contents = new ArrayList<Item>();
    }

    private void collectGarbage() {
        for(int i=contents.size()- 1; i>= 0; i--) {
            Item item = contents.get(i);
            if(item.isUsed()) {
                contents.remove(i);
            }
        }
    }

    public void update(Engine game) {
        for(int i=contents.size() - 1; i>=0; i--) {
            Item item = contents.get(i);
            item.update(game); // Most item updates do basically nothing due to just sitting in your inventory but this may have an effect later
        }
        collectGarbage();
    }
}
