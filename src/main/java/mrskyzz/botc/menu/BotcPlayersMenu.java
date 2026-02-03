package mrskyzz.botc.menu;

import mrskyzz.botc.utils.AbstractBotcMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BotcPlayersMenu extends AbstractBotcMenu {
    private final int ROWS = 5;
    private final int COLS = 9;
    final int INVENTORY_SIZE = ROWS * COLS;
    private final ServerPlayer player;

    public BotcPlayersMenu(int id, Inventory playerInv, ServerPlayer player) {
        super(MenuType.GENERIC_9x5, id);
        this.player = player;

        init(playerInv, ROWS);
    }

    @Override
    public SimpleContainer createContainer(Inventory playerInv) {
        SimpleContainer container = new SimpleContainer(INVENTORY_SIZE);

        ItemStack filler = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);
        filler.setHoverName(Component.literal(""));

        AbstractBotcMenu.setContainerOutline(container, filler, INVENTORY_SIZE, COLS, ROWS);


//        Botc.LOGGER.info("There is " + players.size() + " players");
        setPlayersHead(container);

        return container;
    }

    private void setPlayersHead(SimpleContainer container) {
        int index = 0;
        var players = player.server.getPlayerList().getPlayers();
        //! // TODO  [2026-01-30] : Make sure to remove the admin player head from the menu
//        var players = player.server.getPlayerList().getPlayers().stream()
//                .filter(p -> p != player) // exclude the Game Master (player who opened the book)
//                .toList();

        for (int row = 1; row < ROWS - 1; row++) {
            for (int col = 1; col < COLS - 1; col++) {
                if (index >= players.size()) return;

                int slot = row * COLS + col;
                ServerPlayer target = players.get(index++);

                ItemStack head = new ItemStack(Items.PLAYER_HEAD);
                head.getOrCreateTag().putString(
                        "SkullOwner",
                        target.getGameProfile().getName()
                );

                container.setItem(slot, head);
            }
        }
    }

    @Override
    public void handleClick(int slotId, ClickType clickType, ServerPlayer player) {
    }

}
