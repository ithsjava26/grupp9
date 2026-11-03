package combat;

public class HeavyAttack implements AttackStrategy {
    @Override
    public int calculateDamage(Combatant attacker) {
        return attacker.getDamage() * 3 / 2;
    }
}