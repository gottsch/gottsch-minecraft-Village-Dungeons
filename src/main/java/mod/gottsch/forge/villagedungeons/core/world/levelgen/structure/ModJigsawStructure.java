package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;


/**
 * this is a copy of vanilla JigsawStructure since it is final
 * @author by Mark Gottschling on 8/21/2025
 */
public class ModJigsawStructure extends Structure {
    public static final int MAX_TOTAL_STRUCTURE_RANGE = 160;
    public static final Codec<ModJigsawStructure> CODEC = ExtraCodecs.validate(RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(settingsCodec(instance), StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter((structure) -> {
            return structure.startPool;
        }), ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter((structure) -> {
            return structure.startJigsawName;
        }), Codec.intRange(0, 20).fieldOf("size").forGetter((structure) -> {
            return structure.maxDepth;
        }), HeightProvider.CODEC.fieldOf("start_height").forGetter((structure) -> {
            return structure.startHeight;
        }), Codec.BOOL.fieldOf("use_expansion_hack").forGetter((structure) -> {
            return structure.useExpansionHack;
        }), Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter((structure) -> {
            return structure.projectStartToHeightmap;
        }), Codec.intRange(1, 160).fieldOf("max_distance_from_center").forGetter((structure) -> {
            return structure.maxDistanceFromCenter;
        }))
                .apply(instance, ModJigsawStructure::new);
    }), ModJigsawStructure::verifyRange).codec();

    protected final Holder<StructureTemplatePool> startPool;
    protected final Optional<ResourceLocation> startJigsawName;
    protected final int maxDepth;
    protected final HeightProvider startHeight;
    protected final boolean useExpansionHack;
    protected final Optional<Heightmap.Types> projectStartToHeightmap;
    protected final int maxDistanceFromCenter;

    protected static DataResult<ModJigsawStructure> verifyRange(ModJigsawStructure structure) {
        byte adaptationDistanceModifier;
        switch (structure.terrainAdaptation()) {
            case NONE:
                adaptationDistanceModifier = 0;
                break;
            case BURY:
            case BEARD_THIN:
            case BEARD_BOX:
                adaptationDistanceModifier = 12;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        int i = adaptationDistanceModifier;
        return structure.maxDistanceFromCenter + i > 160 ? DataResult.error(() -> {
            return "Structure size including terrain adaptation must not exceed 128";
        }) : DataResult.success(structure);
    }

    public ModJigsawStructure(StructureSettings structureSettings, Holder<StructureTemplatePool> templatePoolHolder, Optional<ResourceLocation> location, int maxDepth, HeightProvider heightProvider, boolean useExpansionHack,
                              Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter) {
        super(structureSettings);
        this.startPool = templatePoolHolder;
        this.startJigsawName = location;
        this.maxDepth = maxDepth;
        this.startHeight = heightProvider;
        this.useExpansionHack = useExpansionHack;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    public ModJigsawStructure(StructureSettings structureSettings, Holder<StructureTemplatePool> templatePoolHolder, int maxDepth, HeightProvider heightProvider, boolean useExpansionHack, Heightmap.Types types) {
        this(structureSettings, templatePoolHolder, Optional.empty(), maxDepth, heightProvider, useExpansionHack, Optional.of(types), 108);
    }

    public ModJigsawStructure(StructureSettings structureSettings, Holder<StructureTemplatePool> templatePoolHolder, int maxDepth, HeightProvider heightProvider, boolean useExpansionHack) {
        this(structureSettings, templatePoolHolder, Optional.empty(), maxDepth, heightProvider, useExpansionHack, Optional.empty(), 108);
    }

    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {

        // TODO could interrogate the structure resourelocation to extract the featureType


        // vanilla
        ChunkPos chunkpos = context.chunkPos();
        int i = this.startHeight.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));
        BlockPos blockpos = new BlockPos(chunkpos.getMinBlockX(), i, chunkpos.getMinBlockZ());
        return JigsawPlacement.addPieces(
                context,
                this.startPool,
                this.startJigsawName,
                this.maxDepth,
                blockpos,
                this.useExpansionHack,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter);
    }

//    @Override
//    public void afterPlace(WorldGenLevel level, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox box, ChunkPos chunkPos, PiecesContainer piecesContainer) {
//
//        // get the origin from the BoundingBox.
//        BlockPos origin = new BlockPos(box.minX(), box.minY(), box.minZ());
////        Treasure.LOGGER.debug("the origin pos (key) is -> {}", origin);
//        // remove any references from the single pass guards
//        ModProcessor.removeFromGuards(origin);
//
//        super.afterPlace(level, structureManager, chunkGenerator, random, box, chunkPos, piecesContainer);
//    }

    public StructureType<?> type() {
        return StructureType.JIGSAW;
    }
}
