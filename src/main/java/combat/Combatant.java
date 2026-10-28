package combat;

public interface Combatant {
    String getName();

    int getHp();

    int getDamage();

    int getArmour();

    int getSpeed();

    boolean isAlive();

    void takeDamage(int amount);

}