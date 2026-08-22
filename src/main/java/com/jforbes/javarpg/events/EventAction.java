package com.jforbes.javarpg.events;

import com.jforbes.javarpg.gameengine.Engine;

public interface EventAction<E> {
    void execute(E event, Engine state);
}
