package items;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRegistryTest {

    @Test
    @DisplayName("Should populate all item collections from the CSV file")
    void shouldPopulateAllItemCollectionsFromCsv() {
        // Act
        List<Weapon> weapons = ItemRegistry.getWeapons();
        List<Armour> armours = ItemRegistry.getArmours();
        List<Potion> potions = ItemRegistry.getPotions();
        List<Item> allItems = ItemRegistry.getAllItems();

        // Assert
        assertFalse(weapons.isEmpty(), "Weapons list should contain parsed CSV entries");
        assertFalse(armours.isEmpty(), "Armours list should contain parsed CSV entries");
        assertFalse(potions.isEmpty(), "Potions list should contain parsed CSV entries");

        assertEquals(
                weapons.size() + armours.size() + potions.size(),
                allItems.size(),
                "Total item count should equal the sum of weapons, armours, and potions"
        );
    }

    @Test
    @DisplayName("Should correctly map CSV fields into Record components")
    void shouldCorrectlyMapCsvFieldsToRecordComponents() {
        // Act
        Weapon startingWeapon = ItemRegistry.getStartingWeapon();

        // Assert
        assertNotNull(startingWeapon, "Starting weapon should not be null");
        assertEquals("Rusty Dagger", startingWeapon.name());
        assertEquals("Chipped blade suitable for stabbing rats.", startingWeapon.description());
        assertEquals(1, startingWeapon.weight());
        assertEquals(3, startingWeapon.damage());
    }

    @Test
    @DisplayName("Should guarantee starting weapon and armour exist")
    void shouldGuaranteeStartingGearExists() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            Weapon weapon = ItemRegistry.getStartingWeapon();
            Armour armour = ItemRegistry.getStartingArmour();

            assertNotNull(weapon.name());
            assertNotNull(armour.name());
        }, "Starting gear accessor methods should not throw an exception");
    }
}