package mod.gottsch.forge.villagedungeons.core.block;

import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

/**
 * copied vanilla from 1.21.1
 * @author by Mark Gottschling on 3/30/2025
 */
public class WeatheringCopperTrapDoorBlock extends TrapDoorBlock implements WeatheringCopper {

    private final WeatheringCopper.WeatherState weatherState;

    public WeatheringCopperTrapDoorBlock(BlockSetType blockSetType, WeatheringCopper.WeatherState weatherState, BlockBehaviour.Properties properties) {
        super(properties, blockSetType);
        this.weatherState = weatherState;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }
}
