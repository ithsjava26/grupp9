package items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Weapon starterWeapon;
    private Armour starterArmour;
    private Weapon upgradeWeapon;
    private Armour upgradeArmour;
    private Potion testPotion;

    @BeforeEach
    void setUp() {
        starterWeapon = new Weapon("Starter Dagger", "Basic blade", 1, 3);
        starterArmour = new Armour("Starter Clothes", "Basic cloth", 1, 1);
        upgradeWeapon = new Weapon("Steel Sword", "Sharp steel", 3, 10);
        upgradeArmour = new Armour("Iron Plate", "Heavy iron", 8, 8);
        testPotion = new Potion("Health Potion", "Heals 20 HP", 1, 20);
    }


    @Test
    @DisplayName("should initialize with starting weapon and armour")
    void shouldInitializeWithStartingWeaponAndArmour() {
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour);
        assertEquals(starterWeapon, inventory.getEquippedWeapon());
        assertEquals(starterArmour, inventory.getEquippedArmour());
        assertTrue(inventory.getPotions().isEmpty());
    }

    @Test
    @DisplayName("should swap equipped weapon when option two selected")
    void shouldSwapEquippedWeaponWhenOptionTwoSelected() {
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour, () -> "2");

        inventory.handleWeaponDrop(upgradeWeapon);

        assertEquals(upgradeWeapon, inventory.getEquippedWeapon());
    }

    @Test
    @DisplayName("should keep current weapon when option one selected")
    void shouldKeepCurrentWeaponWhenOptionOneSelected() {
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour, () -> "1");

        inventory.handleWeaponDrop(upgradeWeapon);

        assertEquals(starterWeapon, inventory.getEquippedWeapon());
    }

    @Test
    @DisplayName("should swap equipped armour when option two selected")
    void shouldSwapEquippedArmourWhenOptionTwoSelected() {
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour, () -> "2");

        inventory.handleArmourDrop(upgradeArmour);

        assertEquals(upgradeArmour, inventory.getEquippedArmour());
    }

    @Test
    @DisplayName("should enforce potion capacity limit")
    void shouldEnforcePotionCapacityLimit() {
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour);

        assertTrue(inventory.addPotion(testPotion));
        assertTrue(inventory.addPotion(testPotion));
        assertTrue(inventory.addPotion(testPotion));
        assertFalse(inventory.addPotion(testPotion), "Adding a 4th potion to a capacity-3 pouch should return false.");
        assertEquals(3, inventory.getPotions().size());
    }

    @Test
    @DisplayName("should remove existing potion from pouch")
    void shouldRemoveExistingPotion() {
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour);
        inventory.addPotion(testPotion);
        
        boolean wasRemoved = inventory.usePotion(testPotion);
        
        assertTrue(wasRemoved, "Removing an existing potion should return true");
        assertTrue(inventory.getPotions().isEmpty(), "Potion pouch should be empty after removal");
    }
}
