package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.InputStream;
import java.util.regex.Pattern;


class GameMenuTest {

    @Test
    void readChoiceReturnsSelectedNumber() {
        GameMenu menu = new GameMenu();
        java.util.Scanner scanner =
                new java.util.Scanner("2");

        int choice = menu.readChoice(scanner);

        assertEquals(2, choice);
        scanner.close();
    }

    @Test
    void readChoiceReturnsMinusOneForInvalidInput() {
        GameMenu menu = new GameMenu();
        java.util.Scanner scanner =
                new java.util.Scanner("abc");

        int choice = menu.readChoice(scanner);

        assertEquals(-1, choice);
        scanner.close();
    }

    @Test
    void readChoiceReturnsNumberOutSideMenuRange() {
        GameMenu menu = new GameMenu();
        java.util.Scanner scanner =
                new java.util.Scanner("9");

        int choice = menu.readChoice(scanner);

        assertEquals(9, choice);
        scanner.close();
    }

    @Test
    void handleChoiceShowsLoadGameMessage() {
        GameMenu menu = new GameMenu();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(output));
            menu.handleChoice(2);
            assertTrue(output.toString().contains("Load game is not available yet."));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void handleChoiceShowsExitMessage() {
        GameMenu menu = new GameMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(output));
            menu.handleChoice(3);
            assertTrue(output.toString().contains("Exiting game. Goodbye!"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void handleChoiceShowsInvalidChoiceMessage() {
        GameMenu menu = new GameMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(output));
            menu.handleChoice(9);
            assertTrue(output.toString().contains("Invalid choice! Please choose 1, 2 or 3."));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void runMenuRepeatsUntilExit() {
        String input = "2\n3\n";

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)
            ));
            System.setOut(new PrintStream(output));

            GameMenu menu = new GameMenu();
            menu.runMenu();

            String result = output.toString();
            String menuTitle = "=== FÅNGDJUPET ===";

            assertEquals(
                    2,
                    result.split(
                            Pattern.quote(menuTitle), -1
                    ).length -1
            );
            assertTrue(result.contains("Load game is not available yet."));
            assertTrue(result.contains("Exiting game. Goodbye!"));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
