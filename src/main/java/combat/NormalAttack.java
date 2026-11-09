package combat;

public class NormalAttack implements AttackStrategy {
    @Override
    public int calculateDamage(Combatant attacker) {
        return attacker.getDamage();
    }

    @Override
    public int accuracy() {
        return 90;
    }
}