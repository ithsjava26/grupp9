package game;
import combat.Hero;

public class Game {
    private Hero hero;

    private static final int TOTAL_STAGES = 5;
    private int currentStage = 1;
    private boolean gameCompleted = false;

    public Game() {
        hero = new Hero("Player", 100, 15, 5, 10);
    }

    public void start() {
        System.out.println("Welcome to Fångdjupet!");
        System.out.println("Hero: " + hero.getName());
        System.out.println("HP: " + hero.getHp());

        System.out.println("Stage: " + currentStage + " / " + TOTAL_STAGES);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();

    }

    public void nextStage() {
        if (currentStage < TOTAL_STAGES) {
            currentStage++;
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
