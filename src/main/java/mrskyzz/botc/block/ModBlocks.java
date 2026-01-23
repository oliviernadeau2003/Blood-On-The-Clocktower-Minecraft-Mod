package mrskyzz.botc.block;

import mrskyzz.botc.Botc;
import mrskyzz.botc.block.custom.BotcLecternBlock;
import mrskyzz.botc.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static mrskyzz.botc.block.ModBlockEntities.BLOCK_ENTITIES;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Botc.MOD_ID);


//    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
//            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.HONEY_BLOCK)));

    public static final RegistryObject<Block> LECTERN_BLOCK = registerBlock("botc_lectern",
            ()-> new BotcLecternBlock(BlockBehaviour.Properties.copy(Blocks.LECTERN)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blocks){
        RegistryObject<T> toReturn = BLOCKS.register(name,blocks);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
