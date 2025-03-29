package mod.gottsch.forge.villagedungeons.core.block;



import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

/**
 * copied vanilla from 1.21.1
 * @author by Mark Gottschling on 3/29/2025
 */
public class WeatheringCopperGrateBlock extends WaterloggedTransparentBlock implements WeatheringCopper {
//    public static final MapCodec<WeatheringCopperGrateBlock> CODEC = RecordCodecBuilder.mapCodec(
//            p_313130_ -> p_313130_.group(
//                            WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(WeatheringCopperGrateBlock::getAge), propertiesCodec()
//                    )
//                    .apply(p_313130_, WeatheringCopperGrateBlock::new)
//    );

    private final WeatheringCopper.WeatherState weatherState;

//    @Override
//    protected MapCodec<WeatheringCopperGrateBlock> codec() {
//        return CODEC;
//    }

    public WeatheringCopperGrateBlock(WeatheringCopper.WeatherState p_311827_, BlockBehaviour.Properties p_311858_) {
        super(p_311858_);
        this.weatherState = p_311827_;
    }

    @Override
    public void randomTick(BlockState p_309962_, ServerLevel p_309911_, BlockPos p_311585_, RandomSource p_310772_) {
//        this.changeOverTime(p_309962_, p_309911_, p_311585_, p_310772_);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_310531_) {
        return false;
    }

    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }
}
