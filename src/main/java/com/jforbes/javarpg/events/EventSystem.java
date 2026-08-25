package com.jforbes.javarpg.events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.jforbes.javarpg.events.worldevents.Event;

public class EventSystem {
    private List<EventHandler<? extends Event>> events;
    public EventSystem() {
        events = new ArrayList<>();
    }

    public <E extends Event> void register(
        Class<E> eventType, 
        Predicate<E> condition,
        EventAction<E> action
    ) {
        events.add(new EventHandler<E>(eventType, condition, action));
    }

    public void publish(Event event) {
        for (EventHandler<? extends Event> handler : events) {
            processEvent(handler, event);
        }
    }

    private <E extends Event> void processEvent(
            EventHandler<E> handler,
            Event event) {

        if (!handler.eventType().isInstance(event)) {
            // Ensure this event is of the correct type
            return;
        }

        E typedEvent = handler.eventType().cast(event);

        if (handler.condition().test(typedEvent)) {
            handler.action().execute(typedEvent);
        }
    }
}
