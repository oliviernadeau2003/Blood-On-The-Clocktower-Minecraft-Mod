package mrskyzz.botc.game;

public enum GameState {
    WAITING,       // Waiting in lobby for players
    SETUP,         // Assigning roles, initializing data
    FIRST_NIGHT,   // First night with special roles
    NIGHT,         // Regular night phase
    MORNING,       // Reveal night results
    DAY,           // Open discussion
    NOMINATION,    // Players nominate others
    EXECUTION,     // Execution and win condition check
    GAME_OVER      // Game has ended
}
