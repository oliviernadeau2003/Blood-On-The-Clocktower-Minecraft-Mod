package mrskyzz.botc.block.custom;

import mrskyzz.botc.block.ModBlockEntities;
import mrskyzz.botc.block.entity.BotcLecternBlockEntity;
import mrskyzz.botc.item.custom.RoleBookItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BotcLecternBlock extends LecternBlock {
    public BotcLecternBlock(Properties props) {
        super(props);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.BOTC_LECTERN.get().create(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack held = player.getItemInHand(hand);
        /* ---- * 1️⃣ HAS BOOK → ALWAYS OPEN GUI (vanilla behavior) * ---- */
        if (state.getValue(HAS_BOOK)) {
            if (!level.isClientSide) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof BotcLecternBlockEntity botcBe) {
                    player.openMenu(botcBe);
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        /* ---- * 2️⃣ EMPTY → ONLY ACCEPT YOUR BOOK * ---- */
        if (!(held.getItem() instanceof RoleBookItem roleBook)) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide) {
            ItemStack writtenBook = roleBook.createBook();
            boolean placed = tryPlaceBook(player, level, pos, state, writtenBook);
            if (placed) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof BotcLecternBlockEntity botcBe) {
                    botcBe.setOriginalBook(held);
                }
                if (!player.isCreative()) {
                    held.shrink(1);
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof BotcLecternBlockEntity botcBe && state.getValue(HAS_BOOK)) {
                popResource(level, pos, botcBe.getOriginalBook());
            }
            level.removeBlockEntity(pos);
        }
    }
}