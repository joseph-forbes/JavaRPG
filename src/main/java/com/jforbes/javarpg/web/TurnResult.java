package com.jforbes.javarpg.web;

import java.util.List;

public record TurnResult(List<String> messages, GameState state) {
    
}
