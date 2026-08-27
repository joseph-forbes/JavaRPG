package com.jforbes.javarpg.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.gameengine.render.CollectionOutput;
import com.jforbes.javarpg.util.Formatter;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final CollectionOutput output;
    private final Engine game;

    public GameController() {
        this.output = new CollectionOutput();
        this.game = new Engine(output);
    }

    @GetMapping
    public TurnResult getGame() {
        return new TurnResult(
            output.getMessages(),
            new GameState(
                game.isInitialized(), 
                game.isGameOver(),
                game.isInitialized() ? Formatter.format(game.getCurrentLocation().getContents()) :  new String[0]
            )
        );
    }

    @PostMapping("/restart") 
    public TurnResult resetGame() {
        game.reset();
        output.clear();

        return new TurnResult(
            output.getMessages(),
            new GameState(false, false, new String[0])
        );
    }

    @PostMapping("/start")
    public TurnResult startGame(@RequestBody StartGameRequest request) {

        output.clear();

        game.initialize(request.playerName());

        return new TurnResult(
            output.getMessages(), 
            new GameState(true, false, Formatter.format(game.getCurrentLocation().getContents()))
        );
    }

    @PostMapping("/command")
    public TurnResult command(@RequestBody CommandRequest request) {

        output.clear();

        System.out.println(request.message());
        game.executeTurn(request.message());

        return new TurnResult(
            output.getMessages(),
            new GameState(game.isInitialized(), game.isGameOver(), Formatter.format(game.getCurrentLocation().getContents()))
        );
    }
}