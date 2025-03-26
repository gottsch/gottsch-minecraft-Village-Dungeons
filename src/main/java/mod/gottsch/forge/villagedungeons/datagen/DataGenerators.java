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
package mod.gottsch.forge.villagedungeons.datagen;

import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

/**
 * 
 * @author Mark Gottschling on Mar 26, 2025
 *
 */
@Mod.EventBusSubscriber(modid = VillageDungeons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		if (event.includeServer()) {
//			generator.addProvider(event.includeServer(), new Recipes(output));
//			ModBlockTagsProvider blockTags = new ModBlockTagsProvider(output, lookupProvider, event.getExistingFileHelper());
//			generator.addProvider(true, blockTags);
//			generator.addProvider(true, new ModItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), event.getExistingFileHelper()));

		}
		if (event.includeClient()) {
//			generator.addProvider(event.includeClient(), new BlockStates(output, event.getExistingFileHelper()));
//			generator.addProvider(event.includeClient(), new ItemModelsProvider(output, event.getExistingFileHelper()));
			generator.addProvider(event.includeClient(), new LanguageGen(output, "en_us"));
		}
	}
}