package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure.templatesystem;

import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
@Deprecated
public class ModStructureProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, VillageDungeons.MOD_ID);

    public static final RegistryObject<StructureProcessorType<DungeonAirProcessor>> DUNGEON_AIR_PROCESSOR = STRUCTURE_PROCESSORS.register(
            "dungeon_air", () -> () -> DungeonAirProcessor.CODEC
    );

    public static void register(IEventBus eventBus) {
        STRUCTURE_PROCESSORS.register(eventBus);
    }
}
