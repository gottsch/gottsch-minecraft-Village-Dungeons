package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.templatesystem;


import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.gottsch.forge.gottschcore.util.ModUtil;
import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import mod.gottsch.forge.villagedungeons.core.block.ModBlocks;
import mod.gottsch.forge.villagedungeons.core.block.entity.StructureNeighborDependentStateMarkerBlockEntity;
import mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.templatesystem.data.AgingRule;
import mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.templatesystem.data.ProbabilityAgingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.*;

/**
 * A custom processor for Structures to handle blocks
 * -that need to retain their neighbor-dependent?? state, such as Stair and Wall blocks.
 * that need to retain their new state. ex stairs in a tunnel should not be waterlogged if built in water.
 * @author by Mark Gottschling on 8/15/2025
 */
public class DynamicStateAgedProcessor extends StructureProcessor {

    public static final Codec<DynamicStateAgedProcessor> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.optionalFieldOf("agings", 1).forGetter(processor -> processor.agings),
            AgingRule.CODEC.listOf().fieldOf("rules").forGetter(processor -> processor.rules)
    ).apply(instance, DynamicStateAgedProcessor::new));

    private final Integer agings;
    private final List<AgingRule> rules;

    private Map<Block, List<AgingRule>> map;

    public DynamicStateAgedProcessor(int agings, List<AgingRule> agingRules) {

        this.agings = agings;
        this.rules = agingRules;
        this.map = Maps.newHashMap();

        // convert rules into map by block
        rules.forEach(rule -> {
            // convert resourcelocation into block
            Block key = ForgeRegistries.BLOCKS.getValue(rule.block());
            map.computeIfAbsent(key, block -> new ArrayList<>()).add(rule);
        });
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos pos, BlockPos relativePos, StructureTemplate.StructureBlockInfo original, StructureTemplate.StructureBlockInfo current, StructurePlaceSettings settings) {
        Block currentBlock = current.state().getBlock();

        // 1. check if the block is eligible for processing (early exit)
        if (!map.containsKey(currentBlock)) {
            return current;
        }

        RandomSource random = settings.getRandom(current.pos());
        BlockState finalReplacementState = null; // the best result from any rule

        // 2. iterate through all mutually exclusive AgingRules (ex, Rule 1: Mossy Chain, Rule 2: Non-mossy Chain)
        for (AgingRule rule : this.map.get(currentBlock)) {

            BlockState bestResultFromRule = null; // Tracks the deepest successful decay stage for the current rule.

            // determine the maximum number of aging stages to check for this rule.
            int iterations = Math.min(this.agings, rule.outputBlocks().size());

            // 3. Inner Loop: Iterate through the sequential decay stages of the current rule
            for (int count = 0; count < iterations; count++) {
                ProbabilityAgingBlock output = rule.outputBlocks().get(count);

                if (random.nextDouble() <= output.probability()) {
                    // SUCCESS: decay stage achieved. this is the new 'best result'.
                    Block newBlock = ForgeRegistries.BLOCKS.getValue(output.block());
                    if (newBlock != null) {
                        bestResultFromRule = newBlock.defaultBlockState();
                    } else {
                        // fail-safe: if block ID is invalid but probability succeeded, treat as a successful chain end.
                        // the 'bestResultFromRule' remains the last known valid state (or null).
                        break;
                    }
                } else {
                    // FAIL: probability check failed. the decay chain STOPS here.
                    // the most recently successful state (bestResultFromRule) is the result of this rule.
                    break;
                }
            }

            // 4. check if this rule produced an decay (even one stage)
            if (bestResultFromRule != null) {
                // this rule (e.g., Rule 1) successfully decayed the block AT LEAST one stage.
                finalReplacementState = bestResultFromRule;

                // as this rule was successful, we stop checking all other AgingRules (e.g., Rule 2).
                break;
            }
            // if 'bestResultFromRule' is null, the block failed Rule 1's initial check, and the loop continues to Rule 2.
        }

        // short-circuit if aging was not applied
        if (finalReplacementState == null) {
            return current;
        }

        // TODO check the class of the block and update state
        if (finalReplacementState.getBlock() instanceof StairBlock) {
            finalReplacementState = updateStairs(current.state(), finalReplacementState);
        } else if (finalReplacementState.getBlock() instanceof WallBlock) {

        }

        return new StructureTemplate.StructureBlockInfo(current.pos(), finalReplacementState, current.nbt());
    }

