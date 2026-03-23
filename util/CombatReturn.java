package util;

public class CombatReturn {
    int damage;
    int remainingHP;
    String subject;
    public CombatReturn(int damage, String subject, int remainingHP) {
        this.damage = damage;
        this.subject = subject;
        this.remainingHP = remainingHP;
    }

    public String getSubject() {
        return subject;
    }

    public int getDamage() {
        return damage;
    }

    public int getRemainingHP() {
        return remainingHP;
    }
}
