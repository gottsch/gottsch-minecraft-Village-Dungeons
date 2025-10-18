package mod.gottsch.forge.villagedungeons.core.setup;

import mod.gottsch.forge.ddenizensapi.core.client.model.RatModel;
import mod.gottsch.forge.ddenizensapi.core.client.renderer.entity.RatRenderer;
import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import mod.gottsch.forge.villagedungeons.core.block.ModBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author by Mark Gottschling on 4/13/2025
 */
@Mod.EventBusSubscriber(modid = VillageDungeons.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
         event.enqueueWork(() -> {
            // for blended transparency (like stained glass, ice) - best for a colored 'fog'
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SEWER_AIR.get(), RenderType.translucent());
        });
    }

    // TODO how can this change to be more of an api call to DDApi?
    @SubscribeEvent()
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RatModel.LAYER_LOCATION, RatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.RAT_ENTITY_TYPE.get(), RatRenderer::new);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        // Define the greenish color: 0xAARRGGBB (Alpha, Red, Green, Blue)
        // For a fixed, slightly dark green tint. Alpha is ignored for blocks usually.
        // The value here is a 24-bit RGB integer (0xRRGGBB).
        int greenishTint = 0x507050; // A muted greenish-grey color

        // Create the BlockColor
        BlockColor sewerColorHandler = (blockState, blockAndTintGetter, blockPos, tintIndex) -> {
            // blockPos will be null if rendering as an item. We can return the fixed tint.
            return greenishTint;
        };

        /*
         register the BlockColor handlers
         */

        // block model json tintindex = 0
        event.register(sewerColorHandler, ModBlocks.SEWER_AIR.get());
    }
}
