package combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class TurnOrderTest {

    private final TurnOrder turnOrder = new TurnOrder();

    // Higher speed acts first
    @Test
    void fasterHeroActsFirst() {
        Hero hero = new Hero("Hero", 10, 5, 0, 5);
        Enemy enemy = new Enemy("Rat", 10, 3, 0, 2);

        assertSame(hero, turnOrder.whoActsFirst(hero, enemy));
    }

    @Test
    void fasterEnemyActsFirst() {
        Hero hero = new Hero("Hero", 10, 5, 0, 2);
        Enemy enemy = new Enemy("Rat", 10, 3, 0, 5);

        assertSame(enemy, turnOrder.whoActsFirst(hero, enemy));
    }

    // Equal speed: the hero goes first
    @Test
    void heroActsFirstOnEqualSpeed() {
        Hero hero = new Hero("Hero", 10, 5, 0, 3);
        Enemy enemy = new Enemy("Rat", 10, 3, 0, 3);

        assertSame(hero, turnOrder.whoActsFirst(hero, enemy));
    }
}