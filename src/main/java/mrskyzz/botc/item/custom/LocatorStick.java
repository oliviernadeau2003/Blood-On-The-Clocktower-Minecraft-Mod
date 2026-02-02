package mrskyzz.botc.item.custom;

import mrskyzz.botc.game.Door;
import mrskyzz.botc.game.Game;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        // ✅ Client-only
        if (level.isClientSide) {
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        // ❌ Only main hand should open menus
        if (usedHand != InteractionHand.MAIN_HAND) {
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        // Reset / Clear Current Selection And Pass Interaction
        if (player.isShiftKeyDown()) {
            resetInteraction(player, true);
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        // Get block selected block
        BlockPos block = getLookingBlockPos(player, level);

        if (block != null) {
            switch (interactionCount) {
                case 0:
                    firstPosition = block;
                    interactionCount++;
                    player.sendSystemMessage(Component.literal("First Block Selected"));
                    break;
                case 1:
                    createNewDoorAndAdd(firstPosition, block, level);
                    player.sendSystemMessage(Component.literal("New Door Created Named : " + doorName));
                    resetInteraction(player, false);
                    doorCount++;
                    break;
            }
        } else {
            player.sendSystemMessage(Component.literal("No Block Selected"));
        }

//        if (block != null) {
//            player.sendSystemMessage(Component.literal(
//                    "Player looking at block (" +
//                            block.getX() + ", " +
//                            block.getY() + ", " +
//                            block.getZ() + ")"
//            ));
//        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), false);
    }

    private void createNewDoorAndAdd(BlockPos firstPosition, BlockPos block, Level level) {
        this.doorName = "Door " + doorCount;
        Door newDoor = new Door(doorName, firstPosition, block);
        Game.doors.add(newDoor);
        newDoor.save(level);
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
