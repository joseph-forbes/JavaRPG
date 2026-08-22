package com.jforbes.javarpg.events.worldevents;

import com.jforbes.javarpg.util.LocationId;

public record LocationEnteredEvent(LocationId previousLocation, LocationId newLocation) implements Event {
}
