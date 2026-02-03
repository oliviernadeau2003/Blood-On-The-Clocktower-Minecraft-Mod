package mrskyzz.botc.item.custom;

import mrskyzz.botc.game.Door;
import mrskyzz.botc.utils.DoorSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/// Add a second mode based on a tags for either set doors or set players head positions

public class LocatorStick extends Item {

    private final double blockReach = 5.0;  // Default range is 5 block
    private String doorName;
    private int doorCount = 1;
    private BlockPos firstPosition;
    private int interactionCount = 0;

    public LocatorStick(Properties pProperties) {
        super(pProperties);
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
//
//        // ✅ Client-only
//        if (level.isClientSide) {
//            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
//        }
//
//        // ❌ Only main hand should open menus
//        if (usedHand != InteractionHand.MAIN_HAND) {
//            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
//        }
//
//        // Reset / Clear Current Selection And Pass Interaction
//        if (player.isShiftKeyDown()) {
//            resetInteraction(player, true);
//            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
//        }
//
//        // Get block selected block
//        BlockPos block = getLookingBlockPos(player, level);
//
//        if (block != null) {
//            switch (interactionCount) {
//                case 0:
//                    firstPosition = block;
//                    interactionCount++;
//                    player.sendSystemMessage(Component.literal("First Block Selected"));
//                    break;
//                case 1:
//                    createNewDoorAndSave(firstPosition, block, level, serverLevel);
//                    player.sendSystemMessage(Component.literal("New Door Created Named : " + doorName));
//                    resetInteraction(player, false);
//                    doorCount++;
//                    break;
//            }
//        } else {
//            player.sendSystemMessage(Component.literal("No Block Selected"));
//        }
//
//        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), false);
//    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        // Client-only: do nothing
        if (level.isClientSide) {
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        // Only main hand
        if (usedHand != InteractionHand.MAIN_HAND) {
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        // ServerLevel is required from here on
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        // Reset selection
        if (player.isShiftKeyDown()) {
            resetInteraction(player, true);
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        // Get selected block
        BlockPos block = getLookingBlockPos(player, level);

        if (block != null) {
            switch (interactionCount) {
                case 0 -> {
                    firstPosition = block;
                    interactionCount++;
                    player.sendSystemMessage(Component.literal("First Block Selected"));
                }
                case 1 -> {
                    createNewDoorAndSave(firstPosition, block, serverLevel);
                    player.sendSystemMessage(Component.literal("New Door Created Named : " + doorName));
                    resetInteraction(player, false);
                    doorCount++;
                }
            }
        } else {
            player.sendSystemMessage(Component.literal("No Block Selected"));
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), false);
    }


//    private void createNewDoorAndSave(BlockPos firstPosition, BlockPos block, Level level, ServerLevel serverLevel) {
//        DoorSavedData data = DoorSavedData.get(serverLevel);
//
//        this.doorName = "Door " + doorCount;
//        Door newDoor = new Door(doorName, firstPosition, block);
//        newDoor.capture(level);
//        data.addDoor(newDoor);
//    }

    private void createNewDoorAndSave(BlockPos firstPosition, BlockPos block, ServerLevel level) {
        DoorSavedData data = DoorSavedData.get(level);

        this.doorName = "Door " + doorCount;
        Door newDoor = new Door(doorName, firstPosition, block);

        newDoor.capture(level);
        data.addDoor(newDoor);
    }


    private BlockPos getLookingBlockPos(Player player, Level level) {
        Vec3 start = player.getEyePosition(1.0F);
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = start.add(look.scale(blockReach));

        BlockHitResult result = level.clip(
                new ClipContext(
                        start,
                        end,
                        ClipContext.Block.OUTLINE, // Determines what blocks count
                        ClipContext.Fluid.NONE,    // Ignores fluids
                        player
                )
        );

        if (result.getType() == HitResult.Type.BLOCK) {
            return result.getBlockPos();
        }

        return null; // returns null if not looking at a block within range
    }

    private void resetInteraction(Player player, boolean showMessage) {
        interactionCount = 0;
        firstPosition = null;
        if (showMessage) {
            player.sendSystemMessage(Component.literal("Current Selection Have Been Cleared"));
        }
    }
}
