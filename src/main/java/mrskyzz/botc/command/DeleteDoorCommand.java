package mrskyzz.botc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mrskyzz.botc.game.Door;
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
                                    DoorSavedData data = DoorSavedData.get(level);

                                    String remaining = builder.getRemaining().toLowerCase();

                                    data.getDoors().stream()
                                            .map(Door::getName)
                                            .filter(name -> name.toLowerCase().startsWith(remaining))
                                            .sorted(String.CASE_INSENSITIVE_ORDER)
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

    private static int deleteDoor(CommandSourceStack source, String doorName) throws CommandSyntaxException {
        ServerLevel level = source.getLevel();
        DoorSavedData data = DoorSavedData.get(level);

        doorName = doorName.trim();

        Door target = null;
        for (Door door : data.getDoors()) {
            if (door.getName().equalsIgnoreCase(doorName)) {
                target = door;
                break;
            }
        }

        if (target == null) {
            source.sendFailure(
                    Component.literal("Invalid door name: " + doorName)
            );
            return 0;
        }

        // 🔥 IMPORTANT: restore blocks before deleting the door
        if (target.isOpen()) {
            target.close(level);
        }

        data.removeDoor(target); // marks SavedData dirty internally

        Door finalTarget = target;
        source.sendSuccess(
                () -> Component.literal("Door \"" + finalTarget.getName() + "\" deleted successfully"),
                true
        );

        return 1;
    }
}
