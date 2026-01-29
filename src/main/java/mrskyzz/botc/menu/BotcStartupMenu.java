package mrskyzz.botc.menu;

import mrskyzz.botc.Botc;
import mrskyzz.botc.game.Game;
import mrskyzz.botc.game.GameState;
import mrskyzz.botc.utils.AbstractBotcMenu;
import mrskyzz.botc.utils.NameToggleUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BotcStartupMenu extends AbstractBotcMenu {

    public BotcStartupMenu(int id, Inventory playerInv) {
        super(MenuType.GENERIC_9x3, id, playerInv, 3);
    }

    private static void startGame(ServerPlayer player) {
        player.sendSystemMessage(Component.literal("Start clicked!"));
        Game.setGameState(GameState.FIRST_NIGHT);
        player.closeContainer();
        /// Either using the ClientboundSoundPacket for a single player or the player.level().playSound(...) for playing a sound at a world position
        player.connection.send(
                new ClientboundSoundPacket(
                        sound(SoundEvents.ARROW_HIT_PLAYER),
                        SoundSource.PLAYERS,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        1.0F,
                        1.0F,
                        player.getRandom().nextLong()
                )
        );
//         player.level().playSound(
//                 null, // null = play for all nearby players, including this one
//                 player.blockPosition(),
//                 SoundEvents.ARROW_HIT_PLAYER,
//                 SoundSource.PLAYERS,
//                 1.0F,
//                 1.0F
//         );
    }

    private static Holder<SoundEvent> sound(SoundEvent event) {
        return Holder.direct(event);
    }

    @Override
    public SimpleContainer createContainer() {
        final int INVENTORY_SIZE = 27;
        SimpleContainer container = new SimpleContainer(INVENTORY_SIZE);

        ItemStack filler = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);
        filler.setHoverName(Component.literal(""));

        for (int i = 0; i < INVENTORY_SIZE; i++) {
            container.setItem(i, filler.copy());
        }

        ItemStack start = new ItemStack(Items.LIME_CONCRETE);
        start.setHoverName(Component.literal("Start Game"));
        container.setItem(10, start);

        ItemStack setDoors = new ItemStack(Items.SPRUCE_DOOR);
        setDoors.setHoverName(Component.literal("Set Doors"));
        container.setItem(14, setDoors);

        ItemStack setPlayerHead = new ItemStack(Items.PLAYER_HEAD);
        setPlayerHead.setHoverName(Component.literal("Set Player Head").withStyle(ChatFormatting.WHITE));
        container.setItem(15, setPlayerHead);

        ItemStack toggleName = new ItemStack(Items.NAME_TAG);
        toggleName.setHoverName(Component.literal("Toggle Name"));
        container.setItem(16, toggleName);

        return container;
    }

    @Override
    public void handleClick(int slotId, ClickType clickType, ServerPlayer player) {
        switch (slotId) {
            case 10 -> startGame(player);
            case 14 -> player.sendSystemMessage(Component.literal("Set Doors clicked!"));
            case 15 -> player.sendSystemMessage(Component.literal("Set Player Head clicked!"));
            case 16 -> {
                player.sendSystemMessage(Component.literal("Toggle Name clicked!"));
                NameToggleUtil.togglePlayerName(player);
            }

        }
    }

}

