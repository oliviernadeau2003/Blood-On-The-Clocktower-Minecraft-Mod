package mrskyzz.botc.game;

import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class Game {

    // Maybe change it to a Set ?
    public static List<Door> doors = new ArrayList<>();
    private static GameState gameState = GameState.SETUP;

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState newState) {
        gameState = newState;
        // // TODO  [2026-01-30] : Add hooks/events if needed, e.g., notify players
//        GameState {
//            WAITING,       // Waiting in lobby for players, (having a action bar title that says 3/6 (min) 21 (max) players) ???
//            SETUP,         // Assigning roles, initializing data, setting doors and head placement -> (door's head & table's head)
//            FIRST_NIGHT,   // First night with special roles ???
//            NIGHT,         // Regular night phase ???
//            MORNING,       // Reveal night results ???
//            DAY,           // Broadcast that the day has started and that the room are now locked ???
//            NOMINATION,    // Broadcast that the meeting has started ???
//            EXECUTION,     // Broadcast who has been killed/voted
//            GAME_OVER      // Broadcast that the game has ended
//        }
        System.out.println("Game state changed to: " + newState);
    }

//    Example helper
//    public static boolean isNightPhase() {
//        return gameState == GameState.NIGHT || gameState == GameState.FIRST_NIGHT;
//    }

}
