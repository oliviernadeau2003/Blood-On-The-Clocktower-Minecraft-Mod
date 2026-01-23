package mrskyzz.botc.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import mrskyzz.botc.item.ModItems;
import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.LecternRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BotcLecternRenderer extends LecternRenderer {

    // 🔹 Custom book texture
    private static final ResourceLocation BOTC_BOOK_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    "botc",
                    "textures/entity/lectern_book.png"
            );

    // 🔹 Vanilla book texture (hardcoded in 1.20+)
    private static final ResourceLocation VANILLA_BOOK_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/entity/enchanting_table_book.png"
            );

    private final BookModel bookModel;

    public BotcLecternRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        this.bookModel = new BookModel(context.bakeLayer(ModelLayers.BOOK));
    }

    @Override
    public void render(
            LecternBlockEntity lectern,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            int packedOverlay
    ) {
        ItemStack stack = lectern.getBook();
        if (stack.isEmpty()) return;

        boolean isVanillaBook =
                stack.is(Items.BOOK) ||
                        stack.is(Items.WRITTEN_BOOK) ||
                        stack.is(Items.WRITABLE_BOOK);

        boolean isBotcBook =
                stack.is(ModItems.BOTC_BOOK.get());

        if (!isVanillaBook && !isBotcBook) return;

        BlockState state = lectern.getBlockState();
        if (!state.getValue(LecternBlock.HAS_BOOK)) return;

        poseStack.pushPose();

        // --- Vanilla lectern transform ---
        poseStack.translate(0.5D, 1.0625D, 0.5D);
        float rot = state.getValue(LecternBlock.FACING).getClockWise().toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(-rot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(67.5F));
        poseStack.translate(0.0D, -0.125D, 0.0D);

        // --- Static animation (1.20+) ---
        float time = (lectern.getLevel().getGameTime() + partialTicks) * 0.02F;
        float pageFlipLeft = Mth.clamp(Mth.sin(time) * 0.5F + 0.5F, 0F, 1F);
        float pageFlipRight = Mth.clamp(Mth.sin(time + 1.0F) * 0.5F + 0.5F, 0F, 1F);
        float open = 1.0F;

        this.bookModel.setupAnim(time, pageFlipLeft, pageFlipRight, open);

        ResourceLocation texture = isBotcBook
                ? BOTC_BOOK_TEXTURE
                : VANILLA_BOOK_TEXTURE;

        VertexConsumer vc =
                buffer.getBuffer(RenderType.entitySolid(texture));

        this.bookModel.renderToBuffer(
                poseStack,
                vc,
                packedLight,
                packedOverlay,
                1F, 1F, 1F, 1F
        );

        poseStack.popPose();
    }
}
