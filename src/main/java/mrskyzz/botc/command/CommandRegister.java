package mrskyzz.botc.command;

import mrskyzz.botc.Botc;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Botc.MOD_ID)
public class CommandRegister {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        new SetGameStateCommand(event.getDispatcher());
    }
}
