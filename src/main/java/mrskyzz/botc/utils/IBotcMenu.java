package mrskyzz.botc.utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ClickType;

public interface IBotcMenu {

    /**
     * Create and fill the menu container
     */
    SimpleContainer createContainer();

    /**
     * Handle menu slot clicks
     */
    void handleClick(int slotId, ClickType clickType, ServerPlayer player);
}
