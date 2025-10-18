package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.templatesystem.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * @author by Mark Gottschling on 10/16/2025
 */
public record AgingRule(ResourceLocation block, List<ProbabilityAgingBlock> outputBlocks) {
    // a Codec to parse this data from a JSON file.
    public static final Codec<AgingRule> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("block").forGetter(AgingRule::block),
            ProbabilityAgingBlock.CODEC.listOf().fieldOf("output_blocks").forGetter(AgingRule::outputBlocks)
    ).apply(instance, AgingRule::new));
}
