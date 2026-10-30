package items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    protected final List<Item> items;
    private final int weightCapacity;

    public Inventory(int weightCapacity) {
        this.items = new ArrayList<>();
        this.weightCapacity = weightCapacity;
    }

    public boolean add(Item item) {
        if (currentWeight() + item.weight() > weightCapacity) {
            IO.println("Cannot carry " + item.name() + ", you are overburdened.");
            return false;
        }
        items.add(item);
        IO.println(item.name() + " added to inventory.");
        return true;
    }

    public boolean remove(Item item) {
        return items.remove(item);
    }

    public int currentWeight() {
        return items.stream().mapToInt(Item::weight).sum();
    }

    public short display() {
        if (items.isEmpty()) {
            IO.println("Inventory is empty.");
            return 0;
        }

        IO.println("Inventory (" + currentWeight() + "/" + weightCapacity + ")");
        for (Item item : items) {
            String stats = switch (item) {
                case Weapon w -> " DMG: " + w.damage();
                case Armour a -> " DEF: " + a.defence();
                case Potion p -> " HEAL: " + p.healthRestored();
            };
            IO.println(item.name() + item.description() + stats);

        }
        return 0;
    }
}
