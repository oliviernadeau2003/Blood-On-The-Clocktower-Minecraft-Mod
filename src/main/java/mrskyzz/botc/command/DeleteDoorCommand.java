package mrskyzz.botc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mrskyzz.botc.game.Door;
import mrskyzz.botc.game.Game;
import mrskyzz.botc.utils.DoorSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;

public class DeleteDoorCommand {

    public DeleteDoorCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("deletedoor")
                        .requires(source -> source.hasPermission(4)) // OP only
                        .then(Commands.argument("door", StringArgumentType.greedyString())
                                .suggests((context, builder) -> {
                                    ServerLevel level = context.getSource().getLevel();
                                    String remaining = builder.getRemaining().toLowerCase();

                                    Game.getDoors(level).stream()
                                            .map(Door::getName)
                                            .map(String::toLowerCase)
                                            .sorted()
                                            .filter(name -> name.startsWith(remaining))
                                            .forEach(builder::suggest);

                                    return builder.buildFuture();
                                })
                                .executes(context ->
                                        deleteDoor(
                                                context.getSource(),
                                                StringArgumentType.getString(context, "door")
                                        )
                                )
                        )
        );
    }

    private int deleteDoor(CommandSourceStack source, String doorName) throws CommandSyntaxException {

        // Optional: normalize for matching
        doorName = doorName.trim();

        String finalDoorName = doorName;
        boolean removed = Game.getDoors(source.getLevel()).removeIf(
                door -> door.getName().equalsIgnoreCase(finalDoorName)
        );

        if (removed) {
            source.sendSuccess(
                    () -> Component.literal("Door \"" + finalDoorName + "\" deleted successfully"),
                    true
            );
            return 1;
        } else {
            source.sendFailure(
                    Component.literal("Invalid door name: " + doorName)
            );
            return 0;
        }
    }
}
