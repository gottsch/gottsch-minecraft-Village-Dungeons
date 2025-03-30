package mod.gottsch.forge.villagedungeons.core.block;

import mod.gottsch.forge.villagedungeons.core.setup.Registration;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
public class ModBlocks {


    public static final Supplier<BlockBehaviour.Properties> COPPER_PROPS = () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(3.0F, 5.0F);
    public static final Supplier<BlockBehaviour.Properties> DEEPSLATE_ORE_PROPS = () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(3.0F, 6.0F);

    // copper blocks
    public static final RegistryObject<WeatheringCopperGrateBlock> WEATHERED_COPPER_GRATE =
            Registration.BLOCKS.register(
            "weathered_copper_grate", () ->
                            new WeatheringCopperGrateBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .isValidSpawn((a,b, c, d) -> false)
                    .isRedstoneConductor((a, b, c) ->false)
                    .isSuffocating((a, b, c) ->false)
                    .isViewBlocking((a, b, c) -> false)));


    public static void register(IEventBus bus) {
        Registration.BLOCKS.register(bus);
    }

}
