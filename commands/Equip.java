package commands;

import util.Formatter;
import gameengine.Engine;
import items.Item;
import items.gear.armor.Armor;
import items.gear.tools.Bow;
import items.gear.tools.Weapon;
import player.Player;

public class Equip extends Use {
    public String getMan() {
        return "Equip an item. Takes an armor or a weapon.";
    }

    @Override
    protected void interact(Engine game, Item item) {
        Player player = game.getPlayer();
        if(item instanceof Armor) {
            player.setEquipment((Armor) item);
            game.render("You don the " + item.getName().toLowerCase() + " and now look very cool");
        } else if(item instanceof Weapon) {
            player.setEquipment((Weapon) item);
            if(item instanceof Bow) {
                game.render("You equip the " + item.getName().toLowerCase() + ". Do you have a permit to shoot that?");
            } else {
                game.render("You equip the " + item.getName().toLowerCase() + " and now look kinda funny.");
            }
        } else {
            game.render("You cannot equip a" + Formatter.needsAn(item.getName()) + item.getName().toLowerCase() + ".");
        }
    }
}
