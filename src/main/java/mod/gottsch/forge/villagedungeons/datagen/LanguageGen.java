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
import mod.gottsch.forge.villagedungeons.core.block.ModBlocks;
import mod.gottsch.forge.villagedungeons.core.util.LangUtil;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * 
 * @author Mark Gottschling on Mar 26, 2025
 *
 */
public class LanguageGen extends LanguageProvider {

    public LanguageGen(PackOutput gen, String locale) {
        super(gen, VillageDungeons.MOD_ID, locale);
    }
    
    @Override
    protected void addTranslations() {

        // items
//        add(ModItems.PLAYER_DEED.get(), "Player Deed");

        // blocks
        add(ModBlocks.WEATHERED_COPPER_GRATE.get(), "Weathered Copper Grate");
        add(ModBlocks.WEATHERED_COPPER_TRAPDOOR.get(), "Weathered Copper Trap Door");

        /*
         * Util.chats
         */
        // exceptions / errors
//        add(LangUtil.chat("unable_locate_player"), "Unable to locate the player -> %s");
//        add(LangUtil.chat("unexpected_error"), "An unexpected error occurred.");

        /*
         *  Util.tooltips
         */
        // general
        add(LangUtil.tooltip("hold_shift"), "Hold [SHIFT] to expand");
    }
}
