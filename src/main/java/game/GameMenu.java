package game;
import java.util.Scanner;

public class GameMenu {

    public void showMenu() {
        System.out.println("=== FÅNGDJUPET ===");
        System.out.println("1. Start New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit");
        System.out.println("Choose an option: ");
    }

    public int readChoice(Scanner scanner) {
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            return choice;
        }
        if (!scanner.hasNextLine()) {
            return -2;
        }

        scanner.nextLine();
        System.out.println("Invalid input. Please enter a number.");
        return -1;
    }

    public void handleChoice(int choice, Scanner scanner) {

        switch (choice) {
            case 1:
                System.out.println("Starting a new game...");
                Game game = new Game(scanner);
                game.start();
                break;
            case 2:
                System.out.println("Load game is not available yet.");
                break;
            case 3:
                System.out.println("Exiting game. Goodbye!");
                break;
                default:
                    System.out.println("Invalid choice! Please choose 1, 2 or 3.");
        }
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            choice = readChoice(scanner);
            if (choice == -2) {
                System.out.println("Input ended. Exiting game.");
                break;
            }
            handleChoice(choice, scanner);
        } while (choice != 3);
    }

    public static void main(String[] args) {
        GameMenu menu = new GameMenu();
        menu.runMenu();
    }

}
