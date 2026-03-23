package WorldMap;
import java.util.ArrayList;
import java.util.List;

import Entities.Entity;

public class MapTile {
    public List<Entity> contents = new ArrayList<>();

    public MapTile(ArrayList<Entity> contents) {
        this.contents = contents;
    }
    public MapTile() {
        contents = new ArrayList<Entity>();
    }


}
