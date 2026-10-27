package combat;

public interface Combatant {
    String getName();

    int getHp();

    int getDamage();

    int getArmor();

    int getSpeed();

    boolean isAlive();

    void takeDamage(int amount);

}