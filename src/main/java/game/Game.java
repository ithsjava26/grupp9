package game;

import items.Inventory;
import combat.Hero;
import combat.Enemy;
import items.ItemRegistry;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {

    private Hero hero;
    private final Scanner scanner;
    private final SaveManager saveManager;

    private static final int INVENTORY_CAPACITY = 100;
    private final Inventory inventory;

    private static final int TOTAL_STAGES = 5;
    private int currentStage = 1;
    private boolean gameCompleted = false;

    public Game() {
        this(new Scanner(System.in));
    }
    public Game(Scanner scanner) {
        this(scanner, new SaveManager());
        
    }

    public Game(Scanner scanner, SaveManager saveManager) {
        this.scanner = scanner;
        this.saveManager = saveManager;

        inventory = new Inventory(
                INVENTORY_CAPACITY,
                ItemRegistry.getStartingWeapon(),
                ItemRegistry.getStartingArmour(),
                scanner::nextLine
        );

        hero = new Hero("Player", 100, 15, 5, 10);
        hero.equipWeapon(inventory.getEquippedWeapon().damage());
        hero.equipArmour(inventory.getEquippedArmour().defence());
    }

    public void start() {
        System.out.println("Welcome to Fångdjupet!");
        System.out.println("Hero: " + hero.getName());
        System.out.println("HP: " + hero.getHp());

        System.out.println("Stage: " + currentStage + " / " + TOTAL_STAGES);

        while (!gameCompleted && hero.isAlive()) {

            Enemy enemy = new Enemy("Prison Guard", 50, 10, 3, 5);
            BattleController battleController = new BattleController(scanner, inventory);
            boolean heroWon;
            try {
                heroWon = battleController.startBattle(hero, enemy);
            } catch (NoSuchElementException e) {
                System.out.println("Input ended. Exiting game.");
                return;
            }

            if (heroWon) {
                System.out.println("You won the battle!");
                nextStage();
                saveGame();
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

    public void saveGame() {

        GameSave gameSave = new GameSave(hero.getName(), hero.getHp(), currentStage, gameCompleted);
        saveManager.save(gameSave);


    }

    public boolean loadGame() {

        GameSave loadedGame = saveManager.load();

        if (loadedGame == null) {
            return false;
        }

        currentStage = loadedGame.currentStage();
        gameCompleted = loadedGame.gameCompleted();

        hero = new Hero(loadedGame.heroName(), 100, 15, 5, 10);
        hero.restoreHp(loadedGame.heroHp());


        return true;

    }
}
