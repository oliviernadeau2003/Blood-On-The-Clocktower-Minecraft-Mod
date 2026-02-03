package mrskyzz.botc.utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractBotcMenu extends AbstractContainerMenu
        implements IBotcMenu {

    protected SimpleContainer container;

    protected AbstractBotcMenu(MenuType<?> type, int id) {
        super(type, id);
    }

    /** Called AFTER subclass construction */
    protected final void init(Inventory playerInv, int rows) {
        this.container = createContainer(playerInv);

        // Menu slots
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < 9; col++) {
                int index = col + row * 9;
                this.addSlot(new Slot(container, index,
                        8 + col * 18, 18 + row * 18) {
                    @Override
                    public boolean mayPickup(Player player) {
                        return false;
                    }
                });
            }
        }

        // Player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv,
                        col + row * 9 + 9,
                        8 + col * 18, 84 + row * 18));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col,
                    8 + col * 18, 142));
        }
    }

    public static void setContainerOutline(SimpleContainer container,ItemStack filler, int inventory_size, int cols, int rows) {
        // Top row
        for (int col = 0; col < cols; col++) {
            container.setItem(col, filler.copy());
        }

        // Bottom row
        int bottomRowStart = (rows - 1) * cols;
        for (int col = 0; col < cols; col++) {
            container.setItem(bottomRowStart + col, filler.copy());
        }

        // Left & right columns (skip corners to avoid double-setting)
        for (int row = 1; row < rows - 1; row++) {
            container.setItem(row * cols, filler.copy());
            container.setItem(row * cols + cols - 1, filler.copy());
        }
    }

    @Override
    public void clicked(int slotId, int button, ClickType clickType, Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            handleClick(slotId, clickType, serverPlayer);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public abstract SimpleContainer createContainer(Inventory playerInv);
}


//public abstract class AbstractBotcMenu extends AbstractContainerMenu implements IBotcMenu {
//
//    protected final SimpleContainer container;
//
//    protected AbstractBotcMenu(MenuType<?> type, int id, Inventory playerInv, int rows) {
//        super(type, id);
//        this.container = createContainer();
//
//        // Menu slots
//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < 9; col++) {
//                int index = col + row * 9;
//                this.addSlot(new Slot(container, index, 8 + col * 18, 18 + row * 18) {
//                    @Override
//                    public boolean mayPickup(Player player) {
//                        return false;
//                    }
//                });
//            }
//        }
//
//        // Player inventory
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 9; col++) {
//                this.addSlot(new Slot(playerInv, col + row * 9 + 9,
//                        8 + col * 18, 84 + row * 18));
//            }
//        }
//
//        // Hotbar
//        for (int col = 0; col < 9; col++) {
//            this.addSlot(new Slot(playerInv, col,
//                    8 + col * 18, 142));
//        }
//    }
//
//    public static void setContainerOutline(SimpleContainer container,ItemStack filler, int inventory_size, int cols, int rows) {
//        // Top row
//        for (int col = 0; col < cols; col++) {
//            container.setItem(col, filler.copy());
//        }
//
//        // Bottom row
//        int bottomRowStart = (rows - 1) * cols;
//        for (int col = 0; col < cols; col++) {
//            container.setItem(bottomRowStart + col, filler.copy());
//        }
//
//        // Left & right columns (skip corners to avoid double-setting)
//        for (int row = 1; row < rows - 1; row++) {
//            container.setItem(row * cols, filler.copy());
//            container.setItem(row * cols + cols - 1, filler.copy());
//        }
//    }
//
//    @Override
//    public void clicked(int slotId, int button, ClickType clickType, Player player) {
//        if (player instanceof ServerPlayer serverPlayer) {
//            handleClick(slotId, clickType, serverPlayer);
//        }
//    }
//
//    @Override
//    public ItemStack quickMoveStack(Player player, int index) {
//        return ItemStack.EMPTY;
//    }
//
//    @Override
//    public boolean stillValid(Player player) {
//        return true;
//    }
//}
