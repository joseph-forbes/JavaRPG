package com.jforbes.javarpg.commands;

import com.jforbes.javarpg.gameengine.Engine;
import com.jforbes.javarpg.items.gear.armor.DefaultArmor;
import com.jforbes.javarpg.items.gear.armor.NoArmor;
import com.jforbes.javarpg.util.InventoryFinder;

public class WhyAreYouLikeThis implements Command {
    public String getMan() {
        return "Toggles play between SFW mode (requires armor worn at all times) and NSFW mode (allows exposure to the elements).";
    }

    @Override
    public void execute(Engine game, String[] args) {
        game.isNSFW = !game.isNSFW;
        game.render("NSFW mode " + (game.isNSFW ? "enabled" : "disabled") + ".");

        if(!game.isNSFW && game.getPlayer().getArmor() instanceof NoArmor) {
            DefaultArmor armor = (DefaultArmor) InventoryFinder.find(game.getPlayer().getInventory(), DefaultArmor.class);
            if(armor == null) armor = new DefaultArmor();
            game.getPlayer().setEquipment(armor);
        }
    }


}
