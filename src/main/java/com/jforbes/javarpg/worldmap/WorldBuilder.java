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

        buildVillage(game, world);

        return world;
    }
    private static void buildVillage(Engine game, World world) {
        Location neighborhood = buildNeighborhood(game, world);
        Location townSquare = buildTownSquare(game, world);

        World.connect(neighborhood, townSquare, Direction.NORTH);
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

        return steveHome;
    }
    
    private static Location buildCityHall(World world) {
        Location cityHall = new Location("city-hall-entrance");
        cityHall.setDefaultDescription(
            "You've never gotten used to just how grand this building is. " + 
            "This room alone feels bigger than the building it is within " + 
            "but there is still a north, east, and west wing"
        );

        Location eastWing = new Location("city-hall-east-wing");
        eastWing.setDefaultDescription(
            "The east wing of city hall is the most popular place in town. " +
            "Every week, everyone gathers for the town potluck." 
        );
        eastWing.addEntity(new Entity(
            "Tables", 
            "Each table is covered in a fresh tablecloth.", 
            "You see many tables around the room.")
        );
        eastWing.addEntity(new Entity("Sink", 
            "The sink is well maintained. " + 
            "Fresh dishes from last week's potluck are drying off to the side.", 
            "You see a sink on the far end of the room", 
            "The only person in the village who knows how to work this sink is Dale. " + 
            "You feel ashamed for even considering use of this sink a potential option."
        ));
        World.connect(cityHall, eastWing, Direction.EAST);
        world.add(eastWing);

        Location westWing = new Location("city-hall-west-wing");
        westWing.setDefaultDescription("Your mayor, Wilbur, sleeps here");
        westWing.addEntity(new Entity("Bed", "This is where Wilbur sleeps"));
        westWing.addEntity(new Entity(
            "Clothes", 
            "It seems Wilbur hasn't cleaned his room in a while. The clothes are strewn about across the floor.", 
            "There's a pile of clothes on the bed")
        );
        World.connect(cityHall, westWing, Direction.WEST);
        world.add(westWing);

        Location northWing = new Location("city-hall-north-wing");
        northWing.setDefaultDescription(
            "The north wing is the only part of city hall not cleaned by Dale " + 
            "as no one but the mayor is meant to be in here. \n" + 
            "You aren't meant to be here either but nobody stopped you."
        );
        northWing.addEntity(new Entity(
            "Desk", 
            "The table has a thin film of dust atop it"
        ));
        northWing.addEntity(new Entity(
            "Chair", 
            "The chair isn't at the desk. You notice it has wheels on the bottom", 
            "You see a chair off to the side of the room.", 
            "You roll around on the chair. Whee!"
        ));
        World.connect(cityHall, northWing, Direction.NORTH);
        world.add(northWing);

        Location upstairs = new Location("city-hall-upstairs");
        upstairs.setDefaultDescription(
            "You feel like the upstairs was an afterthought added only to allow for " + 
            "such an excellent staircase. All there is is a balcony looking out upon the entrance"
        );
        Location balcony = new Location("city-hall-balcony");
        balcony.setDefaultDescription(
            "The village is run-down at best but looking from up here, " + 
            "it's the most beautiful place you've ever seen."
        );
        balcony.addEntity(new Entity(
            "Village",
            "Your troubles seem insignificant at this distance...",
            "You see the village at a distance."
        ));

        world.add(balcony);
        balcony.connect(Direction.SOUTH, world.getIdByString("town-square"));
        World.connect(upstairs, balcony, Direction.SOUTH);

        House staircaseUp = new House(
            "Staircase", 
            "An ornate design and the pride of the city. " + 
            "The spiral leads to a beautiful opening into the second floor", 
            "You see the base of a spiral staircase.",
            upstairs.getLocationId()
        );
        House staircaseDown = new House(
            "Staircase", 
            "An ornate design and the pride of the city. " + 
            "The spiral leads down to a grand entryway.", 
            "You see the top of a spiral staircase.",
            cityHall.getLocationId()
        );
        cityHall.addEntity(staircaseUp);
        upstairs.addEntity(staircaseDown);
        world.add(upstairs);

        return cityHall;
    }
    

    private static Location buildTownSquare(Engine game, World world) {
        Location townSquare = new Location("town-square");
        townSquare.setDefaultDescription("A bustling village full of all your friends and family.");
        world.add(townSquare); // Add to world early because cityHall connects to it during instantiation

        Location cityHall = buildCityHall(world);
        House cityHallEntity = new House(
            "City Hall", 

            "The only time people really ever come here is the weekly city potluck. " + 
            "That being said, it is a pretty nice looking building with classical " + 
            "architecture and a nice garden.",

            "You see City Hall just ahead.",
            cityHall.getLocationId()
        );
        townSquare.addEntity(cityHallEntity);
        cityHall.connect(Direction.SOUTH, townSquare);
        world.add(cityHall);

        return townSquare;
    }
    private static Location buildNeighborhood(Engine game, World world) {
        Player player = game.getPlayer();

        Location neighborhood = new Location("neighborhood");
        neighborhood.setDefaultDescription("A bustling village full of all your friends and family.");
        world.add(neighborhood);

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

        neighborhood.addEntity(bum);

        ////////// STEVE'S HOME //////////
        
        Location steveHut = buildSteveHome();
        House steveHutEntity = new House(
            "Steve's hut",

            "Steve's hut is a fairly run-down tent with " + 
            "a busy clothes line hanging out front. \n" + 
            "Steve recently got a new roommate, Jeff. " + 
            "You don't have the heart to tell him, " + 
            "but You don't think Jeff is a good fit " + 
            "for the village because there is a giant mess in his front yard.",
            
            "You can see Steve's hut down the street.",
            steveHut.getLocationId()
        );
        neighborhood.addEntity(steveHutEntity);
        // Steve's hut does not have much in the way of walls
        steveHut.connect(Direction.NORTH, neighborhood.getLocationId());
        steveHut.connect(Direction.SOUTH, neighborhood.getLocationId());
        steveHut.connect(Direction.EAST, neighborhood.getLocationId());
        steveHut.connect(Direction.WEST, neighborhood.getLocationId());

        world.add(steveHut);

        ////////// HOME //////////

        Location home = buildHome();
        House homeEntity = new House(
            "home", 
            "You've lived here as long as you can remember", 
            "You see your home", 
            home.getLocationId()
        );
        neighborhood.addEntity(homeEntity);
        player.setLocation(home.getLocationId());
        world.add(home);

        home.connect(Direction.SOUTH, neighborhood.getLocationId());        

        return neighborhood;
    }


}
