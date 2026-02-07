package mrskyzz.botc.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component.Serializer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

//? Should I Set It as a WrittenBookItem Or Item
public class RoleBookItem extends WrittenBookItem {

    private final String title;
    private final String author;
    private final List<Component> pages;

    public RoleBookItem(Properties properties, String title, String author, List<Component> pages) {
        super(properties);
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        // ✅ Client-only menu open
        if (level.isClientSide) {
            ItemStack book = createBook();
            Minecraft.getInstance().setScreen(
                    new net.minecraft.client.gui.screens.inventory.BookViewScreen(
                            net.minecraft.client.gui.screens.inventory.BookViewScreen.BookAccess.fromItem(book)
                    )
            );
        }

        // ✅ sidedSuccess prevents double execution issues
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), false);
    }

    // ADDED OWN LECTERN BLOCK
    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    public ItemStack createBook() {
        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);

        CompoundTag tag = new CompoundTag();
        tag.putString("title", title);
        tag.putString("author", author);

        ListTag pagesTag = new ListTag();

        for (Component component : pages) {
            pagesTag.add(StringTag.valueOf(Serializer.toJson(component)));
        }

        tag.put("pages", pagesTag);
        book.setTag(tag);

        return book;
    }
}
