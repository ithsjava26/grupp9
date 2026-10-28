package combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractCombatantTest {

    // Incoming damage is reduced by armour
    @Test
    void damageTakenShouldBeReducedByArmour() {
        Enemy enemy = new Enemy("Troll", 20, 5, 3, 1);

        enemy.takeDamage(10);

        assertEquals(13, enemy.getHp());
    }

    // Armour larger than damage must never heal the target
    @Test
    void negativeDamageShouldNotHealCombatant() {
        Enemy enemy = new Enemy("Knight", 20, 5, 10, 1);

        enemy.takeDamage(3);

        assertEquals(20, enemy.getHp());
    }

    // Hp stops at 0 and never goes negative
    @Test
    void hpCanNeverBeBelowZero() {
        Enemy enemy = new Enemy("Rat", 5, 1, 0, 1);

        enemy.takeDamage(10);

        assertEquals(0, enemy.getHp());
    }

    // isAlive returns true while hp is above 0
    @Test
    void whileHpAboveZero_isAliveTrue() {
        Hero hero = new Hero("Hero", 5, 3, 0, 2);

        hero.takeDamage(4);

        assertTrue(hero.isAlive());
    }

    // Exactly 0 hp counts as dead
    @Test
    void whileHpAtZero_isAliveFalse() {
        Hero hero = new Hero("Hero", 5, 3, 0, 2);

        hero.takeDamage(5);

        assertFalse(hero.isAlive());
    }
}