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


//    player.setInvisible(!player.isInvisible());

public class BotcGameMasterMenu extends AbstractBotcMenu {
    private final int ROWS = 6;
    private final int COLS = 9;
    final int INVENTORY_SIZE = ROWS * COLS;

    public BotcGameMasterMenu(int id, Inventory playerInv) {
        super(MenuType.GENERIC_9x6, id);
        init(playerInv, 6);
    }

    @Override
    public SimpleContainer createContainer() {
        SimpleContainer container = new SimpleContainer(INVENTORY_SIZE);

        ItemStack filler = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);
        filler.setHoverName(Component.literal(""));
        AbstractBotcMenu.setContainerOutline(container, filler, INVENTORY_SIZE, COLS, ROWS);

        return container;
    }

    @Override
    public void handleClick(int slotId, ClickType clickType, ServerPlayer player) {

    }

}
