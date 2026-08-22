package com.jforbes.javarpg.worldmap;

import java.util.HashMap;
import java.util.Map;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.player.Player;
import com.jforbes.javarpg.util.LocationId;


public class World {
    Map<LocationId, Location> locations;

    public World() {
        locations = new HashMap<LocationId, Location>();
    }

    public void update(Engine game) {
        //getCurrentLocation(game.getPlayer()).update(game);;
        for(Location location : locations.values()) {
           location.update(game);
        }
    }
    public void add(LocationId id, Location location) {
        locations.put(id, location);
    }
    public Location get(LocationId id) {
        return locations.get(id);
    }
    public Location get(String id) {
        for(LocationId key : locations.keySet()) {
            if(key.equals(id)) {
                return locations.get(key);
            }
        }
        throw new NullPointerException("Could not find location: \"" + id + "\".");
    }
    public Location getCurrentLocation(Player player) {
        return get(player.getLocation());
    }
}
