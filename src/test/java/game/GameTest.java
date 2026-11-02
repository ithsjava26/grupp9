package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GameTest {

    @Test
    void gameStartsaAtStageOne() {
        Game game = new Game();

        assertEquals(1, game.getCurrentStage());
    }

    @Test
    void nextStageMovesToStageTwo() {
        Game game = new Game();

        game.nextStage();
        assertEquals(2, game.getCurrentStage());
    }

    @Test
    void gameIsNotCompleteBeforeFinalStage() {
        Game game = new Game();

        assertFalse(game.isGameComplete());
    }

    @Test
    void gameIsCompleteAtFinalStage() {
        Game game = new Game();

        for (int i = 1; i < 5; i++) {
            game.nextStage();
        }
        game.nextStage();
        assertTrue(game.isGameComplete());
    }

    @Test
    void gameDoesNotGoBeyondFinalStage() {
        Game game = new Game();

        for (int i = 1; i < 5; i++) {
            game.nextStage();
        }
        game.nextStage();
        assertEquals(5, game.getCurrentStage());
    }

    @Test
    void gameIsNotCompleteWhenReachFinalStage() {
        Game game = new Game();
        for (int i = 1; i < 5; i++) {
            game.nextStage();
        }
        assertEquals(5, game.getCurrentStage());
        assertFalse(game.isGameComplete());
    }

    @Test
    void gameExitsWhenBattleInputEnds() {
        Scanner scanner = new Scanner("");

        Game game = new Game(scanner);

        assertDoesNotThrow(() -> game.start());
        assertEquals(1, game.getCurrentStage());
        assertFalse(game.isGameComplete());
    }

    }


