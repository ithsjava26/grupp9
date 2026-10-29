package combat;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BattleTest {

    // Faster hero attacks first, then the enemy hits back
    @Test
    void bothSidesAttackInSpeedOrder() {
        Hero hero = new Hero("Hero", 20, 8, 0, 5);
        Enemy enemy = new Enemy("Rat", 20, 2, 0, 2);
        Battle battle = new Battle(hero, enemy);

        List<TurnEvent> events = battle.playTurn(new NormalAttack());

        assertEquals(2, events.size());
        assertEquals(new TurnEvent("Hero", "Rat", 8, 12), events.get(0));
        assertEquals(new TurnEvent("Rat", "Hero", 2, 18), events.get(1));
    }

    // Faster enemy attacks first, so the events come in that order
    @Test
    void fasterEnemyAttacksFirst() {
        Hero hero = new Hero("Hero", 20, 8, 0, 2);
        Enemy enemy = new Enemy("Rat", 20, 2, 0, 5);
        Battle battle = new Battle(hero, enemy);

        List<TurnEvent> events = battle.playTurn(new NormalAttack());

        assertEquals("Rat", events.get(0).attacker());
        assertEquals("Hero", events.get(1).attacker());
    }

    // A dead enemy does not get to strike back
    @Test
    void deadEnemyDoesNotAttackBack() {
        Hero hero = new Hero("Hero", 20, 8, 0, 5);
        Enemy enemy = new Enemy("Rat", 5, 2, 0, 2);
        Battle battle = new Battle(hero, enemy);

        List<TurnEvent> events = battle.playTurn(new NormalAttack());

        assertEquals(1, events.size());
        assertTrue(battle.isOver());
    }

    // The hero can die too, and then does not strike back
    @Test
    void deadHeroDoesNotAttackBack() {
        Hero hero = new Hero("Hero", 2, 8, 0, 2);
        Enemy enemy = new Enemy("Rat", 20, 5, 0, 5);
        Battle battle = new Battle(hero, enemy);

        List<TurnEvent> events = battle.playTurn(new NormalAttack());

        assertEquals(1, events.size());
        assertTrue(battle.isOver());
    }

    // Reported damage is what got through after armour
    @Test
    void eventReportsDamageAfterArmour() {
        Hero hero = new Hero("Hero", 20, 8, 0, 5);
        Enemy enemy = new Enemy("Knight", 20, 2, 3, 2);
        Battle battle = new Battle(hero, enemy);

        List<TurnEvent> events = battle.playTurn(new NormalAttack());

        assertEquals(5, events.get(0).damage());
    }

    // Once the fight is over, no more attacks happen
    @Test
    void noEventsAfterBattleIsOver() {
        Hero hero = new Hero("Hero", 20, 8, 0, 5);
        Enemy enemy = new Enemy("Rat", 5, 2, 0, 2);
        Battle battle = new Battle(hero, enemy);
        battle.playTurn(new NormalAttack());

        assertTrue(battle.playTurn(new NormalAttack()).isEmpty());
    }
}