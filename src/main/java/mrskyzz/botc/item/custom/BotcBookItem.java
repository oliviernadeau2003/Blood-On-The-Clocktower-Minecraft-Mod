package mrskyzz.botc.item.custom;

import mrskyzz.botc.Botc;
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            openChest(serverPlayer);
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}


//public class BotcBookItem extends Item {
//
//    private SimpleContainer menu1 = createFilledContainer(3 * 9);
//
//    public BotcBookItem(Properties pProperties) {
//        super(pProperties);
//    }
//
//    private SimpleContainer createFilledContainer(int size) {
//        SimpleContainer container = new SimpleContainer(size);
//
//        ItemStack filler = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);
//        filler.setHoverName(Component.literal(""));
//
//        for (int i = 0; i < size; i++) {
//            container.setItem(i, filler.copy());
//        }
//
//        filler = new ItemStack(Items.LIME_CONCRETE);
//        filler.setHoverName(Component.literal("START"));
//        container.setItem(10, filler);
//
//        return container;
//    }
//
//    private void openChest(ServerPlayer player) {
//        int rows = 3;
//        int size = rows * 9;
//
//        SimpleContainer container = createFilledContainer(size);
//
//        NetworkHooks.openScreen(
//                player,
//                new SimpleMenuProvider(
//                        (id, playerInv, p) ->
//                                new ChestMenu(
//                                        MenuType.GENERIC_9x3,
//                                        id,
//                                        playerInv,
//                                        menu1,
//                                        rows
//                                ),
//                        Component.literal("Blood On The Clocktower")
//                )
//        );
//    }
//
//
//    @Override
//    public boolean isFoil(@NotNull ItemStack stack) {
//        return true;
//    }
//
//
/// /    private void openChest(ServerPlayer serverPlayer) {
/// /        SimpleContainer container = new SimpleContainer(9*3);
/// /
/// /        NetworkHooks.openScreen(
/// /                serverPlayer,
/// /                new SimpleMenuProvider(
/// /                        (id, playerInv, player) ->
/// /                                new ChestMenu(
/// /                                        MenuType.GENERIC_9x3,
/// /                                        id,
/// /                                        playerInv,
/// /                                        container,
/// /                                        3
/// /                                ),
/// /                        Component.literal("Blood On The Clocktower")
/// /                )
/// /        );
/// /    }
//
//    /// When shift-click, open an inventory for setting doors and settings like head placements
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
//            openChest(serverPlayer);
//        }
//
/// /        if (player.isShiftKeyDown()) {}
//
//        //* Toggle Player Visibility
/// /        player.setInvisible(!player.isInvisible());
//
//        return InteractionResultHolder.success(player.getItemInHand(hand));
//    }
//
//
//}
