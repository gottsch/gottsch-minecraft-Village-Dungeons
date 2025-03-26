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
package mod.gottsch.forge.villagedungeons.core.config;

import mod.gottsch.forge.gottschcore.config.AbstractConfig;
import mod.gottsch.forge.villagedungeons.core.VillageDungeons;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Mark Gottschling on 3/24/2025
 */
@Mod.EventBusSubscriber(modid = VillageDungeons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config extends AbstractConfig {
    public static final String CATEGORY_DIV = "##############################";
    public static final String UNDERLINE_DIV = "------------------------------";

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ClientConfig CLIENT;

    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ServerConfig SERVER;

    // setup as a singleton
    public static Config instance = new Config();

    static {
        final Pair<CommonConfig, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder()
                .configure(CommonConfig::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();

        final Pair<ClientConfig, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder()
                .configure(ClientConfig::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();

        final Pair<ServerConfig, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder()
                .configure(ServerConfig::new);
        SERVER_SPEC = serverSpecPair.getRight();
        SERVER = serverSpecPair.getLeft();
    }

    /**
     *
     */
    public static void register() {
        registerCommonConfig();
        registerClientConfig();
        registerServerConfig();
    }

    private static void registerCommonConfig() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
    }

    private static void registerClientConfig() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
    }

    private static void registerServerConfig() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_SPEC);
    }

    /*
     *
     */
    public static class CommonConfig {
        public Logging logging;
        public CommonConfig(ForgeConfigSpec.Builder builder) {
            logging = new Logging(builder);
        }
    }

    public static class ClientConfig {
//        public Gui gui;
        public ClientConfig(ForgeConfigSpec.Builder builder) {
//            gui = new Gui(builder);
        }
    }

    public static class ServerConfig {
//        public General general;
//        public Borders borders;
//        public Protection protection;

        public ServerConfig(ForgeConfigSpec.Builder builder) {
//            general = new General(builder);
//            borders = new Borders(builder);
//            protection = new Protection(builder);
        }
    }
}
