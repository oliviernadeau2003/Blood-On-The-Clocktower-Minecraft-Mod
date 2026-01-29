package mrskyzz.botc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mrskyzz.botc.game.Game;
import mrskyzz.botc.game.GameState;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

public class SetGameStateCommand {

    public SetGameStateCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("setgamestate")
                        .requires(source -> source.hasPermission(4)) // OP only
                        .then(Commands.argument("state", StringArgumentType.word())
                                .suggests((context, builder) -> {
                                    String remaining = builder.getRemaining().toLowerCase();

                                    Arrays.stream(GameState.values())
                                            .map(Enum::name)
                                            .map(String::toLowerCase) // lowercase (vanilla-style)
                                            .sorted()                 // alphabetical order
                                            .filter(name -> name.startsWith(remaining))
                                            .forEach(builder::suggest);

                                    return builder.buildFuture();
                                })
                                .executes(command ->
                                        setGameState(
                                                command.getSource(),
                                                StringArgumentType.getString(command, "state")
                                        )
                                )
                        )
        );
    }


    private int setGameState(CommandSourceStack source, String stateName) throws CommandSyntaxException {

        try {
            GameState state = GameState.valueOf(stateName.toUpperCase());
            Game.setGameState(state);

            source.sendSuccess(() -> Component.literal("Game state set to: " + state), true);
            return 1;

        } catch (IllegalArgumentException e) {
            source.sendFailure(Component.literal("Invalid game state: " + stateName));
            return 0;
        }
    }
}
