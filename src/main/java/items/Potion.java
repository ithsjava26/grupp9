package items;

public record Potion(String name, String description, int weight, int healthRestored) implements Item {
}