/// //////////////////////
//        try {
//            if (!map.containsKey(current.state().getBlock())) {
//                return current;
//            }
//            RandomSource random = settings.getRandom(current.pos());
//            BlockState newState = null;
//            for (AgingRule rule : this.map.get(current.state().getBlock())) {
//                // calculate agings / iterations to attempt
//                int iterations = Math.min(this.agings, rule.outputBlocks().size());
//
//                for (int count = 0; count < iterations; count++) {
//                    if (random.nextDouble() <= rule.outputBlocks().get(count).probability()) {
//                        Block newBlock = ForgeRegistries.BLOCKS.getValue(rule.outputBlocks().get(count).block());
//                        if (newBlock != null) {
//                            newState = newBlock.defaultBlockState();
//                        }
//                    } else {
//                        break;
//                    }
//                }
//              if (newState != null) { break; }
//            }
//
//            return new StructureTemplate.StructureBlockInfo(current.pos(), newState, current.nbt());
/// /////////////////////////
//            BlockState currentState = current.state();
//            BlockState newState = null;
//
//            if (currentState.is(BlockTags.STAIRS)) {
//                List<Block> replacementBlocks = STAIR_REPLACEMENTS.get(currentState.getBlock());
//                if (replacementBlocks != null && !replacementBlocks.isEmpty()) {
//                    newState = maybeReplaceStairs(settings.getRandom(current.pos()), currentState, replacementBlocks);
//                    if (newState != null) {
//                        return new StructureTemplate.StructureBlockInfo(current.pos(), newState, current.nbt());
//                    }
//                }
//                return current;
//            }
//            // check if the block is a cobblestone wall and if a random probability passes
//            else if (currentState.is(BlockTags.WALLS)) {
//
//                if (currentState.is(Blocks.COBBLESTONE_WALL)) {
//                    newState = maybeReplaceWall(settings.getRandom(current.pos()), currentState, Blocks.MOSSY_COBBLESTONE_WALL.defaultBlockState());
//                } else if (currentState.is(Blocks.STONE_BRICK_WALL)) {
//                    newState = maybeReplaceWall(settings.getRandom(current.pos()), currentState, Blocks.MOSSY_STONE_BRICK_WALL.defaultBlockState());
//                }
//
//                // return the new info with the NBT tag
//                return Optional.ofNullable(newState)
//                        .map(state -> {
//                            CompoundTag nbt = new CompoundTag();
//                            nbt.putString(StructureNeighborDependentStateMarkerBlockEntity.TARGET_BLOCK,
//                                    ModUtil.getName(state.getBlock()).toString());
//                            return new StructureTemplate.StructureBlockInfo(
//                                    current.pos(),
//                                    ModBlocks.STRUCTURE_NEIGHBOR_DEPENDENT_STATE_MARKER.get().defaultBlockState(),
//                                    nbt
//                            );
//                        })
//                        .orElse(current);
//            }
//            return current;
//
//        } catch(Exception e) {
//            VillageDungeons.LOGGER.error(e);
//            throw e;
//        }
//    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructureProcessors.AGED_PROCESSOR.get();
    }

    private BlockState updateStairs(BlockState currentState, BlockState newState) {
            Direction direction = currentState.getValue(StairBlock.FACING);
            Half half = currentState.getValue(StairBlock.HALF);
//            FluidState fluidState = currentState.getFluidState();
//            if (!fluidState.isEmpty()) {
//                newState = newState.setValue(StairBlock.WATERLOGGED, true);
//            }
            return newState.setValue(StairBlock.FACING, direction).setValue(StairBlock.HALF, half).setValue(StairBlock.WATERLOGGED, false);
    }

}
