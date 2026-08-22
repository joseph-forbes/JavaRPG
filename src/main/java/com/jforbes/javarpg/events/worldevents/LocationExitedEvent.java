package com.jforbes.javarpg.events.worldevents;

import com.jforbes.javarpg.util.LocationId;

public record LocationExitedEvent(LocationId previousLocation, LocationId newLocation) implements Event {
}
