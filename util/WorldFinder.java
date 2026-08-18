package util;

import worldmap.Location;
import entities.Entity;

public class WorldFinder {
    public static Entity find(Location location, Class<? extends Entity> entityType) {
        return location.getContents()
            .stream()
            .filter(entityType::isInstance)
            .findFirst()
            .orElse(null);
    }
}
