package mrskyzz.botc.game;

public class Game {

    private static GameState gameState = GameState.SETUP;

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState newState) {
        gameState = newState;
        // TODO: Add hooks/events if needed, e.g., notify players
        System.out.println("Game state changed to: " + newState);
    }

    // Example helper
//    public static boolean isNightPhase() {
//        return gameState == GameState.NIGHT || gameState == GameState.FIRST_NIGHT;
//    }
}
