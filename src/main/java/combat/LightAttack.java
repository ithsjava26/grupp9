package combat;

public class LightAttack implements AttackStrategy {
    @Override
    public int calculateDamage(Combatant attacker) {
        return attacker.getDamage() * 3 / 4;
    }
}