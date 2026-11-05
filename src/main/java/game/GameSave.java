package game;

public record GameSave(
        String heroName,
        int heroHp,
        int currentStage,
        boolean gameCompleted
) {
}
