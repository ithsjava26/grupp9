package items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Weapon equippedWeapon;
    private Armour equippedArmour;
    private final List<Potion> potions;
    private final int maxPotions;

    public Inventory(int maxPotions, Weapon startingWeapon, Armour startingArmour) {
        this.potions = new ArrayList<>();
        this.maxPotions = maxPotions;
        this.equippedWeapon = startingWeapon;
        this.equippedArmour = startingArmour;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armour getEquippedArmour() {
        return equippedArmour;
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public void handleWeaponDrop(Weapon newWeapon) {
        IO.println("\n--- LOOT FOUND ---");
        IO.println("New Weapon: " + newWeapon.name() + " (+" + newWeapon.damage() + " DMG) - " + newWeapon.description());
        IO.println("Currently Equipped: " + equippedWeapon.name() + " (+" + equippedWeapon.damage() + " DMG)");
        IO.println("[1] Keep current (" + equippedWeapon.name() + ")");
        IO.println("[2] Take new and discard current (" + newWeapon.name() + ")");
        IO.println("Choose an option (1-2): ");

        int choice = Integer.parseInt(IO.readln());
        if (choice == 2) {
            IO.println("You discarded " + equippedWeapon.name() + " and equipped " + newWeapon.name() + ".");
            equippedWeapon = newWeapon;
        } else {
            IO.println("You left " + newWeapon.name() + " behind.");
        }
    }

    public void handleArmourDrop(Armour newArmour) {
        IO.println("\n--- LOOT FOUND ---");
        IO.println("New Armour: " + newArmour.name() + " (+" + newArmour.defence() + " DEF) - " + newArmour.description());
        IO.println("Currently Equipped: " + equippedArmour.name() + " (+" + equippedArmour.defence() + " DEF)");
        IO.println("[1] Keep current (" + equippedArmour.name() + ")");
        IO.println("[2] Take new and discard current (" + newArmour.name() + ")");
        IO.println("Choose an option (1-2): ");

        int choice = Integer.parseInt(IO.readln());
        if (choice == 2) {
            IO.println("You discarded " + equippedArmour.name() + " and equipped " + newArmour.name() + ".");
            equippedArmour = newArmour;
        } else {
            IO.println("You left " + newArmour.name() + " behind.");
        }
    }

    public boolean addPotion(Potion potion) {
        if (potions.size() >= maxPotions) {
            IO.println("Your potion pouch is full! Cannot carry " + potion.name() + ".");
            return false;
        }
        potions.add(potion);
        IO.println("Added to potion pouch: " + potion.name());
        return true;
    }

    public void display() {
        IO.println("\n=== HERO STATUS ===");
        IO.println("Weapon: %s (+%d DMG)%n " + equippedWeapon.name() + equippedWeapon.damage());
        IO.println("Armour: %s (+%d DEF)%n " + equippedArmour.name() + equippedArmour.defence());
        IO.println("Potions (" + potions.size() + "/" + maxPotions + "):");

        if (potions.isEmpty()) {
            IO.println("  - None");
        } else {
            for (int i = 0; i < potions.size(); i++) {
                Potion p = potions.get(i);
                IO.println("  [%d] %s (+%d HP)%n", i + 1, p.name(), p.healthRestored());
            }
        }
    }

}
