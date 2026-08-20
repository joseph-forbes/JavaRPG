package com.jforbes.javarpg.commands;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.items.gear.armor.DefaultArmor;
import com.jforbes.javarpg.items.gear.armor.NoArmor;
import com.jforbes.javarpg.items.gear.tools.DefaultWeapon;
import com.jforbes.javarpg.player.Player;
import com.jforbes.javarpg.util.InventoryFinder;

public class Unequip implements Command {
    @Override
    public String getMan() {
        return "Unequip either armor or weapon. Can either be \"unequip armor\" or \"unequip weapon\"";
    }

    @Override
    public void execute(Engine game, String[] args) {
        if(args.length == 0) {
            game.render("Please say either \"unequip armor\" or \"unequip weapon\"");
            return;
        }

        Player player = game.getPlayer();
        switch (args[0].toLowerCase()) {
            case "armor":
                if(player.getArmor() instanceof DefaultArmor && !game.isNSFW) {
                    game.render("You are not wearing any armor, just a leather tunic. To enable nudity, type \"NSFW\"");
                } else if(player.getArmor() instanceof DefaultArmor) {
                    game.render("You remove your leather tunic. Nobody wants to be around you anymore and you are much more susceptible to the elements.");
                    player.setEquipment(new NoArmor()); // No armor
                } else if(player.getArmor() instanceof NoArmor) {
                    game.render("You attempt to remove your skin but cannot seem to. Maybe it's best to keep it on for now.");
                } else {
                    game.render("You remove your " + player.getArmor().getName().toLowerCase() + " and don a simple leather tunic.");
                    DefaultArmor armor = (DefaultArmor) InventoryFinder.find(game.getPlayer().getInventory(), DefaultArmor.class);
                    if(armor == null) armor = new DefaultArmor();
                    game.getPlayer().setEquipment(armor);
                }
            break;
            case "weapon":
                if(player.getWeapon() instanceof DefaultWeapon) {
                    game.render("You aren't holding a weapon.");
                } else {
                    game.render("You place your " + player.getWeapon().getName().toLowerCase() + " in your satchel and prepare your fists for a fight.");
                    player.setEquipment(new DefaultWeapon());
                }
            break;
            default:
                game.render("Please say either \"unequip armor\" or \"unequip weapon\"");
                break;
        }
    }
}
