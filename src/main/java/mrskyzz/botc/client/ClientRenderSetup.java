package mrskyzz.botc.client;

import mrskyzz.botc.Botc;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import mrskyzz.botc.client.render.layer.DeathSkinOverlayLayer;

@Mod.EventBusSubscriber(
        modid = Botc.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientRenderSetup {

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {

        event.getSkins().forEach(skin -> {
            var renderer = event.getSkin(skin);

            if (renderer instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(
                        new DeathSkinOverlayLayer(playerRenderer)
                );
            }
        });
    }

}
