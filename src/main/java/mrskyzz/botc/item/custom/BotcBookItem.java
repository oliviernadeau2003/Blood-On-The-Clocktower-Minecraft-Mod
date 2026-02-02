package mrskyzz.botc.item.custom;

import mrskyzz.botc.game.Game;
import mrskyzz.botc.game.GameState;
import mrskyzz.botc.menu.BotcGameMasterMenu;
import mrskyzz.botc.menu.BotcStartupMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class BotcBookItem extends Item {

    public BotcBookItem(Properties properties) {
        super(properties);
    }

    private void openChest(ServerPlayer player) {
        // Based on the current game state, a different menu will be open.
        if (Game.getGameState() == GameState.SETUP) {
            NetworkHooks.openScreen(
                    player,
                    new SimpleMenuProvider(
                            (id, playerInv, p) -> new BotcStartupMenu(id, playerInv),
                            Component.literal("Startup Menu - BOTC")
                    )
            );
        } else {
            NetworkHooks.openScreen(
                    player,
                    new SimpleMenuProvider(
                            (id, playerInv, p) -> new BotcGameMasterMenu(id, playerInv),
                            Component.literal("Game Master Menu - BOTC")
                    )
            );
        }
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
//            openChest(serverPlayer);
//        }
//        return InteractionResultHolder.success(player.getItemInHand(hand));
//    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        // ❌ Client does nothing
        if (level.isClientSide) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }

        // ❌ Only main hand should open menus
        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }

        // ✅ Server-only menu open
        if (player instanceof ServerPlayer serverPlayer) {
            openChest(serverPlayer);
        }

        // ✅ sidedSuccess prevents double execution issues
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), false);
    }


    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}

