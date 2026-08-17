package worldmap;

import util.LocationId;

import java.util.HashMap;
import java.util.Map;


public class World {
    Map<LocationId, Location> locations;

    public World() {
        locations = new HashMap<LocationId, Location>();
    }

    public void update() {
        for(Location location : locations.values()) {
            location.update();
        }
    }
    public void add(LocationId id, Location location) {
        locations.put(id, location);
    }
    public Location get(LocationId id) {
        return locations.get(id);
    }
}
