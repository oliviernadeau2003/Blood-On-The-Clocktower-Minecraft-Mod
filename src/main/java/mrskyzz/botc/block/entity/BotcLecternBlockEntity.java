package mrskyzz.botc.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BotcLecternBlockEntity extends LecternBlockEntity {

    private ItemStack originalBook = ItemStack.EMPTY;

    public BotcLecternBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public ItemStack getOriginalBook() {
        // ✅ use vanilla accessor
        return originalBook.isEmpty()
                ? this.getBook().copy()
                : originalBook.copy();
    }

    public void setOriginalBook(ItemStack stack) {
        this.originalBook = stack.copy();
        this.setChanged();
    }

    @Override
    public void clearContent() {
        super.clearContent();
        originalBook = ItemStack.EMPTY;
    }
}
