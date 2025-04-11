package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import java.util.Optional;

/**
 * @author by Mark Gottschling on 3/28/2025
 */
@Deprecated
public class CustomHeightPlacement extends StructurePlacement {

    public static final Codec<CustomHeightPlacement> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("minY").forGetter(customHeightPlacement -> customHeightPlacement.minY),
                    Codec.INT.fieldOf("maxY").forGetter(customHeightPlacement -> customHeightPlacement.maxY)
            ).apply(instance, CustomHeightPlacement::new));

    public static final DeferredRegister<StructurePlacementType<?>> PLACEMENT_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_PLACEMENT.key(), "your_mod_id");

    public static final RegistryObject<StructurePlacementType<CustomHeightPlacement>> CUSTOM_HEIGHT_PLACEMENT = PLACEMENT_TYPES.register("custom_height_placement", () -> () -> CODEC);

    private final int minY;
    private final int maxY;

    public CustomHeightPlacement(int minY, int maxY) {
        super(
                new Vec3i(0, 0, 0), // p_227028_ - offset
                FrequencyReductionMethod.DEFAULT, // p_227029_ - frequencyReductionMethod
                1.0F, // p_227030_ - frequency
                0, // p_227031_ - salt
                Optional.empty() // p_227032_ - exclusionZone
        );
        this.minY = minY;
        this.maxY = maxY;
    }

    protected boolean isPlacementChunk(ChunkGeneratorStructureState structureState, int x, int z) {
        // Get the world position of the corner of the current chunk
        BlockPos chunkStartPos = new BlockPos(x * 16, 0, z * 16);

        // You might want to sample the height at a relevant point within the chunk
        // For simplicity, let's use the Y-coordinate of the chunk's starting position.
        // Be aware that this is a simplification and might not be accurate for all terrain.
        int sampleY = chunkStartPos.getY(); // This will likely be 0 unless you have custom chunk generation

        // A more robust approach might involve querying the ChunkGenerator for the height
        // at a specific (x, z) within the chunk.
        // Example (may need adjustments based on your ChunkGenerator):
        // int sampleY = chunkGeneratorStructureState.getBaseHeight(chunkX * 16 + 8, chunkZ * 16 + 8, Heightmap.Types.WORLD_SURFACE_WG);

        return sampleY >= minY && sampleY <= maxY;
    }

    @Override
    public StructurePlacementType<?> type() {
        return CUSTOM_HEIGHT_PLACEMENT.get();
    }

    public static void register(IEventBus bus) {
        PLACEMENT_TYPES.register(bus);
    }
}
