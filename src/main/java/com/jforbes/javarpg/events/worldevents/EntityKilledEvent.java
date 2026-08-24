package com.jforbes.javarpg.events.worldevents;

import com.jforbes.javarpg.entities.Entity;

public record EntityKilledEvent(Entity entity) implements Event {
}