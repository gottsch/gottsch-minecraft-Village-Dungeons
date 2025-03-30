package mod.gottsch.forge.villagedungeons.core.item;

import mod.gottsch.forge.villagedungeons.core.block.ModBlocks;
import mod.gottsch.forge.villagedungeons.core.setup.Registration;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * @author by Mark Gottschling on 3/29/2025
 */
public class ModItems {

    public static final RegistryObject<Item> WEATHERED_COPPER_GRATE = fromBlock(ModBlocks.WEATHERED_COPPER_GRATE, Item.Properties::new);

    // convenience method: take a RegistryObject<Block> and make a corresponding RegistryObject<Item> from it
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block, Supplier<Item.Properties> itemProperties) {
        return Registration.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), itemProperties.get()));
    }

    public static void register(IEventBus bus) {
        Registration.ITEMS.register(bus);
    }
}
