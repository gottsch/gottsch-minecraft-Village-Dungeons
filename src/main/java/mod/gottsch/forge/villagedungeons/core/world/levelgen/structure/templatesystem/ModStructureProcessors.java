/*
 * This file is part of  Village Dungeons.
 * Copyright (c) 2025 Mark Gottschling (gottsch)
 *
 * Village Dungeons is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Village Dungeons is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Village Dungeons.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
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
public class ModStructureProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, VillageDungeons.MOD_ID);

    public static final RegistryObject<StructureProcessorType<SewerGeneratorBlockProcessor>> SEWER_GENERATOR_PROCESSOR = STRUCTURE_PROCESSORS.register(
            "sewer_generator_processor", () -> () -> SewerGeneratorBlockProcessor.CODEC
    );

    public static void register(IEventBus eventBus) {
        STRUCTURE_PROCESSORS.register(eventBus);
    }
}
