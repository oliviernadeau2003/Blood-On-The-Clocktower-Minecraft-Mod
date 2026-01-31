package mrskyzz.botc.game;

import net.minecraft.core.BlockPos;

public class Door {

    private String name;
    private final BlockPos pos1;
    private final BlockPos pos2;

    public Door(String name, BlockPos pos1, BlockPos pos2) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

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
}

