package mod.gottsch.forge.villagedungeons.core.setup;

import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import mod.gottsch.forge.villagedungeons.core.config.Config;
import mod.gottsch.forge.villagedungeons.core.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
@Mod.EventBusSubscriber(modid = VillageDungeons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    // TODO these will have to go under a Village Dungeons Tab
    @SubscribeEvent
    public static void registerItemsToTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.WEATHERED_COPPER_GRATE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        }
    }
}

