package items;

public sealed interface Item permits Armour, Weapon, Potion {
    String name();

    String description();
    //int weight(); **Maybe we implement a weight system later?
}

record Weapon(String name, String description, int damage) implements Item {
}

record Armour(String name, String description, int defense) implements Item {
}

record Potion(String name, String description, int healthRestored) implements Item {
}
