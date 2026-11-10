package game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SaveManagerTest {

    @Test
    void testSaveAndLoad(@TempDir Path tempDir) {
        GameSave original = new GameSave("Player", 80, 2, false);

        Path testFile = tempDir.resolve("savegame.txt");
        SaveManager manager = new SaveManager(testFile);

        manager.save(original);

        GameSave loaded = manager.load();

        assertEquals(original, loaded);

    }

    @Test
    void testLoadMissingFile(@TempDir Path tempDir) {

        Path missingFile = tempDir.resolve("missing-save.txt");
        SaveManager manager = new SaveManager(missingFile);

        GameSave loaded = manager.load();

        assertNull(loaded);



    }
}
