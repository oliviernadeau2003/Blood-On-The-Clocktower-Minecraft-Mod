package mrskyzz.botc.game;

import mrskyzz.botc.utils.DoorSavedData;
import net.minecraft.server.level.ServerLevel;

import java.util.List;

public class Game {

    //    public static List<Door> doors = new ArrayList<>();
    private static GameState gameState = GameState.SETUP;
//    static DoorSavedData data = DoorSavedData.get(level);
//    public static List<Door> doors = data.getDoors();

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

//    public static void toggleDoors(Level level) {
//        for (Door door : doors) {
//            door.toggle(level);
//        }
//    }

    public static List<Door> getDoors(ServerLevel level) {
        return DoorSavedData.get(level).getDoors();
    }

    public static void toggleDoors(ServerLevel level) {
        DoorSavedData data = DoorSavedData.get(level);

        for (Door door : data.getDoors()) {
            door.toggle(level);
            data.setDirty(); // SAVE change
        }
    }

//    Example helper
//    public static boolean isNightPhase() {
//        return gameState == GameState.NIGHT || gameState == GameState.FIRST_NIGHT;
//    }

}
