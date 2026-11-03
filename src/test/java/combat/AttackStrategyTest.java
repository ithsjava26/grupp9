package combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttackStrategyTest {

    // Normal attack deals the attacker's base damage
    @Test
    void normalAttackDealsBaseDamage() {
        Hero hero = new Hero("Hero", 20, 8, 0, 2);

        assertEquals(8, new NormalAttack().calculateDamage(hero));
    }

    // Light attack deals 75 percent of base damage
    @Test
    void lightAttackDeals75PercentOfBaseDamage() {
        Hero hero = new Hero("Hero", 20, 8, 0, 2);

        assertEquals(6, new LightAttack().calculateDamage(hero));
    }

    // Heavy attack deals 150 percent of base damage
    @Test
    void heavyAttackDeals150PercentOfBaseDamage() {
        Hero hero = new Hero("Hero", 20, 8, 0, 2);

        assertEquals(12, new HeavyAttack().calculateDamage(hero));
    }

    // Integer division rounds down
    @Test
    void lightAttackRoundsDown() {
        Hero hero = new Hero("Hero", 20, 5, 0, 2);

        assertEquals(3, new LightAttack().calculateDamage(hero));
    }
}