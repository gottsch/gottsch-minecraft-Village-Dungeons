
package mod.gottsch.forge.villagedungeons.core.world.levelgen.structure;

import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author by Mark Gottschling on 8/16/2025
 */
public class ModStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, VillageDungeons.MOD_ID);

    public static final RegistryObject<StructureType<ModJigsawStructure>> DUNGEON_STRUCTURE =
            STRUCTURE_TYPES.register("dungeon_structure", () -> () -> ModJigsawStructure.CODEC);

    public static void register(IEventBus modEventBus) {
        STRUCTURE_TYPES.register(modEventBus);
    }
}
