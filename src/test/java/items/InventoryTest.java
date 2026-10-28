package items;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @Test
    @DisplayName("Adding an item at weight capacity should not add the item")
    void addingItemAtWeightCapacityShouldNotAddItem() {
        Inventory inventory = new Inventory(10);
        inventory.add(new Weapon("weapon: ", "sword of ultimate ass-kickery", 10, 10));
        inventory.add(new Armour("armour: ", "Boots of maximum tom-foolery", 1, 1));
        assertEquals(1, inventory.items.size());
    }

    @Test
    @DisplayName("Removing an item should remove the item from inventory")
    void discardingItemFromInventoryShouldRemoveItem() {
        Inventory inventory = new Inventory(10);
        Weapon sword = new Weapon("Iron sword of ultimate ass-kickery", "looks sharp", 5, 10);
        inventory.add(sword);
        inventory.add(sword);
        inventory.remove(sword);
        assertEquals(1, inventory.items.size());
    }

    @Test
    @DisplayName("Current weight should return the total weight of items in inventory")
    void currentWeight() {
        Inventory inventory = new Inventory(10);
        Weapon sword = new Weapon("Iron sword of ultimate ass-kickery", " looks sharp", 5, 10);
        inventory.add(sword);
        inventory.add(sword);
        assertEquals(10, inventory.currentWeight());
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Empty inventory should display empty message")
    void emptyInventoryShouldDisplayEmptyMessage() {
        Inventory inventory = new Inventory(10);
        inventory.display();
        assertEquals("Inventory is empty.", outputStreamCaptor.toString().trim());
    }
}
