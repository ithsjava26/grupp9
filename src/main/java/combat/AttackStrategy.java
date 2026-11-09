package combat;

public interface AttackStrategy {
    int calculateDamage(Combatant attacker);

    int accuracy();

    default boolean hasPriority() {
        return false;
    }
}
