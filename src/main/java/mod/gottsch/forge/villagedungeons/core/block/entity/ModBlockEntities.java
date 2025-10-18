package mod.gottsch.forge.villagedungeons.core.block.entity;

import mod.gottsch.forge.villagedungeons.core.block.ModBlocks;
import mod.gottsch.forge.villagedungeons.core.setup.Registration;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author by Mark Gottschling on 10/9/2025
 */
public class ModBlockEntities {

    public static final RegistryObject<BlockEntityType<StructureNeighborDependentStateMarkerBlockEntity>> STRUCTURE_NEIGHBOR_DEPENDENT_STATE_MARKER =
            Registration.BLOCK_ENTITIES.register("structure_neighbor_dependent_state_marker",
                    () -> BlockEntityType.Builder.of(StructureNeighborDependentStateMarkerBlockEntity::new,
                            ModBlocks.STRUCTURE_NEIGHBOR_DEPENDENT_STATE_MARKER.get()
                    ).build(null));

    public static void register(IEventBus bus) {
        Registration.BLOCK_ENTITIES.register(bus);
    }

}
