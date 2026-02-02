package mrskyzz.botc.menu;

import mrskyzz.botc.game.Door;
import mrskyzz.botc.game.Game;
import mrskyzz.botc.game.GameState;
import mrskyzz.botc.utils.AbstractBotcMenu;
import mrskyzz.botc.utils.NameToggleUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkHooks;

public class BotcStartupMenu extends AbstractBotcMenu {
    private final int ROWS = 3;
    private final int COLS = 9;
    final int INVENTORY_SIZE = ROWS * COLS;

    public BotcStartupMenu(int id, Inventory playerInv) {
        super(MenuType.GENERIC_9x3, id);
        init(playerInv, ROWS);
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
        SimpleContainer container = new SimpleContainer(INVENTORY_SIZE);

        ItemStack filler = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);
        filler.setHoverName(Component.literal(""));

        for (int i = 0; i < INVENTORY_SIZE; i++) {
            container.setItem(i, filler.copy());
        }

        ItemStack startItem = new ItemStack(Items.LIME_CONCRETE);
        startItem.setHoverName(Component.literal("Start Game").withStyle(ChatFormatting.GREEN));
        container.setItem(10, startItem);

        // Doors Logic -
        ItemStack setDoorsItem = new ItemStack(Items.SPRUCE_DOOR);
        setDoorsItem.setHoverName(Component.literal("Set Doors").withStyle(ChatFormatting.GRAY));

        // - Lore
        CompoundTag displayTag = setDoorsItem.getOrCreateTagElement("display");
        ListTag loreTag = new ListTag();

        for (Door door : Game.doors) {
            loreTag.add(StringTag.valueOf(
                    Component.Serializer.toJson(
                            Component.literal(door.getName())
                                    .withStyle(ChatFormatting.GREEN)
                    )
            ));
        }

        displayTag.put("Lore", loreTag);
        container.setItem(14, setDoorsItem);

        // ---

        ItemStack setPlayerHeadItem = new ItemStack(Items.PLAYER_HEAD);
        setPlayerHeadItem.setHoverName(Component.literal("Set Player Head").withStyle(ChatFormatting.GOLD));
        container.setItem(15, setPlayerHeadItem);

        ItemStack setToggleNameItem = new ItemStack(Items.NAME_TAG);
        setToggleNameItem.setHoverName(Component.literal("Toggle Name").withStyle(ChatFormatting.DARK_AQUA));
        container.setItem(16, setToggleNameItem);

        return container;
    }

    @Override
    public void handleClick(int slotId, ClickType clickType, ServerPlayer player) {
        switch (slotId) {
            case 10 -> startGame(player);
            case 14 -> {
                player.sendSystemMessage(Component.literal("Set Doors clicked!"));

                //! TEMP
                Game.toggleDoors(player.level());
            }
            case 15 -> {
                player.sendSystemMessage(Component.literal("Set Player Head clicked!"));

                //! TEMP
                NetworkHooks.openScreen(
                        player,
                        new SimpleMenuProvider(
                                (id, playerInv, p) -> new BotcPlayersMenu(id, playerInv, player),
                                Component.literal("Players - BOTC")
                        )
                );


            }
            case 16 -> {
                player.sendSystemMessage(Component.literal("Toggle Name clicked!"));
                NameToggleUtil.togglePlayerName(player);
            }

        }
    }

}

