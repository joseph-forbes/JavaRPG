package com.jforbes.javarpg.worldmap;

import com.jforbes.javarpg.entities.*;
import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.items.ammo.*;
import com.jforbes.javarpg.player.Player;
import com.jforbes.javarpg.util.WorldFinder;
import com.jforbes.javarpg.util.enums.Direction;

public class WorldBuilder {
    public static World build(Engine game) {
        World world = new World();

        //Location home = buildHome();
        //game.getPlayer().setLocation(home.getLocationId());
        //world.add(home.getLocationId(), home);

        Location village = buildVillage(game, world);
        world.add(village.getLocationId(), village);




        return world;
    }

    private static Location buildHome() {
        Location home = new Location("home");
        home.setDefaultDescription("This is your home. It is very homely.");
        

        home.addEntity(new Corndog());
        home.addEntity(new Entity("Pickle statue"));
        home.addDescription(
            "This place no longer feels like home because some hooligan took all your corndogs.", 
            engine -> WorldFinder.find(home, Corndog.class) == null
        );

        return home;
    }
    private static Location buildVillage(Engine game, World world) {
        Player player = game.getPlayer();

        Location village = new Location("village");
        village.setDefaultDescription("A bustling village full of all your friends and family.");

        NPC bum = new NPC(
            "Steve", 
            "Steve is your good friend from high school. " + 
            "He lives in a hut down the street.", 
            "You see Steve"
        );
        bum.add("Hey " + player.getName() + "! How's it going?");
        bum.add("What, don't recognize your old buddy Steve?");
        bum.add("Geeze man, what's your deal? Talk to me, g*sh diggity!");
        bum.add("It's like you're a player in a video game with no ability to " + 
        "communicate beyond a fairly restrictive set of commands or somethin'.");
        bum.add("Fine. If you're gonna stare at me like a vide game character, I'll just restart my talking tree like an NPC! " + 
        "Let's see how you like it.");

        village.addEntity(bum);

        Location home = buildHome();
        House homeEntity = new House(
            "home", 
            "You've lived here as long as you can remember", 
            "You see your home", 
            home.getLocationId()
        );
        village.addEntity(homeEntity);
        player.setLocation(home.getLocationId());
        world.add(home.getLocationId(), home);

        //village.connect(Direction.NORTH, home.getLocationId());
        home.connect(Direction.SOUTH, village.getLocationId());


        return village;
    }
}
