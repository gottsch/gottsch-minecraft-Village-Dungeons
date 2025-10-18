/*
 * This file is part of Treasure2.
 * Copyright (c) 2025 Mark Gottschling (gottsch)
 *
 * Treasure2 is free software: you can redistribute it and/or modify
 * it under the terms of the Open Software Licence 3.0.
 *
 * Treasure2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Open Software Licence 3.0 for more details.
 *
 * You should have received a copy of the Open Software Licence
 * along with Treasure2. If not, see <https://www.tldrlegal.com/license/open-software-licence-3-0>.
 */
package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.templatesystem;


import com.mojang.serialization.Codec;
import mod.gottsch.forge.gottschcore.block.FacingBlock;
import mod.gottsch.forge.villagedungeons.core.integration.ModIntegrations;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * @author by Mark Gottschling on 9/4/2025
 */
public class SewerDecoProcessor extends StructureProcessor {

    public static final Codec<SewerDecoProcessor> CODEC = Codec.unit(SewerDecoProcessor::new);

    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructureProcessors.SEWER_DECO_PROCESSOR.get();
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos pos, BlockPos relativePos, StructureTemplate.StructureBlockInfo original, StructureTemplate.StructureBlockInfo current, StructurePlaceSettings placementSettings) {
        RandomSource random = placementSettings.getRandom(current.pos());

        BlockState belowState = levelReader.getBlockState(current.pos().below());

        // check if in water
        if (current.state().is(Blocks.WATER)) { //getFluidState().isEmpty()) {
            // check if pos below is solid
            if (belowState.isSolidRender(levelReader, current.pos()) && belowState.getFluidState().isEmpty()) {
                // if random meets, place sea grass
                if (random.nextDouble() < 0.15) {
                    BlockState newState = Blocks.SEAGRASS.defaultBlockState();
                    return new StructureTemplate.StructureBlockInfo(current.pos(), newState, new CompoundTag());
                }
            }
        }

        // NOTE this won't work as the blocks are processed from the bottom up, so above
        //  will always be dirt or rock, etc. need to process on finalize. scan template again for
        //  top y values and check if dirt and then if below is air, add hanging roots.
        // if block above is dirt, then maybe place hanging roots (or other sewer roots)
//        BlockState aboveState = levelReader.getBlockState(current.pos().above());
        if (current.state().is(Blocks.DIRT)) {
            if (belowState.is(Blocks.AIR)) {
                if (random.nextDouble() < 0.5) {
                    BlockState newState = Blocks.HANGING_ROOTS.defaultBlockState();
                    return new StructureTemplate.StructureBlockInfo(current.pos().below(), newState, new CompoundTag());
                }
            }
        }

        // NOTE so far, this is the only time so far Dungeon Blocks is explicitly ref in code
         if (ModIntegrations.DungeonBlocks.isCorbel(current.state())) {
            // check behind for support
            BlockPos blockPos = pos.relative(current.state().getValue(FacingBlock.FACING).getOpposite());
            BlockState supportState = levelReader.getBlockState(blockPos);
            if (!supportState.isSolidRender(levelReader, blockPos) || !supportState.getFluidState().isEmpty()) {
                // remove corbel
                return new StructureTemplate.StructureBlockInfo(current.pos(), Blocks.AIR.defaultBlockState(), new CompoundTag());
            }

        }

        // TODO if door, does it have it's partner block


//
//        // calculate the block's new y position relative to the ground.
//        // we use relativePos.getY() to get the original height in the template. (kinda moot as all flowers are at relative = 0)
//        BlockPos newPos =  current.pos(); //new BlockPos(current.pos().getX(), groundY + relativePos.getY(), current.pos().getZ());
//        BlockState groundState = levelReader.getBlockState(newPos);
//
//        // 1. check the current pos if viable location
//        if (groundState.isSolidRender(levelReader, newPos) && groundState.getFluidState().isEmpty()) {
//            // check above, and if clear, move the pos up
//            if (levelReader.getBlockState(newPos.above()).canBeReplaced()) {
//                newPos = newPos.above();
//            } else {
//                return null;
//            }
//        }
//        else if (!groundState.is(Blocks.AIR)) {
//            return null;
//        }
//
//        BlockPos posBelow = newPos.below();
//        BlockState stateBelow = levelReader.getBlockState(posBelow);
//
//        // 2. if the block is not a gravestone base block, it needs to be a valid, empty spot to move down.
//        if (!stateBelow.is(TreasureTags.Blocks.GRAVESTONE_BASE)) {
//            // if the block is not replaceable or is a fluid, it's an invalid location.
//            if (!stateBelow.canBeReplaced() || !stateBelow.getFluidState().isEmpty()) {
//                return null;
//            }
//
//            // check if the block two positions below is a grass block.
//            if (levelReader.getBlockState(posBelow.below()).is(TreasureTags.Blocks.GRAVESTONE_BASE)) {
//                newPos = posBelow;
//            } else {
//                return null;
//            }
//        }
//
//        // select a gravestone or gravestone spawner
//        BlockState newState = null;
//        if (Config.SERVER.markers.enableSpawner.get() &&
//                RandomHelper.checkProbability(random, Config.SERVER.markers.spawnerProbability.get())) {
//            // grab a random spawner gravestone
//            newState = TreasureBlocks.GRAVESTONE_SPAWNERS.get(random.nextInt(TreasureBlocks.GRAVESTONE_SPAWNERS.size())).get().defaultBlockState();
//        } else {
//            // select a gravestone
//            newState = GRAVESTONES.get(random.nextInt(GRAVESTONES.size())).defaultBlockState();
//        }
//
//        // randomize direction
//        newState = newState.setValue(GravestoneBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random));

        return current;
    }

//    @Override
//    public List<StructureTemplate.StructureBlockInfo> finalizeProcessing(ServerLevelAccessor level, BlockPos spawnPos, BlockPos maxPos, List<StructureTemplate.StructureBlockInfo> originalBlocks, List<StructureTemplate.StructureBlockInfo> processedBlocks, StructurePlaceSettings placeSettings) {
//        List<BlockPos> removePos = new ArrayList<>();
//
//        for (StructureTemplate.StructureBlockInfo processedBlock : processedBlocks) {
//            if (processedBlock.state().is(TreasureBlocks.SKELETON.get())) {
//                BlockPos skeletonBottomPos = processedBlock.pos();
//                BlockPos newPos = skeletonBottomPos.relative(processedBlock.state().getValue(IFacingBlock.FACING));
//
//                // check new pos is valid location - air or replaceable with solid base
//                BlockState newState = level.getBlockState(newPos);
//                if ((newState.is(Blocks.AIR) || newState.canBeReplaced())
//                    && level.getBlockState(newPos.below()).is(TreasureTags.Blocks.GRAVESTONE_BASE)) {
//                    // check for water at the second position
//                    FluidState otherFluidState = level.getFluidState(newPos);
//                    boolean isWaterAtOther = otherFluidState.getType() == Fluids.WATER;
//
//                    // place the skeleton top part
//                    level.setBlock(newPos, processedBlock.state().setValue(SkeletonBlock.PART, SkeletonBlock.EnumPartType.TOP).setValue(SkeletonBlock.WATERLOGGED, isWaterAtOther), 0);
//                } else {
//                    // remove the skeleton
//                    removePos.add(skeletonBottomPos);
//                }
//            }
//        }
//
//        // Return a new list that doesn't contain the placeholder
//        return processedBlocks.stream()
//                .filter(block -> !removePos.contains(block.pos()))
//                .collect(Collectors.toList());
//    }
}
