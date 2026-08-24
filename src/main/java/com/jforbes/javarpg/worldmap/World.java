package com.jforbes.javarpg.worldmap;

import java.util.HashMap;
import java.util.Map;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.player.Player;
import com.jforbes.javarpg.util.LocationId;
import com.jforbes.javarpg.util.enums.Direction;


public class World {
    Map<LocationId, Location> locations;

    public World() {
        locations = new HashMap<LocationId, Location>();
    }

    public void update(Engine game) {
        for(Location location : locations.values()) {
           location.update(game);
        }
    }
    public void add(LocationId id, Location location) {
        locations.put(id, location);
    }
    public void add(Location location) {
        locations.put(location.getLocationId(), location);
    }
    public Location get(LocationId id) {
        return locations.get(id);
    }
    public Location get(String id) {
        return get(getIdByString(id));
    }
    public LocationId getIdByString(String id) {
        for(LocationId key : locations.keySet()) {
            if(key.equals(id)) {
                return key;
            }
        }
        throw new NullPointerException("Could not find location: \"" + id + "\".");
    }
    public Location getCurrentLocation(Player player) {
        return get(player.getLocation());
    }

    public static void connect(Location location1, Location location2, Direction dir1to2) {
        location1.connect(dir1to2, location2);
        switch(dir1to2) {
            case NORTH:
                location2.connect(Direction.SOUTH, location1);
            break;
            case SOUTH:
                location2.connect(Direction.NORTH, location1);
            break;
            case EAST:
                location2.connect(Direction.WEST, location1);
            break;
            case WEST:
                location2.connect(Direction.EAST, location1);
            break;
        }
    }  
}
