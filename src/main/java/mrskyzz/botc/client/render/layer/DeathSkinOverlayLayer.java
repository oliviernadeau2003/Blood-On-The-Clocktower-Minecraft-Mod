package mrskyzz.botc.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mrskyzz.botc.item.custom.DeathOverlayHelmetItem;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class DeathSkinOverlayLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private static final ResourceLocation OVERLAY_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            "botc",
            "textures/entity/player/death_player_texture_layer.png"
    );

    public DeathSkinOverlayLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!(helmet.getItem() instanceof DeathOverlayHelmetItem)) {
            return;
        }
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(OVERLAY_TEXTURE));
        PlayerModel<AbstractClientPlayer> model = this.getParentModel();
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.0D, 0.001D);
        model.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
    }
}
