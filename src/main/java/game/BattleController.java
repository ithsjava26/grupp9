package game;
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
    public BattleController(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean startBattle(Hero hero, Enemy enemy) {
        Battle battle = new Battle(hero, enemy);

        while (!battle.isOver()) {
            System.out.println("\nChooce your attack:");
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
               System.out.println(event.attacker() + " attacks " + event.target()
               + " for " + event.damage() + " damage!");
               System.out.println(event.target() + " has " + event.targetHpLeft() + " HP left.");
            }
        }
        return hero.isAlive();
    }
}
