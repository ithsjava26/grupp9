package combat;

public class LightAttack implements AttackStrategy {
    @Override
    public int calculateDamage(Combatant attacker) {
        return attacker.getDamage() * 3 / 4;
    }

    @Override
    public int accuracy() {
        return 100;
    }

    @Override
    public boolean hasPriority() {
        return true;
    }
}