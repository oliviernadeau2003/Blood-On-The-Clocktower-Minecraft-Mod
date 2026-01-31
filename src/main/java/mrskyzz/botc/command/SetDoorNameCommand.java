package mrskyzz.botc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mrskyzz.botc.game.Door;
import mrskyzz.botc.game.Game;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class SetDoorNameCommand {

    public SetDoorNameCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("setdoorname")
                        .requires(source -> source.hasPermission(4)) // OP only
                        .then(
                                Commands.argument("door", StringArgumentType.string())
                                        .suggests((context, builder) -> {
                                            Game.doors.stream()
                                                    .map(Door::getName)
                                                    .sorted(String.CASE_INSENSITIVE_ORDER)
                                                    .forEach(builder::suggest);
                                            return builder.buildFuture();
                                        })
                                        .then(
                                                Commands.argument("name", StringArgumentType.greedyString())
                                                        .executes(context ->
                                                                setDoorName(
                                                                        context.getSource(),
                                                                        StringArgumentType.getString(context, "door"),
                                                                        StringArgumentType.getString(context, "name")
                                                                )
                                                        )
                                        )
                        )

        );
    }

    private int setDoorName(CommandSourceStack source, String oldName, String newName) throws CommandSyntaxException {

        for (Door door : Game.doors) {
            if (door.getName().equalsIgnoreCase(oldName)) {

                for (Door d : Game.doors) {
                    if (d.getName().equalsIgnoreCase(newName)) {
                        source.sendFailure(
                                Component.literal("A door named \"" + newName + "\" already exists.")
                        );
                        return 0;
                    }
                }

                door.setName(newName); // ← THIS is the key change

                source.sendSuccess(
                        () -> Component.literal(
                                "Door \"" + oldName + "\" renamed to \"" + newName + "\""
                        ),
                        true
                );

                return 1;
            }
        }

        source.sendFailure(
                Component.literal("Invalid door name: \"" + oldName + "\"")
        );
        return 0;
    }

}
