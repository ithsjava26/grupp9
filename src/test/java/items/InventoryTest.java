package items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Weapon starterWeapon;
    private Armour starterArmour;
    private Weapon upgradeWeapon;
    private Armour upgradeArmour;

    @BeforeEach
    void setUp() {
        starterWeapon = new Weapon("Starter Dagger", "Basic blade", 1, 3);
        starterArmour = new Armour("Starter Clothes", "Basic cloth", 1, 1);
        upgradeWeapon = new Weapon("Steel Sword", "Sharp steel", 3, 10);
        upgradeArmour = new Armour("Iron Plate", "Heavy iron", 8, 8);
    }


    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
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
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour);
        provideInput("2\n");
        Weapon newWeapon = ItemRegistry.getWeapons().get(1);

        inventory.handleWeaponDrop(newWeapon);

        assertEquals(newWeapon, inventory.getEquippedWeapon());
    }

    @Test
    @DisplayName("should keep current weapon when option one selected")
    void shouldKeepCurrentWeaponWhenOptionOneSelected() {
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour);
        provideInput("1\n");
        Weapon newWeapon = ItemRegistry.getWeapons().get(1);

        inventory.handleWeaponDrop(newWeapon);

        assertEquals(starterWeapon, inventory.getEquippedWeapon());
    }

    @Test
    @DisplayName("should swap equipped armour when option two selected")
    void shouldSwapEquippedArmourWhenOptionTwoSelected() {
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour);
        provideInput("2\n");
        Armour newArmour = ItemRegistry.getArmours().get(1);

        inventory.handleArmourDrop(upgradeArmour);

        assertEquals(upgradeArmour, inventory.getEquippedArmour());
    }

    @Test
    @DisplayName("should enforce potion capacity limit")
    void shouldEnforcePotionCapacityLimit() {
        Inventory inventory = new Inventory(3, starterWeapon, starterArmour);
        Potion potion = ItemRegistry.getPotions().getFirst();

        assertTrue(inventory.addPotion(potion));
        assertTrue(inventory.addPotion(potion));
        assertTrue(inventory.addPotion(potion));
        assertFalse(inventory.addPotion(potion), "Adding a 4th potion to a capacity-3 pouch should return false.");
        assertEquals(3, inventory.getPotions().size());
    }
}
