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
            return scanner.nextInt();
        }

        scanner.nextLine();
        System.out.println("Invalid input. Please enter a number.");
        return -1;
    }

    public void handleChoice(int choice) {
        if (choice == -1) {
            return;
        }
        switch (choice) {
            case 1:
                System.out.println("Starting a new game...");
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

        showMenu();
        int choice = readChoice(scanner);
        handleChoice(choice);
    }

    public static void main(String[] args) {
        GameMenu menu = new GameMenu();
        menu.runMenu();
    }

}
