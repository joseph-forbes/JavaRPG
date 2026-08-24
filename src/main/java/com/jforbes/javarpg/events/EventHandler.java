package com.jforbes.javarpg.events;

import java.util.function.Predicate;

import com.jforbes.javarpg.events.worldevents.Event;

public record EventHandler<E extends Event> (
    Class<E> eventType,
    Predicate<E> condition,
    EventAction<E> action
) {
}
