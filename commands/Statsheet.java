package commands;

import gameengine.Engine;
import player.Player;
import util.Formatter;
import util.enums.Stats;

public class Statsheet implements Command {

    @Override
    public void execute(Engine game, String[] args) {
        // TODO Auto-generated method stub
        Player player = game.getPlayer();

        game.render("Name: " + player.getName());
        game.render("HP: " + player.getStat(Stats.HP));

        game.render();
        game.render("Weapon: " + player.getWeapon().getName());
        game.render("Attack damage: " + player.getStat(Stats.DAMAGE));
        game.render("Attack accuracy bonus: " + player.getStat(Stats.DAMAGE_BONUS));
        
        game.render();
        game.render("Armor: " + player.getArmor().getName());
        game.render("AC (defense): " + player.getStat(Stats.AC));
    }

    @Override
    public String getMan() {
        return "Examine your defense and attack damage, along with equipped weapons and armor";
    }
    
}
