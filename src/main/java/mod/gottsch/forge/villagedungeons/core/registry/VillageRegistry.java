package mod.gottsch.forge.villagedungeons.core.registry;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;

import java.util.Map;
import java.util.Optional;

/**
 * @author by Mark Gottschling on 4/4/2025
 */
public class VillageRegistry {
    public static class DungeonInfo {
        private BlockPos position;
        public void setPosition(BlockPos position) {
            this.position = position;
        }
    }

    private static final Map<BlockPos, DungeonInfo> REGISTRY = Maps.newHashMap();

    public static Optional<DungeonInfo> register(BlockPos pos, DungeonInfo info) {
        return Optional.ofNullable(REGISTRY.put(pos, info));
    }

    // TODO load(), save()
    // TODO call from World Persistence
}
