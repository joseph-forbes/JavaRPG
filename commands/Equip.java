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
            equipArmor(player, (Armor) item);
        } else if(item instanceof Weapon) {
            equipWeapon(player, (Weapon) item);
        } else {
            game.render("You cannot equip a" + Formatter.needsAn(item.getName()) + item.getName().toLowerCase() + ".");
        }
    }
    protected void equipArmor(Player player, Armor armor) {
        player.setEquipment((Armor) armor);
        System.out.println("You don the " + armor.getName().toLowerCase() + " and now look very cool");
    }
    protected void equipWeapon(Player player, Weapon item) {
        player.setEquipment((Weapon) item);
            if(item instanceof Bow) {
                System.out.println("You equip the " + item.getName().toLowerCase() + ". Do you have a permit to shoot that?");
            } else {
                System.out.println("You equip the " + item.getName().toLowerCase() + " and now look kinda funny.");
            }
    }
}
