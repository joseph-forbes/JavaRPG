package commands;

import util.Formatter;
import gameengine.Engine;
import items.Item;
import items.gear.armor.Armor;
import items.gear.tools.Bow;
import items.gear.tools.Weapon;

public class Equip extends Use {
    public String getMan() {
        return "Equip an item. Takes an armor or a weapon.";
    }

    @Override
    protected void interact(Engine game, Item item) {
        if(item instanceof Armor) {
            equipArmor(game, (Armor) item);
        } else if(item instanceof Weapon) {
            equipWeapon(game, (Weapon) item);
        } else {
            game.render("You cannot equip a" + Formatter.needsAn(item.getName()) + item.getName().toLowerCase() + ".");
        }
    }
    protected void equipArmor(Engine game, Armor armor) {
        game.getPlayer().setEquipment((Armor) armor);
        game.render("You don the " + armor.getName().toLowerCase() + " and now look very cool");
    }
    protected void equipWeapon(Engine game, Weapon item) {
        game.getPlayer().setEquipment((Weapon) item);
            if(item instanceof Bow) {
                game.render("You equip the " + item.getName().toLowerCase() + ". Do you have a permit to shoot that?");
            } else {
                game.render("You equip the " + item.getName().toLowerCase() + " and now look kinda funny.");
            }
    }
}
