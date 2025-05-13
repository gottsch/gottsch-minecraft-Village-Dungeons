package mod.gottsch.forge.villagedungeons.core.setup;

import mod.gottsch.forge.ddenizensapi.core.client.model.RatModel;
import mod.gottsch.forge.ddenizensapi.core.client.renderer.entity.RatRenderer;
import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author by Mark Gottschling on 4/13/2025
 */
@Mod.EventBusSubscriber(modid = VillageDungeons.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    // TODO how can this change to be more of an api call to DDApi?
    @SubscribeEvent()
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RatModel.LAYER_LOCATION, RatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.RAT_ENTITY_TYPE.get(), RatRenderer::new);
    }
}
