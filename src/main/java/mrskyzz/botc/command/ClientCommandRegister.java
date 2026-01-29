package mrskyzz.botc.command;

import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientCommandRegister {

    @SubscribeEvent
    public static void registerClientCommands(RegisterClientCommandsEvent event) {
//        SetGameStateCommand.register(event.getDispatcher());
    }
}
