package items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ItemRegistry {
    private ItemRegistry() {
    }

    static final List<Weapon> mutableWeapons = new ArrayList<>();
    static final List<Armour> mutableArmours = new ArrayList<>();
    static final List<Potion> mutablePotions = new ArrayList<>();

    private static Weapon reg(Weapon w) {
        mutableWeapons.add(w);
        return w;
    }

    private static Armour reg(Armour a) {
        mutableArmours.add(a);
        return a;
    }

    private static Potion reg(Potion p) {
        mutablePotions.add(p);
        return p;
    }

    //Weapons
    public static final Weapon PEG_LEG = reg(new Weapon("Peg Leg. ",
            "An old wooden prosthetic leg... It kind of smells.", 1, 1));
    public static final Weapon WOODEN_SWORD = reg(new Weapon("Wooden Sword. ",
            "An old training sword. Not very effective in combat.", 1, 2));
    public static final Weapon RUSTY_AXE = reg(new Weapon("Rusty Axe. ",
            "How much wood could a woodchuck chuck.", 2, 3));
    public static final Weapon IRON_PAN = reg(new Weapon("Iron Pan. ",
            "What about second breakfast?", 3, 4));
    public static final Weapon SHARD_OF_NARSIL = reg(new Weapon("Shard of Narsil. ",
            "The broken hilt of the sword that cut the one ring from the hand of the dark lord Sauron.", 2, 6));
    public static final Weapon ANDURIL = reg(new Weapon("Anduril. ",
            "Flame of the west. Reforged by the Elven smiths of Rivendell.", 4, 8));

    //Armour
    public static final Armour LEATHER_SPEEDO = reg(new Armour("Leather Speedo. ",
            "Bro, put some clothes on.", 1, 1));
    public static final Armour IRON_ARMOUR = reg(new Armour("Iron Armour. ",
            "Heavy but it does the job.", 4, 2));
    public static final Armour STEEL_ARMOUR = reg(new Armour("Steel Armour. ",
            "Steel plate. Lightweight and strong", 3, 3));
    public static final Armour DIAMOND_CHESTPLATE = reg(new Armour("Diamond Chestplate. ",
            "Don't ask me how this was made.", 4, 6));
    public static final Armour MITHRIL_MAIL = reg(new Armour("Mithril chainmail. ",
            "Forged by the Dwarves of Erebor, nigh indestructible.", 2, 8));

    //Potions
    public static final Potion MINOR_HEALTH_POTION = reg(new Potion("Health Potion. ",
            "Restores -- health points.", 1, 1));
    public static final Potion GREATER_HEALTH_POTION = reg(new Potion("Greater Health Potion. ",
            "Restores -- health points.", 1, 2));
    public static final Potion SUPER_HEALTH_POTION = reg(new Potion("Super Health Potion. ",
            "Restores -- health points.", 1, 3));


    public static final List<Weapon> Weapons = Collections.unmodifiableList(mutableWeapons);
    public static final List<Armour> Armours = Collections.unmodifiableList(mutableArmours);
    public static final List<Potion> Potions = Collections.unmodifiableList(mutablePotions);

    public static final List<Item> ALL_ITEMS = Stream.of(mutableWeapons, mutableArmours, mutablePotions)
            .flatMap(List::stream)
            .map(Item.class::cast)
            .toList();
}

