package util;

public class CombatReturn {
    int damage;
    String subject;
    public CombatReturn(int damage, String subject) {
        this.damage = damage;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public int getDamage() {
        return damage;
    }
}
