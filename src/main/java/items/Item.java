package items;

public sealed interface Item permits Armour, Weapon, Potion {
    String name();

    String description();

    int weight();
}

