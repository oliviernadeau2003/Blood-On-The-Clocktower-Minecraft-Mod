package mrskyzz.botc.utils;

import mrskyzz.botc.game.Door;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.ArrayList;
import java.util.List;

public class DoorSavedData extends SavedData {

    private static final String DATA_NAME = "doors";
    private final List<Door> doors = new ArrayList<>();

    /* -------------------------
       Access
       ------------------------- */

    public static DoorSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                DoorSavedData::load,
                DoorSavedData::new,
                DATA_NAME
        );
    }

    /* -------------------------
       Persistence
       ------------------------- */

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();

        for (Door door : doors) {
            list.add(door.saveToNBT());
        }

        tag.put("Doors", list);
        return tag;
    }

    public static DoorSavedData load(CompoundTag tag) {
        DoorSavedData data = new DoorSavedData();
        ListTag list = tag.getList("Doors", CompoundTag.TAG_COMPOUND);

        for (int i = 0; i < list.size(); i++) {
            data.doors.add(Door.loadFromNBT(list.getCompound(i)));
        }

        return data;
    }

    /* -------------------------
       API
       ------------------------- */

    public List<Door> getDoors() {
        return doors;
    }

    public void addDoor(Door door) {
        doors.add(door);
        setDirty(); // VERY IMPORTANT
    }

    public void removeDoor(Door door) {
        doors.remove(door);
        setDirty();
    }
}
