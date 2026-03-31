package worldmap;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;

public class MapTile {
    public List<Entity> contents = new ArrayList<>();

    public MapTile(ArrayList<Entity> contents) {
        this.contents = contents;
    }
    public MapTile() {
        contents = new ArrayList<Entity>();
    }

    public void update() {
        for(Entity entity : contents) {
            entity.update();
        }
        collectGarbage();
    }
    
    private void collectGarbage() {
        for(int i=contents.size()- 1; i>= 0; i--) {
            Entity entity = contents.get(i);
            if(entity.isDead()) {
                contents.remove(i);
            }
        }
    }
}
