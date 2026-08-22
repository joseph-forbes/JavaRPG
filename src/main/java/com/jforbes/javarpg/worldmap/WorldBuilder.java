package com.jforbes.javarpg.worldmap;

import com.jforbes.javarpg.entities.*;
import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.items.Item;
import com.jforbes.javarpg.items.ammo.*;
import com.jforbes.javarpg.items.gear.armor.Armor;
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
    private static Location buildSteveHome() {
        Location steveHome = new Location("steve-hut");
        steveHome.setDefaultDescription(
            "Steve's hut is a small tent. " + 
            "He has a cozy hay bed, a stuffy clothes line just outside, " + 
            "and a stuffed troll head hanging on his wall."
        );
        
        Enemy troll = new Enemy("Troll", 30, 18, 6, 15, 130);
        steveHome.addEntity(troll);
        steveHome.addDescription(
            "Steve's hut is a small tent. " + 
            "His new roommate, Jeff, appears to have left a mess on the floor.", 
            game -> steveHome.getContents().contains(troll)
        );
        steveHome.addEntity(new Entity(
            "Blood", 
            "The blood appears to be fresh. " + 
            "Upon close inspection it also appears to be on the troll's claws and teeth", 
            "You see blood spattered on the walls")
        );
        Item skull = new Armor("Skull", 1);
        skull.setDetailedDescription("The skull appears to be from a human. It is on top of a pile of torn up bones and the tattered remains of a blue leather tunic.");
        steveHome.addEntity(skull);

        System.out.println(steveHome.getContents());

        return steveHome;
    }
    private static Location buildVillage(Engine game, World world) {
        Player player = game.getPlayer();

        Location village = new Location("village");
        village.setDefaultDescription("A bustling village full of all your friends and family.");

        ////////// STEVE //////////
        
        NPC bum = new NPC(
            "Steve", 
            "Steve is your good friend from high school. " + 
            "He lives in a hut down the street and is the village's laundry attendand and local kook.", 
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

        ////////// STEVE'S HOME //////////
        
        Location steveHome = buildSteveHome();
        House steveHouse = new House(
            "Steve's hut",

            "Steve's hut is a fairly run-down tent with " + 
            "a busy clothes line hanging out front. \n" + 
            "Steve recently got a new roommate, Jeff. " + 
            "You don't have the heart to tell him, " + 
            "but You don't think Jeff is a good fit " + 
            "for the village because there is a giant mess in his front yard.",
            
            "You can see Steve's hut down the street.",
            steveHome.getLocationId()
        );
        village.addEntity(steveHouse);
        // Steve's hut does not have much in the way of walls
        steveHome.connect(Direction.NORTH, village.getLocationId());
        steveHome.connect(Direction.SOUTH, village.getLocationId());
        steveHome.connect(Direction.EAST, village.getLocationId());
        steveHome.connect(Direction.WEST, village.getLocationId());

        world.add(steveHome.getLocationId(), steveHome);

        ////////// HOME //////////

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

        home.connect(Direction.SOUTH, village.getLocationId());        

        return village;
    }


}
