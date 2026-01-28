package mrskyzz.botc;

// TODO : Remake lang accordingly ..
// TODO : When "killing" a player, darken out his name

import com.mojang.logging.LogUtils;
import mrskyzz.botc.block.ModBlockEntities;
import mrskyzz.botc.block.ModBlocks;
import mrskyzz.botc.item.ModCreativeModTabs;
import mrskyzz.botc.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Botc.MOD_ID)
public class Botc {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "botc";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static GameState gameState = GameState.SETUP;

    public Botc(FMLJavaModLoadingContext context) {
        var modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        // modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    public enum GameState {
        WAITING,
        /// Waiting in lobby for players to join before the game starts
        SETUP,
        /// Assigning roles, initializing data, teleporting players
        FIRST_NIGHT,
        /// Special first night where extra roles act
        NIGHT,
        /// Regular night phase where roles perform their actions
        MORNING,
        /// Night results are revealed (deaths, info), transition to day
        DAY,
        /// Open discussion phase for all living players
        NOMINATION,
        /// Players nominate others and vote for execution
        EXECUTION,
        /// Execution is carried out and win conditions are checked
        GAME_OVER
        /// Game has ended and the winning team is announced
    }

}
