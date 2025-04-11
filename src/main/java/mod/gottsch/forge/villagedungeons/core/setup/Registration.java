package mod.gottsch.forge.villagedungeons.core.setup;

import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
public class Registration {
    /*
     * deferred registries
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VillageDungeons.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VillageDungeons.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VillageDungeons.MOD_ID);

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, VillageDungeons.MOD_ID);

    private static final String STRUCTURE_ID = "villageDungeons:myStructure";
    private static final String VILLAGE_NBT_FLAG = "hasGenerated_" + STRUCTURE_ID.replace(":", "_");

}
