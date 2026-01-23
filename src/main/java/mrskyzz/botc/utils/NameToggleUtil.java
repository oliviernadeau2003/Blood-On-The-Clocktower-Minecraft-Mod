package mrskyzz.botc.utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.network.chat.Component;

public class NameToggleUtil {

    private static final String HIDE_NAME_TEAM = "hide_name";

    public static void togglePlayerName(ServerPlayer player) {
        Scoreboard scoreboard = player.getScoreboard();
        String playerName = player.getScoreboardName();

        PlayerTeam currentTeam = scoreboard.getPlayersTeam(playerName);

        // Player already hidden → remove from team
        if (currentTeam != null && currentTeam.getName().equals(HIDE_NAME_TEAM)) {
            scoreboard.removePlayerFromTeam(playerName, currentTeam);
            player.sendSystemMessage(Component.literal("Player name shown"));
            return;
        }

        // Get or create hide-name team
        PlayerTeam hideTeam = scoreboard.getPlayerTeam(HIDE_NAME_TEAM);
        if (hideTeam == null) {
            hideTeam = scoreboard.addPlayerTeam(HIDE_NAME_TEAM);
            hideTeam.setNameTagVisibility(PlayerTeam.Visibility.NEVER);
        }

        scoreboard.addPlayerToTeam(playerName, hideTeam);
        player.sendSystemMessage(Component.literal("Player name hidden"));
    }
}

