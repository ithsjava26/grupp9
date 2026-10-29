package combat;

public interface Combatant {
    String getName();

    int getHp();

    int getDamage();

    int getArmour();

    int getSpeed();

    boolean isAlive();

    void takeDamage(int amount);

    // Deals damage to the target using the chosen attack strategy
    default void attack(Combatant target, AttackStrategy strategy) {
        target.takeDamage(strategy.calculateDamage(this));
    }

}