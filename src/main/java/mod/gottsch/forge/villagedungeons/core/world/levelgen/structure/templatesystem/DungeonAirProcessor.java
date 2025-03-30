package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.templatesystem;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
public class DungeonAirProcessor extends StructureProcessor {
    public static final Codec<DungeonAirProcessor> CODEC = Codec.unit(DungeonAirProcessor::new);

    public DungeonAirProcessor() {
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos pos, BlockPos relativePos, StructureTemplate.StructureBlockInfo original, StructureTemplate.StructureBlockInfo current, StructurePlaceSettings placementSettings) {
        // keep air blocks as they are
        if (current.state().isAir()) {
            return current;
        }
        // process other blocks normally
        return current;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructureProcessors.DUNGEON_AIR_PROCESSOR.get();
    }
}
