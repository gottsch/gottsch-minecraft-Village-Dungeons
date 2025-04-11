package mod.gottsch.forge.villagedungeons.core.mixin;

import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import mod.gottsch.forge.villagedungeons.core.registry.VillageRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.mutable.MutableObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author by Mark Gottschling on 4/6/2025
 */
@Mixin(targets = {"net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement$Placer"})
public class PreventDuplicateVillageDungeonMixin {

    @Unique
    private boolean isDungeonPlaced = false;

    @Inject(
            method = "tryPlacingChildren",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z", ordinal = 1, shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILSOFT)
    /*
     * NOTE ignore this incorrect signature error. it is correct as of 1.20.1 47.4.0.
     * for some reason it tries to switch the order of the last holder and boolean values.
     */
    private void villageDungeons_tryPlacingChildren(PoolElementStructurePiece startingPiece, MutableObject<VoxelShape> p_227266_, int p_227267_, boolean p_227268_, LevelHeightAccessor p_227269_, RandomState p_227270_, CallbackInfo ci,
                                                    StructurePoolElement $$6, BlockPos $$7, Rotation $$8, StructureTemplatePool.Projection $$9, boolean $$10, MutableObject $$11, BoundingBox $$12, int $$13,
                                                    Iterator var15, StructureTemplate.StructureBlockInfo $$14, Direction $$15, BlockPos $$16,
                                                    BlockPos $$17, int $$18, int $$19, ResourceKey<StructureTemplatePool> $$20,
                                                    Optional<? extends Holder<StructureTemplatePool>> $$21, Holder<StructureTemplatePool> $$22, Holder<StructureTemplatePool> $$23, boolean $$24, MutableObject<VoxelShape> $$26,
                                                    List<StructurePoolElement> list) {

        // see if it is a village template, and skip if not.
        if (startingPiece.getElement() instanceof SinglePoolElement) {
            if (((ModSinglePoolElementMixin) startingPiece.getElement()).getTemplate().left().map(t -> t.getPath().toLowerCase().contains("village")).orElse(false)) {
                List<StructurePoolElement> distinctModTemplates = list.stream()
                        .filter(t -> {
                            if (t instanceof SinglePoolElement) {
                                return ((ModSinglePoolElementMixin) t).getTemplate().left().map(m -> m.getNamespace().equalsIgnoreCase(VillageDungeons.MOD_ID)).orElse(false);
                            }
                            return false;
                        })
                        .distinct()
                        .toList();

                // if any mod templates exist
                if (!distinctModTemplates.isEmpty()) {
                    // filter out mod templates from the existing list
                    List<StructurePoolElement> filteredElements = list.stream()
                            .filter(t -> {
                                if (t instanceof SinglePoolElement) {
                                    return ((ModSinglePoolElementMixin) t).getTemplate().left().map(m -> !m.getNamespace().equalsIgnoreCase(VillageDungeons.MOD_ID)).orElse(true);
                                }
                                return true;
                            })
                            .toList();

                    if (!isDungeonPlaced) {
                        // clear the existing list
                        list.clear();
                        // add mod templates to the front of the list
                        list.addAll(distinctModTemplates);
                        // re-add vanilla templates
                        list.addAll(filteredElements);
                        isDungeonPlaced = true;

                        // register dungeon
                        VillageRegistry.DungeonInfo info = new VillageRegistry.DungeonInfo();
                        info.setPosition(startingPiece.getPosition());

                        VillageRegistry.register(startingPiece.getPosition(), info);

                        // TODO need to save the dungeon bounding box - probably on return from tryPlacingChildren() method
                    }
                }
            }
        }
    }
}
