package commands;

import gameengine.Engine;
import player.Player;
import util.enums.Stats;

public class Statsheet implements Command {

    @Override
    public void execute(Engine game, String[] args) {
        Player player = game.getPlayer();

        game.render("Name: " + player.getName());
        game.render("Level: " + player.getLvl());
        game.render("HP: " + player.getStat(Stats.HP));
        game.render("XP: " + player.getXp() + " / " + player.getXpToNextLevel());

        game.render();
        game.render("Weapon: " + player.getWeapon().getName());
        game.render("Attack Damage: " + player.getStat(Stats.DAMAGE));
        game.render("Attack Accuracy Bonus: " + player.getStat(Stats.DAMAGE_BONUS));
        
        game.render();
        game.render("Armor: " + player.getArmor().getName());
        game.render("AC (defense): " + player.getStat(Stats.AC));
    }

    @Override
    public String getMan() {
        return "Examine your defense and attack damage, along with equipped weapons and armor";
    }
    
}
