package mrskyzz.botc.game;

import mrskyzz.botc.utils.DoorSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
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
    private final Map<BlockPos, CompoundTag> storedBlockNBT = new HashMap<>();
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

//    public static Door loadFromNBT(CompoundTag tag) {
//        Door door = new Door(
//                tag.getString("Name"),
//                BlockPos.of(tag.getLong("Pos1")),
//                BlockPos.of(tag.getLong("Pos2"))
//        );
//
//        door.open = tag.getBoolean("Open");
//        return door;
//    }

    public static Door loadFromNBT(CompoundTag tag) {
        Door door = new Door(
                tag.getString("Name"),
                BlockPos.of(tag.getLong("Pos1")),
                BlockPos.of(tag.getLong("Pos2"))
        );

        door.open = tag.getBoolean("Open");

        if (tag.contains("Blocks")) {
            ListTag blocksList = tag.getList("Blocks", Tag.TAG_COMPOUND);
            for (int i = 0; i < blocksList.size(); i++) {
                CompoundTag blockTag = blocksList.getCompound(i);
                BlockPos pos = BlockPos.of(blockTag.getLong("Pos"));
                door.storedBlockNBT.put(pos, blockTag.getCompound("State"));
            }
        }

        return door;
    }

//    public CompoundTag saveToNBT() {
//        CompoundTag tag = new CompoundTag();
//
//        tag.putString("Name", name);
//        tag.putLong("Pos1", pos1.asLong());
//        tag.putLong("Pos2", pos2.asLong());
//        tag.putBoolean("Open", open);
//
//        return tag;
//    }

    public CompoundTag saveToNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putString("Name", name);
        tag.putLong("Pos1", pos1.asLong());
        tag.putLong("Pos2", pos2.asLong());
        tag.putBoolean("Open", open);

        ListTag blocksList = new ListTag();
        for (Map.Entry<BlockPos, BlockState> entry : storedBlocks.entrySet()) {
            CompoundTag blockTag = new CompoundTag();
            blockTag.putLong("Pos", entry.getKey().asLong());
            blockTag.put("State", NbtUtils.writeBlockState(entry.getValue()));
            blocksList.add(blockTag);
        }

        tag.put("Blocks", blocksList);
        return tag;
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

    public void resolveStoredBlocks(Level level) {
        storedBlocks.clear();

        var lookup = level.registryAccess().lookupOrThrow(Registries.BLOCK);

        for (Map.Entry<BlockPos, CompoundTag> entry : storedBlockNBT.entrySet()) {
            BlockState state = NbtUtils.readBlockState(lookup, entry.getValue());
            storedBlocks.put(entry.getKey(), state);
        }

        storedBlockNBT.clear(); // free memory
    }


    /* -------------------------
       Toggle logic
       ------------------------- */

//    public void toggle(Level level) {
//        if (open) {
//            close(level);
//        } else {
//            open(level);
//        }
//
//        open = !open;
//    }

    public void toggle(Level level) {
        if (open) {
            close(level);
        } else {
            open(level);
        }

        open = !open;

        if (!level.isClientSide()) {
            DoorSavedData.get((ServerLevel) level).setDirty();
        }
    }


    public void open(Level level) {
        for (BlockPos pos : storedBlocks.keySet()) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    public void close(Level level) {
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
