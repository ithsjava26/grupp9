package game;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class SaveManager {

    private final Path saveFile;

    public SaveManager() {
        this.saveFile = Path.of("savegame.txt");
    }

    public SaveManager(Path saveFile) {
        this.saveFile = saveFile;
    }

    public void save(GameSave gameSave) {

        String data = gameSave.heroName() + ";" +
                gameSave.heroHp() + ";" +
                gameSave.currentStage() + ";" +
                gameSave.gameCompleted();

        try {
            Files.writeString(saveFile, data);
        } catch (IOException e) {
            System.out.println("Could not save game: " + e.getMessage());
        }


    }

    public GameSave load() {

        try {
            String data = Files.readString(saveFile);
            String[] parts = data.split(";");

            String heroName = parts[0];
            int heroHp = Integer.parseInt(parts[1]);
            int currentStage = Integer.parseInt(parts[2]);
            boolean gameCompleted = Boolean.parseBoolean(parts[3]);

            return new GameSave(heroName, heroHp, currentStage, gameCompleted);



        } catch (IOException e) {
            System.out.println("Could not load game: " + e.getMessage());
        }

        return null;
    }


    public static void main(String[] args) {
        GameSave gameSave = new GameSave("Player", 80, 2, false);

        SaveManager manager = new SaveManager();
        //manager.save(gameSave);

        GameSave loadedGame = manager.load();
        IO.println(loadedGame);



    }
}
