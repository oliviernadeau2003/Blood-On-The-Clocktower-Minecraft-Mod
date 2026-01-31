package mrskyzz.botc.item;

import mrskyzz.botc.Botc;
import mrskyzz.botc.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Botc.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BOTC_TAB = CREATIVE_MODE_TABS.register("botc_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BOTC_BOOK.get())).title(Component.translatable("creativetab.botc_tab")).displayItems((itemDisplayParameters, output) -> {

        // ITEMS
        output.accept(ModItems.BOTC_BOOK.get());
        output.accept(ModItems.DEATH_PLAYER_HELMET.get());
        output.accept(ModItems.LOCATOR_STICK.get());

        // BOOKS ITEM ---
        // - DEMON
        output.accept(ModItems.BOOK_ROLE_DIABLOTIN.get());
        output.accept(ModItems.BOOK_ROLE_BARON.get());
        output.accept(ModItems.BOOK_ROLE_EMPOISONNEUR.get());
        output.accept(ModItems.BOOK_ROLE_ESPION.get());
        output.accept(ModItems.BOOK_ROLE_FEMME_ECARLATE.get());

        // - CITADINS
        output.accept(ModItems.BOOK_ROLE_ARCHIVISTE.get());
        output.accept(ModItems.BOOK_ROLE_CROQUE_MORT.get());
        output.accept(ModItems.BOOK_ROLE_CUISTOT.get());
        output.accept(ModItems.BOOK_ROLE_EMPATHE.get());
        output.accept(ModItems.BOOK_ROLE_ENQUETEUR.get());
        output.accept(ModItems.BOOK_ROLE_FOSSOYEUR.get());
        output.accept(ModItems.BOOK_ROLE_LAVANDIERE.get());
        output.accept(ModItems.BOOK_ROLE_MAIRE.get());
        output.accept(ModItems.BOOK_ROLE_MOINE.get());
        output.accept(ModItems.BOOK_ROLE_POURFENDEUR.get());
        output.accept(ModItems.BOOK_ROLE_SOLDAT.get());
        output.accept(ModItems.BOOK_ROLE_VIERGE.get());
        output.accept(ModItems.BOOK_ROLE_VOYANTE.get());
        output.accept(ModItems.BOOK_ROLE_VIERGE.get());

        // - ETRANGER
        //output.accept(ModItems.BOOK_ROLE_IVROGNE.get());
        output.accept(ModItems.BOOK_ROLE_RECLUS.get());
        output.accept(ModItems.BOOK_ROLE_SAINT.get());
        output.accept(ModItems.BOOK_ROLE_VIERGE.get());


        // BLOCKS
        //output.accept(ModBlocks.TEST_BLOCK.get());
        output.accept(ModBlocks.LECTERN_BLOCK.get());

    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
