package mrskyzz.botc.menu;

import mrskyzz.botc.utils.AbstractBotcMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;

public class BotcAdminGameMenu extends AbstractBotcMenu {

    public BotcAdminGameMenu(int id, Inventory playerInv) {
        super(MenuType.GENERIC_9x6, id, playerInv, 6);
    }


    @Override
    public SimpleContainer createContainer() {
        final int INVENTORY_SIZE = 54;
        SimpleContainer container = new SimpleContainer(INVENTORY_SIZE);
        return container;
    }

    @Override
    public void handleClick(int slotId, ClickType clickType, ServerPlayer player) {

    }
}
