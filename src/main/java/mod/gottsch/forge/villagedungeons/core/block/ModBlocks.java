package mod.gottsch.forge.villagedungeons.core.block;

import mod.gottsch.forge.villagedungeons.core.setup.Registration;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
public class ModBlocks {

    // TODO MOVE to GottschCore
    public static final RegistryObject<Block> STRUCTURE_NEIGHBOR_DEPENDENT_STATE_MARKER = Registration.BLOCKS.register("structure_neighbor_dependent_state_marker", () -> new StructureNeighborDependentStateMarkerBlock(Block.Properties.of().replaceable().noCollission().noLootTable().air()));

    // air blocks
    public static final RegistryObject<Block> SEWER_AIR = Registration.BLOCKS.register("sewer_air",
            () -> new SewerAir(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT)
                    .air() // Can start with air properties or build custom
                    .noOcclusion() // Allows light/visibility through the block
                    .isViewBlocking((state, level, pos) -> false) // Important for view
                    .isValidSpawn((state, level, pos, entityType) -> false) // Prevent mobs from spawning
                    .noCollission() // Makes it non-solid like air
//                    .instabreak() // Can be instantly broken in creative
                    .replaceable() // Can be placed over without breaking
                    .noLootTable()
                    // Other properties like sound type, resistance, etc.
            ));

    public static void register(IEventBus bus) {
        Registration.BLOCKS.register(bus);
    }

}
