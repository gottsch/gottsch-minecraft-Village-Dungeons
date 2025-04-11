package mod.gottsch.forge.villagedungeons.core.mixin;

import com.mojang.datafixers.util.Either;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author by Mark Gottschling on 4/4/2025
 */
@Mixin(SinglePoolElement.class)
public interface ModSinglePoolElementMixin {

    @Accessor
    Either<ResourceLocation, StructureTemplate> getTemplate();
}
