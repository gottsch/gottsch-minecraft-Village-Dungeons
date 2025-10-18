package mod.gottsch.forge.villagedungeons.core.mixin;

import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

/**
 * @author by Mark Gottschling on 4/4/2025
 */
@Mixin(JigsawPlacement.class)
public class ModJigsawPlacementMixin {
    // TODO this will have to be a list of a regex matcher
    @Unique
    private static final ResourceLocation village_Dungeons_1_20_1$plainsLargeTavern1 = new ResourceLocation(VillageDungeons.MOD_ID, "village/plains_large_tavern_1_with_cellar");

    @Inject(
            method = "addPieces(Lnet/minecraft/world/level/levelgen/structure/Structure$GenerationContext;Lnet/minecraft/core/Holder;Ljava/util/Optional;ILnet/minecraft/core/BlockPos;ZLjava/util/Optional;I)Ljava/util/Optional;",
            at = @At(value = "INVOKE_ASSIGN",
            target = "Ljava/util/Optional;isPresent()Z"),
//                    target ="Lnet/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool;getRandomTemplate(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/level/levelgen/structure/pools/StructurePoolElement;", shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void villageDungeons_addPieces(Structure.GenerationContext p_227239_, Holder<StructureTemplatePool> p_227240_, Optional<ResourceLocation> p_227241_, int p_227242_, BlockPos pos, boolean p_227244_, Optional<Heightmap.Types> p_227245_, int p_227246_,
                                                  CallbackInfoReturnable<Optional<Structure.GenerationStub>> cir,
                                                  RegistryAccess $$8,
                                                  ChunkGenerator $$9,
                                                  StructureTemplateManager $$10,
                                                  LevelHeightAccessor $$11, WorldgenRandom $$12,
                                                  Registry<StructureTemplatePool> $$13,
                                                  Rotation $$14,
                                                  StructureTemplatePool $$15,
                                                  StructurePoolElement structurePoolElement) {

        // NOTE this method is responsible for adding the starting pieces, and indireclty calls tryPlacingChildren(),
        // which is actually responsible for adding the child pieces (ie tavern).

        // NOTE use this method to setup the registration of the village.
        // NOTE piece.position = blockPos of the piece
        // NOTE blockpos1 is the pos of the starting piece ie the key to the registry
        // NOTE in tryPlacingChlidren, blockpos is the startingPiece blockPos
        // TODO problems:
        /*
        the blockpos or the starting piece is not pass down.  unless it is contained within the piece itself?
        this is the MOST IMPORTANT data. it is used as the key to the Village/Dungeon Registry
         */
//        VillageDungeons.LOGGER.info("pos -> {}", pos);
//        VillageDungeons.LOGGER.info("optional resource location -> {}", p_227241_);

        /// ////////////
//        ModSinglePoolElementMixin elementMixin = (ModSinglePoolElementMixin) structurePoolElement;
//        if (elementMixin.getTemplate().left().isPresent()) {
//            if (elementMixin.getTemplate().left().get().equals(village_Dungeons_1_20_1$plainsLargeTavern1)) {
//                // TODO
//                cir.setReturnValue(Optional.empty());
//            }
//        }
    }
}
