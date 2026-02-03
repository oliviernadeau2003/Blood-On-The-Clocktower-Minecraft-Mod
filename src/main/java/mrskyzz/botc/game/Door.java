package mrskyzz.botc.game;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class Door {

    private final BlockPos pos1;
    private final BlockPos pos2;
    // Stores the original blocks of the door
    private final Map<BlockPos, BlockState> storedBlocks = new HashMap<>();
    private String name;
    // false = closed (blocks placed), true = open (air)
    private boolean open = false;

    public Door(String name, BlockPos pos1, BlockPos pos2) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    /* -------------------------
       Initialization
       ------------------------- */

    public static Door loadFromNBT(CompoundTag tag) {
        Door door = new Door(
                tag.getString("Name"),
                BlockPos.of(tag.getLong("Pos1")),
                BlockPos.of(tag.getLong("Pos2"))
        );

        door.open = tag.getBoolean("Open");
        return door;
    }

    /**
     * Captures the blocks in the door area.
     * Call this ONCE after creating the door.
     */
    public void capture(Level level) {
        storedBlocks.clear();

        for (BlockPos pos : BlockPos.betweenClosed(pos1, pos2)) {
            BlockState state = level.getBlockState(pos);

            if (!state.isAir()) {
                storedBlocks.put(pos.immutable(), state);
            }
        }
    }

    public CompoundTag saveToNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putString("Name", name);
        tag.putLong("Pos1", pos1.asLong());
        tag.putLong("Pos2", pos2.asLong());
        tag.putBoolean("Open", open);

        return tag;
    }


    /* -------------------------
       Toggle logic
       ------------------------- */

    public void toggle(Level level) {
        if (open) {
            close(level);
        } else {
            open(level);
        }

        open = !open;
    }

    private void open(Level level) {
        for (BlockPos pos : storedBlocks.keySet()) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    private void close(Level level) {
        for (Map.Entry<BlockPos, BlockState> entry : storedBlocks.entrySet()) {
            level.setBlock(entry.getKey(), entry.getValue(), 3);
        }
    }

    /* -------------------------
       Getters / setters
       ------------------------- */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlockPos getPos1() {
        return pos1;
    }

    public BlockPos getPos2() {
        return pos2;
    }

    public boolean isOpen() {
        return open;
    }
}
