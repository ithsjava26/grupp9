package combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombatantAttackTest {

    // Damage comes from the chosen strategy and is applied to the target
    @Test
    void attackAppliesStrategyDamageToTarget() {
        Hero hero = new Hero("Hero", 20, 8, 0, 3);
        Enemy enemy = new Enemy("Rat", 20, 2, 0, 2);

        hero.attack(enemy, new HeavyAttack());

        assertEquals(8, enemy.getHp());
    }

    // The target's armour is still applied to the strategy damage
    @Test
    void attackIsReducedByTargetArmour() {
        Hero hero = new Hero("Hero", 20, 8, 0, 3);
        Enemy enemy = new Enemy("Knight", 20, 2, 3, 2);

        hero.attack(enemy, new NormalAttack());

        assertEquals(15, enemy.getHp());
    }
}