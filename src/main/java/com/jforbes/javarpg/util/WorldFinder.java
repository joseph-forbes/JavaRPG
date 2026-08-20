package com.jforbes.javarpg.util;

import com.jforbes.javarpg.entities.Entity;
import com.jforbes.javarpg.worldmap.Location;

public class WorldFinder {
    public static Entity find(Location location, Class<? extends Entity> entityType) {
        return location.getContents()
            .stream()
            .filter(entityType::isInstance)
            .findFirst()
            .orElse(null);
    }
}
