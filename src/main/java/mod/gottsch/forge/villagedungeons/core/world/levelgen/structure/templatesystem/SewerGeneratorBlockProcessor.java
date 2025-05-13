/*
 * This file is part of  Village Dungeons.
 * Copyright (c) 2025 Mark Gottschling (gottsch)
 *
 * Village Dungeons is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Village Dungeons is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Village Dungeons.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.templatesystem;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 . * @author by Mark Gottschling on 3/29/2025
 */
public class SewerGeneratorBlockProcessor extends StructureProcessor {
    public static final Codec<SewerGeneratorBlockProcessor> CODEC = Codec.unit(SewerGeneratorBlockProcessor::new);

    public SewerGeneratorBlockProcessor() {
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos pos, BlockPos relativePos, StructureTemplate.StructureBlockInfo original, StructureTemplate.StructureBlockInfo current, StructurePlaceSettings placementSettings) {
        if (current.state().is(Blocks.PINK_STAINED_GLASS)) {

            // TODO initiate sewer generation
            // calculate the starting position based on the existing jigsaw block
            BlockPos startPos = pos.below();

            // TODO calculate the rotation based on the rotation of the current structure info

            // return air block
            return new StructureTemplate.StructureBlockInfo(original.pos(), Blocks.AIR.defaultBlockState(), null);
        }

        // process other blocks normally
        return current;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructureProcessors.SEWER_GENERATOR_PROCESSOR.get();
    }
}
