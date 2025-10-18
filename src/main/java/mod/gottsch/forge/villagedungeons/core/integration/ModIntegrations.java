package mod.gottsch.forge.villagedungeons.core.integration;

import mod.gottsch.forge.dungeonblocks.core.block.ModBlocks;
import mod.gottsch.forge.dungeonblocks.core.tag.ModTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;

/**
 * @author by Mark Gottschling on 10/16/2025
 */
public class ModIntegrations {
    public static final String DUNGEONBLOCKS_MOD_ID = "dungeonblocks";

    public static boolean isLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static class DungeonBlocks {
        public static boolean isDungeonBlocksLoaded() {
            return isLoaded(DUNGEONBLOCKS_MOD_ID);
        }

        public static boolean isCorbel(BlockState state) {
            if (!isDungeonBlocksLoaded()) {
                return false;
            }

            return state.is(ModTags.Blocks.CORBELS);
        }

        public static boolean isLedge(BlockState state) {
            if (!isDungeonBlocksLoaded()) {
                return false;
            }

            return state.is(ModTags.Blocks.LEDGES);
        }
    }
}
