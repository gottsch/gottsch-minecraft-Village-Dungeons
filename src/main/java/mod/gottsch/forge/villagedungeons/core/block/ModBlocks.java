package mod.gottsch.forge.villagedungeons.core.block;

import mod.gottsch.forge.villagedungeons.core.setup.Registration;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
public class ModBlocks {

    // copper blocks
//    public static final RegistryObject<Block> COPPER_GRATE =
//            Registration.BLOCKS.register(
//                    "copper_grate", () ->
//                            new WeatheringCopperGrateBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of()
//                                    .strength(3.0F, 6.0F)
//                                    .sound(SoundType.COPPER)
//                                    .mapColor(MapColor.WARPED_STEM)
//                                    .noOcclusion()
//                                    .requiresCorrectToolForDrops()
//                                    .isValidSpawn((a,b, c, d) -> false)
//                                    .isRedstoneConductor((a, b, c) ->false)
//                                    .isSuffocating((a, b, c) ->false)
//                                    .isViewBlocking((a, b, c) -> false)));
//
//    public static final RegistryObject<Block> EXPOSED_COPPER_GRATE =
//            Registration.BLOCKS.register(
//                    "exposed_copper_grate", () -> new WeatheringCopperGrateBlock(WeatheringCopper.WeatherState.EXPOSED,
//                            BlockBehaviour.Properties.copy(COPPER_GRATE.get())
//                                    .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)));
//
//    public static final RegistryObject<Block> WEATHERED_COPPER_GRATE =
//            Registration.BLOCKS.register(
//            "weathered_copper_grate", () ->
//                            new WeatheringCopperGrateBlock(WeatheringCopper.WeatherState.WEATHERED,
//                                    BlockBehaviour.Properties.copy(COPPER_GRATE.get())
//                                            .mapColor(MapColor.COLOR_ORANGE)));
//
//    public static final RegistryObject<Block> OXIDIZED_COPPER_GRATE =
//            Registration.BLOCKS.register(
//                    "oxidized_copper_grate", () -> new WeatheringCopperGrateBlock(WeatheringCopper.WeatherState.OXIDIZED,
//                            BlockBehaviour.Properties.copy(COPPER_GRATE.get())
//                                    .mapColor(MapColor.WARPED_NYLIUM)));
//
//
//    public static final RegistryObject<Block> WEATHERED_COPPER_TRAPDOOR = Registration.BLOCKS.register(
//            "weathered_copper_trapdoor",() ->
//            new WeatheringCopperTrapDoorBlock(
//                    BlockSetType.OAK, WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.copy(WEATHERED_COPPER_GRATE.get()))
//    );
//
//
//    public static final RegistryObject<Block> WAXED_COPPER_GRATE =
//            Registration.BLOCKS.register(
//                    "waxed_copper_grate", () -> new Block(BlockBehaviour.Properties.copy(COPPER_GRATE.get())));
//
//    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_GRATE =
//            Registration.BLOCKS.register(
//                    "waxed_exposed_copper_grate", () -> new Block(BlockBehaviour.Properties.copy(EXPOSED_COPPER_GRATE.get())));
//
//    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_GRATE =
//            Registration.BLOCKS.register(
//                    "waxed_weathered_copper_grate", () -> new Block(BlockBehaviour.Properties.copy(WEATHERED_COPPER_GRATE.get())));
//
//    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_GRATE =
//            Registration.BLOCKS.register(
//                    "waxed_oxidized_copper_grate", () -> new Block(BlockBehaviour.Properties.copy(COPPER_GRATE.get())));

    public static void register(IEventBus bus) {
        Registration.BLOCKS.register(bus);
    }

}
