package items;

import java.util.List;
import java.util.Random;

public class LootGenerator {
    private final Random random;

    public LootGenerator(Random random) {
        this.random = random;
    }

    public Item dropAnyItem() {
        List<Item> pool = ItemRegistry.ALL_ITEMS;
        return pool.get(random.nextInt(pool.size()));
    }

    public Potion dropPotionOnly() {
        List<Potion> pool = ItemRegistry.mutablePotions;
        return pool.get(random.nextInt(pool.size()));
    }
}

/**
 * To implement random drop generator
 * LootGenerator lootGen = new LootGenerator(new Random());
 * Item droppedLoot = lootGen.dropAnyItem();
 * IO.println("The enemy dropped " + droppedLoot.name());
 */
