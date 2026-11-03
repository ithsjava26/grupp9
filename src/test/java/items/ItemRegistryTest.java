package items;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRegistryTest {

    @Test
    @DisplayName("Should populate all item collections from the CSV file")
    void shouldPopulateAllItemCollectionsFromCsv() {
        List<Weapon> weapons = ItemRegistry.getWeapons();
        List<Armour> armours = ItemRegistry.getArmours();
        List<Potion> potions = ItemRegistry.getPotions();
        List<Item> allItems = ItemRegistry.getAllItems();

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
        Weapon startingWeapon = ItemRegistry.getStartingWeapon();

        assertNotNull(startingWeapon, "Starting weapon should not be null");
        assertEquals("Peg Leg", startingWeapon.name());
        assertEquals("An old wooden prosthetic leg.. it kind of smells.", startingWeapon.description());
        assertEquals(1, startingWeapon.damage());
    }

    @Test
    @DisplayName("Should guarantee starting weapon and armour exist")
    void shouldGuaranteeStartingGearExists() {
        assertDoesNotThrow(() -> {
            Weapon weapon = ItemRegistry.getStartingWeapon();
            Armour armour = ItemRegistry.getStartingArmour();

            assertNotNull(weapon.name());
            assertNotNull(armour.name());
        }, "Starting gear accessor methods should not throw an exception");
    }
}
