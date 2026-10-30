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

    // Healing restores hp
    @Test
    void healRestoresHp() {
        Hero hero = new Hero("Hero", 20, 5, 0, 3);
        hero.takeDamage(10);

        hero.heal(4);

        assertEquals(14, hero.getHp());
    }

    // Healing never goes above the starting hp
    @Test
    void healCannotExceedMaxHp() {
        Hero hero = new Hero("Hero", 20, 5, 0, 3);
        hero.takeDamage(3);

        hero.heal(100);

        assertEquals(20, hero.getHp());
    }

    // A dead combatant cannot be healed back to life
    @Test
    void deadCombatantCannotBeHealed() {
        Hero hero = new Hero("Hero", 20, 5, 0, 3);
        hero.takeDamage(20);

        hero.heal(10);

        assertFalse(hero.isAlive());
    }

    // A negative heal must not reduce hp
    @Test
    void negativeHealDoesNothing() {
        Hero hero = new Hero("Hero", 20, 5, 0, 3);
        hero.takeDamage(5);

        hero.heal(-3);

        assertEquals(15, hero.getHp());
    }


}