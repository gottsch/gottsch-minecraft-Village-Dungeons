package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.templatesystem.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

/**
 * @author by Mark Gottschling on 10/16/2025
 */
public record ProbabilityAgingBlock(ResourceLocation block, double probability) {
    // a Codec to parse this data from a JSON file.
    public static final Codec<ProbabilityAgingBlock> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("block").forGetter(ProbabilityAgingBlock::block),
            Codec.DOUBLE.optionalFieldOf("probability", 0.0).forGetter(ProbabilityAgingBlock::probability)
    ).apply(instance, ProbabilityAgingBlock::new));
}
