package items;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ItemRegistry {
    private static final List<Weapon> WEAPONS = new ArrayList<>();
    private static final List<Armour> ARMOURS = new ArrayList<>();
    private static final List<Potion> POTIONS = new ArrayList<>();
    private static final List<Item> ALL_ITEMS = new ArrayList<>();

    static {
        loadItemsFromCsv("/items.csv");
    }

    private ItemRegistry() {
    }

    private static void loadItemsFromCsv(String resourcePath) {
        InputStream input = ItemRegistry.class.getResourceAsStream(resourcePath);
        if (input == null) {
            throw new IllegalStateException("Could not locate " + resourcePath + " on the classpath.");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            reader.lines()
                    .skip(1) // Skip header line
                    .filter(line -> !line.isBlank())
                    .forEach(ItemRegistry::parseAndRegisterLine);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize ItemRegistry from CSV", e);
        }
    }

    private static void parseAndRegisterLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 5) return;

        String type = parts[0].trim().toUpperCase();
        String name = parts[1].trim();
        String description = parts[2].trim();
        int weight = Integer.parseInt(parts[3].trim());
        int value = Integer.parseInt(parts[4].trim());

        switch (type) {
            case "WEAPON" -> {
                Weapon w = new Weapon(name, description, weight, value);
                WEAPONS.add(w);
                ALL_ITEMS.add(w);
            }
            case "ARMOUR" -> {
                Armour a = new Armour(name, description, weight, value);
                ARMOURS.add(a);
                ALL_ITEMS.add(a);
            }
            case "POTION" -> {
                Potion p = new Potion(name, description, weight, value);
                POTIONS.add(p);
                ALL_ITEMS.add(p);
            }
            default -> throw new IllegalArgumentException("Unknown item type in CSV: " + type);
        }
    }

    public static List<Weapon> getWeapons() {
        return Collections.unmodifiableList(WEAPONS);
    }

    public static List<Armour> getArmours() {
        return Collections.unmodifiableList(ARMOURS);
    }

    public static List<Potion> getPotions() {
        return Collections.unmodifiableList(POTIONS);
    }

    public static List<Item> getAllItems() {
        return Collections.unmodifiableList(ALL_ITEMS);
    }

    public static Weapon getStartingWeapon() {
        return WEAPONS.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No weapons loaded in registry."));
    }

    public static Armour getStartingArmour() {
        return ARMOURS.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No armours loaded in registry."));
    }
}
