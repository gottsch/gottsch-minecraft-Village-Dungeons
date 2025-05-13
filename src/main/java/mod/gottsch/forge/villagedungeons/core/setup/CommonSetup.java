package mod.gottsch.forge.villagedungeons.core.setup;

import mod.gottsch.forge.ddenizensapi.core.entity.monster.Rat;
import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import mod.gottsch.forge.villagedungeons.core.config.Config;
import mod.gottsch.forge.villagedungeons.core.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
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
//            event.accept(ModItems.WAXED_COPPER_GRATE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        }

        else if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.RAT_EGG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    /**
     * attach defined attributes to the entity.
     * @param event
     */
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(Registration.RAT_ENTITY_TYPE.get(), Rat.createAttributes().build());
    }
}

