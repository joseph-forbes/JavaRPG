package com.jforbes.javarpg.events;

public interface EventAction<E> {
    void execute(E event);
}
