package mod.gottsch.forge.villagedungeons.core.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * copied vanilla from 1.21.1
 * @author by Mark Gottschling on 3/29/2025
 */
public class TransparentBlock extends HalfTransparentBlock {
//    public static final MapCodec<TransparentBlock> CODEC = simpleCodec(TransparentBlock::new);

    public TransparentBlock(BlockBehaviour.Properties p_312723_) {
        super(p_312723_);
    }

//    @Override
//    protected MapCodec<? extends TransparentBlock> codec() {
//        return CODEC;
//    }

    @Override
    public VoxelShape getVisualShape(BlockState p_312193_, BlockGetter p_310654_, BlockPos p_310658_, CollisionContext p_311129_) {
        return Shapes.empty();
    }

    @Override
    public float getShadeBrightness(BlockState p_312407_, BlockGetter p_310193_, BlockPos p_311965_) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_312717_, BlockGetter p_312877_, BlockPos p_312899_) {
        return true;
    }
}
