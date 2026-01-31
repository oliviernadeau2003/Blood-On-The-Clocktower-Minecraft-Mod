package mrskyzz.botc;

// TODO  [2026-01-30] : Remake lang accordingly ..
// TODO  [2026-01-30] : When "killing" a player, darken out his name

/// Blood on the Clocktower (BotC) officially supports 5 to 20 players (plus one Storyteller). While the game technically functions at the minimum, it is generally considered best with 8 to 15 players, often requiring special rules (Travellers) for groups over 15.
/// Key details:
/// Optimal Range: 10-12 players is frequently cited as the ideal, well-balanced experience.
/// Small Groups (5-6 players): Known as "Teensyville," these games are faster and more intense, sometimes requiring specific, smaller character scripts.
/// Large Groups (16+): Uses "Travellers," which are characters designed for players who arrive late, leave early, or to balance very large groups.
/// The game is designed with a Storyteller acting as the moderator, bringing the total number of participants to 6-21

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
}
