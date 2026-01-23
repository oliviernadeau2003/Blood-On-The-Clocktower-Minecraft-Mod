package mrskyzz.botc.client;

import mrskyzz.botc.block.ModBlockEntities;
import mrskyzz.botc.client.renderer.BotcLecternRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "botc",
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = net.minecraftforge.api.distmarker.Dist.CLIENT
)
public class ClientSetup {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                ModBlockEntities.BOTC_LECTERN.get(),
                BotcLecternRenderer::new
        );
    }
}
