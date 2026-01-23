package mrskyzz.botc.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BotcMenu extends AbstractContainerMenu {

    // PLAYER NAME VISIBILITY
    private static final String HIDE_NAME_TEAM = "hide_name";
    private final SimpleContainer container;

    public BotcMenu(int id, Inventory playerInv) {
        super(MenuType.GENERIC_9x3, id);
        this.container = createFilledContainer();

        // Menu slots (3x9)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int index = col + row * 9;
                this.addSlot(new Slot(container, index, 8 + col * 18, 18 + row * 18) {
                    @Override
                    public boolean mayPickup(Player player) {
                        return false; // Prevent grabbing menu items
                    }
                });
            }
        }

        // Player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9,
                        8 + col * 18, 84 + row * 18));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col,
                    8 + col * 18, 142));
        }
    }

    // 🔹 Items inside the menu
    private SimpleContainer createFilledContainer() {
        SimpleContainer container = new SimpleContainer(27);

        ItemStack filler = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);
        filler.setHoverName(Component.literal(""));

        for (int i = 0; i < 27; i++) {
            container.setItem(i, filler.copy());
        }

        ItemStack item = new ItemStack(Items.LIME_CONCRETE);
        item.setHoverName(Component.literal("START"));
        container.setItem(10, item);

        // NAME TAG
        item = new ItemStack(Items.NAME_TAG);
        item.setHoverName(Component.literal("TOGGLE NAME"));
        container.setItem(16, item);

        return container;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    // 🔹 Handle clicks
    @Override
    public void clicked(int slotId, int button, ClickType clickType, Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        if (slotId == 10) {
            player.sendSystemMessage(Component.literal("START clicked!"));
            return; // Cancel normal behavior
        } else if (slotId == 16) {
            player.sendSystemMessage(Component.literal("TOGGLE NAME clicked!"));
            NameToggleUtil.togglePlayerName(serverPlayer);
            return; // Cancel normal behavior
        }
        super.clicked(slotId, button, clickType, player);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

}

