package mod.gottsch.forge.villagedungeons.core.setup;

import mod.gottsch.forge.ddenizensapi.core.entity.monster.Rat;
import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
public class Registration {
    /*
     * deferred registries
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VillageDungeons.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VillageDungeons.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VillageDungeons.MOD_ID);

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VillageDungeons.MOD_ID);

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, VillageDungeons.MOD_ID);

    private static final String STRUCTURE_ID = "villageDungeons:myStructure";
    private static final String VILLAGE_NBT_FLAG = "hasGenerated_" + STRUCTURE_ID.replace(":", "_");

    private static final String RAT = "rat";

    public static final RegistryObject<EntityType<Rat>> RAT_ENTITY_TYPE = Registration.ENTITIES.register(RAT, () -> EntityType.Builder.of(Rat::new, MobCategory.MONSTER)
            .sized(Rat.WIDTH, Rat.HEIGHT)
            .clientTrackingRange(8)
            .setShouldReceiveVelocityUpdates(false)
            .setTrackingRange(25)
            .build(RAT));

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ENTITIES.register(eventBus);
    }
}
