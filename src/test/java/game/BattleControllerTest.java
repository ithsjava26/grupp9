package game;

import combat.Hero;
import combat.Enemy;
import items.Inventory;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class BattleControllerTest {

    @Test
    void decliningLootDoesNotAddItemToInventory() {
        Inventory inventory = new Inventory(100);
        Hero hero = new Hero("Player", 100, 100, 5, 10);
        Enemy enemy = new Enemy("Guard", 1, 1, 0, 1);

        Scanner scanner = new Scanner("2\n2\n");
        BattleController battleController =
                new BattleController(scanner, inventory);

        boolean heroWon = battleController.startBattle(hero, enemy);
        assertTrue(heroWon);
        assertEquals(0, inventory.currentWeight());
    }

    @Test
    void acceptingLootAddsItemToInventory() {
        Inventory inventory = new Inventory(100);
        Hero hero = new Hero("Player", 100, 100, 5, 10);
        Enemy enemy = new Enemy("Guard", 1, 1, 0, 1);

        Scanner scanner = new Scanner("2\n1\n");
        BattleController battleController =
                new BattleController(scanner, inventory);

        boolean heroWon = battleController.startBattle(hero, enemy);
        assertTrue(heroWon);
        assertTrue(inventory.currentWeight() > 0);
    }
}
