package game;

import items.Item;
import items.Potion;
import items.LootGenerator;
import java.util.List;
import java.util.Random;
import items.ItemRegistry;
import combat.Hero;
import combat.Enemy;
import items.Inventory;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class BattleControllerTest {

    @Test
    void decliningLootDoesNotAddItemToInventory() {
        Inventory inventory = new Inventory(
                3,
                ItemRegistry.getStartingWeapon(),
                ItemRegistry.getStartingArmour(),
                () -> "1"
        );
        Hero hero = new Hero("Player", 100, 100, 5, 10);
        Enemy enemy = new Enemy("Guard", 1, 1, 0, 1);

        Scanner scanner = new Scanner("2\n2\n");
        BattleController battleController =
                new BattleController(scanner, inventory);

        boolean heroWon = battleController.startBattle(hero, enemy);
        assertTrue(heroWon);

        assertEquals(ItemRegistry.getStartingWeapon(),
                inventory.getEquippedWeapon());
        assertEquals(ItemRegistry.getStartingArmour(),
                inventory.getEquippedArmour());
        assertTrue(inventory.getPotions().isEmpty());

    }

    @Test
    void acceptingLootAddsItemToInventory() {
        Inventory inventory = new Inventory(
                3,
                ItemRegistry.getStartingWeapon(),
                ItemRegistry.getStartingArmour(),
                () -> "2"
        );
        Hero hero = new Hero("Player", 100, 100, 5, 10);
        Enemy enemy = new Enemy("Guard", 1, 1, 0, 1);

        Scanner scanner = new Scanner("2\n1\n");
        List<Item> allItems = ItemRegistry.getAllItems();
        int potionIndex = -1;

        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i) instanceof Potion) {
                potionIndex = i;
                break;
            }
        }

        assertTrue(potionIndex >= 0);

        final int selectedIndex = potionIndex;

        Random fixedRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return selectedIndex;
            }
        };
        LootGenerator lootGenerator = new LootGenerator(fixedRandom);

        BattleController battleController =
                new BattleController(scanner, inventory, lootGenerator);

        boolean heroWon = battleController.startBattle(hero, enemy);
        assertTrue(heroWon);
        assertFalse(inventory.getPotions().isEmpty());

    }
}
