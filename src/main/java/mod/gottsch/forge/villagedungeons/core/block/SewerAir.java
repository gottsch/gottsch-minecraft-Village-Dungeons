package mod.gottsch.forge.villagedungeons.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author by Mark Gottschling on 10/10/2025
 */
@Deprecated
// not worth it
public class SewerAir extends Block {


    public SewerAir(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

//    @Override
    public static boolean shouldRenderFace(BlockState state, BlockGetter level, BlockPos pos, Direction face, BlockPos facePos) {
        BlockState neighborState = level.getBlockState(facePos);
        // Only render the face if the neighboring block is NOT also sewer_fog
        if (neighborState.is(ModBlocks.SEWER_AIR.get())) {
            return false;
        }
        return Block.shouldRenderFace(state, level, pos, face, facePos);
    }
}
