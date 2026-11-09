package game;

import items.Weapon;
import items.Armour;
import items.Potion;
import items.Inventory;
import items.Item;
import items.LootGenerator;
import java.util.Random;
import combat.Battle;
import combat.Hero;
import combat.Enemy;
import combat.AttackStrategy;
import combat.LightAttack;
import combat.NormalAttack;
import combat.HeavyAttack;
import combat.TurnEvent;
import java.util.List;
import java.util.Scanner;

public class BattleController {
    private final Scanner scanner;
    private final Inventory inventory;
    private final LootGenerator lootGenerator;
    private final Random battleRandom;

    public BattleController(Scanner scanner) {
        this(scanner, null);
    }
    public BattleController(Scanner scanner, Inventory inventory) {
        this(scanner, inventory, new LootGenerator(new Random()));
    }


    BattleController(Scanner scanner, Inventory inventory,
                     LootGenerator lootGenerator) {
        this.scanner = scanner;
        this.inventory = inventory;
        this.lootGenerator = lootGenerator;
        this.battleRandom = new Random();
    }
    BattleController(Scanner scanner, Inventory inventory, LootGenerator lootGenerator,
                     Random battleRandom) {
        this.scanner = scanner;
        this.inventory = inventory;
        this.lootGenerator = lootGenerator;
        this.battleRandom = battleRandom;
    }


    public boolean startBattle(Hero hero, Enemy enemy) {
        Battle battle = new Battle(hero, enemy, battleRandom);

        while (!battle.isOver()) {
            System.out.println("\nChoose your attack:");
            System.out.println("1. Light Attack");
            System.out.println("2. Normal Attack");
            System.out.println("3. Heavy Attack");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine().trim();
            AttackStrategy attack;

            switch (choice) {
                case "1":
                    attack = new LightAttack();
                    break;
                case "2":
                    attack = new NormalAttack();
                    break;
                case "3":
                    attack = new HeavyAttack();
                    break;
                default:
                    System.out.println("Invalid Choice! Try again.");
                    continue;
            }

            List<TurnEvent> events = battle.playTurn(attack);
            for (TurnEvent event : events) {
               if (event.hit()) {
                   System.out.println(event.attacker() + " attacks " + event.target()
                   + " for " + event.damage() + " damage!");
               } else {
                   System.out.println(event.attacker() + " attacks "
                   + event.target() + " but misses!");
               }
               System.out.println(event.target() + " has " + event.targetHpLeft() + " HP left.");
            }
        }
        if (hero.isAlive() && inventory != null) {
            Item droppedLoot = lootGenerator.dropAnyItem();
            System.out.println("The enemy dropped: " + droppedLoot.name());

            if (droppedLoot instanceof Weapon weapon) {
                inventory.handleWeaponDrop(weapon);
                hero.equipWeapon(inventory.getEquippedWeapon().damage());
            } else if (droppedLoot instanceof Armour armour) {
                inventory.handleArmourDrop(armour);
                hero.equipArmour(inventory.getEquippedArmour().defence());
            } else if (droppedLoot instanceof Potion potion) {

                while (true) {
                    System.out.println("Do you want to pick it up?");
                    System.out.println("1. Yes, take it");
                    System.out.println("2.No, leave it");
                    System.out.print("Your choice: ");

                    String lootChoice = scanner.nextLine().trim();

                    if (lootChoice.equals("1")) {
                        inventory.addPotion(potion);
                        break;
                    } else if (lootChoice.equals("2")) {
                        System.out.println("You left the loot behind.");
                        break;
                    } else {
                        System.out.println("Invalid choice. PLease enter 1 or 2.");
                    }
                }
            }
        }
        return hero.isAlive();
    }
}
