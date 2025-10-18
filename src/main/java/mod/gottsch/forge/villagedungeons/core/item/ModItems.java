package mod.gottsch.forge.villagedungeons.core.item;

import mod.gottsch.forge.villagedungeons.core.block.ModBlocks;
import mod.gottsch.forge.villagedungeons.core.setup.Registration;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static mod.gottsch.forge.villagedungeons.core.setup.Registration.RAT_ENTITY_TYPE;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
public class ModItems {
    // mod eggs
    // TODO colors should be dark gray and pink
    public static final RegistryObject<Item> RAT_EGG = Registration.ITEMS.register("rat_egg", () -> new ForgeSpawnEggItem(RAT_ENTITY_TYPE, 0xacacac, 0x6f5e48, new Item.Properties()));

    public static RegistryObject<Item> SEWER_AIR = fromBlock(ModBlocks.SEWER_AIR, () -> new Item.Properties());

    // convenience method: take a RegistryObject<Block> and make a corresponding RegistryObject<Item> from it
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block, Supplier<Item.Properties> itemProperties) {
        return Registration.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), itemProperties.get()));
    }

    public static void register(IEventBus bus) {
        Registration.ITEMS.register(bus);
    }
}
