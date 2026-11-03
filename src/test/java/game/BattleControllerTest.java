package game;

import items.Armour;
import items.Weapon;
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

        Scanner scanner = new Scanner("1\n2\n");
        BattleController battleController =
                new BattleController(
                        scanner, inventory);

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

        Scanner scanner = new Scanner("1\n1\n");
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


    @Test
    void equippedWeaponUpdatesHeroDamage() {
        Hero hero = new Hero("Player", 100, 15, 5, 10);

        Inventory inventory = new Inventory(
                3,
                ItemRegistry.getStartingWeapon(),
                ItemRegistry.getStartingArmour(),
                () -> "2"
        );
        int baseDamage = hero.getDamage();

        hero.equipWeapon(inventory.getEquippedWeapon().damage());

        assertEquals(
                baseDamage + inventory.getEquippedWeapon().damage(),
                hero.getDamage()
        );
    }


    @Test
    void acceptingWeaponLootUpdatesHeroDamage() {

        Inventory inventory = new Inventory(
                3,
                ItemRegistry.getStartingWeapon(),
                ItemRegistry.getStartingArmour(),
                () -> "2"
        );

        Hero hero = new Hero("Player", 100, 100, 5, 10);
        Enemy enemy = new Enemy("Guard", 1, 1, 0, 1);

        hero.equipWeapon(inventory.getEquippedWeapon().damage());

        int startingDamage = hero.getDamage();

        List<Item> allItems = ItemRegistry.getAllItems();
        int weaponIndex = -1;

        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i) instanceof Weapon weapon
                    && weapon.damage() != inventory.getEquippedWeapon().damage()) {
                weaponIndex = i;
                break;
            }
        }

        assertTrue(weaponIndex >= 0);

        final int selectedIndex = weaponIndex;

        Random lootRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return selectedIndex;
            }
        };

        LootGenerator lootGenerator = new LootGenerator(lootRandom);
        Scanner scanner = new Scanner("1\n1\n");

        BattleController controller = new BattleController(
                scanner, inventory, lootGenerator
        );

        assertTrue(controller.startBattle(hero, enemy));

        assertEquals(
                100 + inventory.getEquippedWeapon().damage(),
                hero.getDamage()
        );

        assertNotEquals(startingDamage, hero.getDamage());
    }


    @Test
    void acceptingArmourLootUpdatesHeroDefence() {
        Inventory inventory = new Inventory(
                3,
                ItemRegistry.getStartingWeapon(),
                ItemRegistry.getStartingArmour(),
                () -> "2"
        );

        Hero hero = new Hero("Player", 100, 100, 5, 10);
        Enemy enemy = new Enemy("Guard", 1, 1, 0, 1);

        hero.equipArmour(inventory.getEquippedArmour().defence());
        int startingArmour = hero.getArmour();

        List<Item> allItems = ItemRegistry.getAllItems();
        int armourIndex = -1;

        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i) instanceof Armour armour
                    && armour.defence() != inventory.getEquippedArmour().defence()) {
                armourIndex = i;
                break;
            }
        }

        assertTrue(armourIndex >= 0);

        final int selectedIndex = armourIndex;

        Random lootRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return selectedIndex;
            }
        };

        LootGenerator lootGenerator = new LootGenerator(lootRandom);
        Scanner scanner = new Scanner("1\n1\n");

        BattleController controller = new BattleController(
                scanner, inventory, lootGenerator
        );

        assertTrue(controller.startBattle(hero, enemy));

        assertEquals(
                5 + inventory.getEquippedArmour().defence(),
                hero.getArmour()
        );

        assertNotEquals(startingArmour, hero.getArmour());
    }

}
