package mrskyzz.botc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import mrskyzz.botc.Botc;
import mrskyzz.botc.game.Game;
import mrskyzz.botc.game.GameState;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.function.Supplier;

public class SetGameStateCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        // Create literal command /setgamestate
        LiteralArgumentBuilder<CommandSourceStack> cmd = Commands.literal("setgamestate")
                .requires(cs -> cs.hasPermission(4)); // Only ops can execute

        // Add argument "state" with suggestions based on GameState enum
        RequiredArgumentBuilder<CommandSourceStack, String> stateArg =
                Commands.argument("state", StringArgumentType.word())
                        .suggests((context, builder) -> {
                            Arrays.stream(GameState.values())
                                    .map(Enum::name)
                                    .forEach(builder::suggest);
                            return builder.buildFuture();
                        })
                        .executes(context -> execute(context, StringArgumentType.getString(context, "state")));

        // Attach argument to the command
        cmd.then(stateArg);

        // Register the command
        dispatcher.register(cmd);
    }

    private static int execute(CommandContext<CommandSourceStack> context, String stateName) {
        try {
            GameState state = GameState.valueOf(stateName.toUpperCase());
            Game.setGameState(state); // Update the current game state
            context.getSource().sendSuccess(
                    (java.util.function.Supplier<net.minecraft.network.chat.Component>)
                            () -> Component.literal("Game state set to: " + state),
                    true
            );
            return 1; // success
        }
        catch (IllegalArgumentException e) {
//            context.getSource().sendFailure(
//                    (java.util.function.Supplier<net.minecraft.network.chat.Component>)
//                            () -> Component.literal("Invalid game state: " + stateName)
//            );
            return 0; // failure
        }
    }

}
