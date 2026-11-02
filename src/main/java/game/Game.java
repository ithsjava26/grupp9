package game;
import combat.Hero;
import combat.Enemy;
import java.util.Scanner;

public class Game {
    private Hero hero;
    private final Scanner scanner;


    private static final int TOTAL_STAGES = 5;
    private int currentStage = 1;
    private boolean gameCompleted = false;

    public Game() {
        this(new Scanner(System.in));
    }
    public Game(Scanner scanner) {
        this.scanner = scanner;
        hero = new Hero("Player", 100, 15, 5, 10);
    }

    public void start() {
        System.out.println("Welcome to Fångdjupet!");
        System.out.println("Hero: " + hero.getName());
        System.out.println("HP: " + hero.getHp());

        System.out.println("Stage: " + currentStage + " / " + TOTAL_STAGES);

        while (!gameCompleted && hero.isAlive()) {

            Enemy enemy = new Enemy("Prison Guard", 50, 10, 3, 5);
            BattleController battleController = new BattleController(scanner);
            boolean heroWon = battleController.startBattle(hero, enemy);

            if (heroWon) {
                System.out.println("You won the batle!");
                nextStage();
            } else {
                System.out.println("Game Over! Your hero was defeated.");
            }
        }
    }

    public static void main(String[] args) {
       GameMenu menu = new GameMenu();
        menu.runMenu();

    }

    public void nextStage() {
        if (currentStage < TOTAL_STAGES) {
            currentStage++;
            System.out.println("Congratulations! You advanced to Stage " + currentStage + "!");
            System.out.println("Stage: " + currentStage + " / " + TOTAL_STAGES);
        } else {
            gameCompleted = true;
            System.out.println("All regular stages completed!");
        }
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public boolean isGameComplete() {
        return gameCompleted;
    }
}
