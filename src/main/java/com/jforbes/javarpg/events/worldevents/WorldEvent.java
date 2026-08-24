package com.jforbes.javarpg.events.worldevents;

import com.jforbes.javarpg.gameengine.Engine;

public record WorldEvent(Engine gameState) implements Event {
    
}
