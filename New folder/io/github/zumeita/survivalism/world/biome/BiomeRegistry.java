package io.github.zumeita.survivalism.world.biome;

import com.google.common.collect.Lists;
import io.github.zumeita.survivalism.Survivalism;

import io.github.zumeita.survivalism.world.SurvivalismWorldType;
import io.github.zumeita.survivalism.world.biome.classifications.HoldridgeLifeZones;
import io.github.zumeita.survivalism.world.biome.provider.SurvivalismBiomeProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BiomeRegistry {

    public static SurvivalismWorldType worldType = new SurvivalismWorldType();


    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Survivalism.MOD_ID);

    private static ArrayList<RegistryKey<Biome>> biomes = Lists.newArrayList(); // Contains all registered biomes

    public static final RegistryObject<Biome> POLAR_DESERT = register("polar_desert");

    public static final RegistryObject<Biome> SUBPOLAR_DRY_TUNDRA = register("subpolar_dry_tundra");
    public static final RegistryObject<Biome> SUBPOLAR_MOIST_TUNDRA = register("subpolar_moist_tundra");
    public static final RegistryObject<Biome> SUBPOLAR_WET_TUNDRA = register("subpolar_wet_tundra");
    public static final RegistryObject<Biome> SUBPOLAR_RAIN_TUNDRA = register("subpolar_rain_tundra");

    public static final RegistryObject<Biome> BOREAL_DESERT = register("boreal_desert");
    public static final RegistryObject<Biome> BOREAL_DRY_SCRUB = register("boreal_dry_scrub");
    public static final RegistryObject<Biome> BOREAL_MOIST_FOREST = register("boreal_moist_forest");
    public static final RegistryObject<Biome> BOREAL_WET_FOREST = register("boreal_wet_forest");
    public static final RegistryObject<Biome> BOREAL_RAIN_FOREST = register("boreal_rain_forest");

    public static final RegistryObject<Biome> COOL_TEMPERATE_DESERT = register("cool_temperate_desert");
    public static final RegistryObject<Biome> COOL_TEMPERATE_DESERT_SCRUB = register("cool_temperate_desert_scrub");
    public static final RegistryObject<Biome> COOL_TEMPERATE_STEPPE = register("cool_temperate_steppe");
    public static final RegistryObject<Biome> COOL_TEMPERATE_MOIST_FOREST = register("cool_temperate_moist_forest");
    public static final RegistryObject<Biome> COOL_TEMPERATE_WET_FOREST = register("cool_temperate_wet_forest");
    public static final RegistryObject<Biome> COOL_TEMPERATE_RAIN_FOREST = register("cool_temperate_rain_forest");

    public static final RegistryObject<Biome> WARM_TEMPERATE_DESERT = register("warm_temperate_desert");
    public static final RegistryObject<Biome> WARM_TEMPERATE_DESERT_SCRUB = register("warm_temperate_desert_scrub");
    public static final RegistryObject<Biome> WARM_TEMPERATE_THORN_STEPPE = register("warm_temperate_thorn_steppe");
    public static final RegistryObject<Biome> WARM_TEMPERATE_DRY_FOREST = register("warm_temperate_dry_forest");
    public static final RegistryObject<Biome> WARM_TEMPERATE_MOIST_FOREST = register("warm_temperate_moist_forest");
    public static final RegistryObject<Biome> WARM_TEMPERATE_WET_FOREST = register("warm_temperate_wet_forest");
    public static final RegistryObject<Biome> WARM_TEMPERATE_RAIN_FOREST = register("warm_temperate_rain_forest");

    public static final RegistryObject<Biome> SUBTROPICAL_DESERT = register("subtropical_desert");
    public static final RegistryObject<Biome> SUBTROPICAL_DESERT_SCRUB = register("subtropical_desert_scrub");
    public static final RegistryObject<Biome> SUBTROPICAL_THORN_STEPPE = register("subtropical_thorn_steppe");
    public static final RegistryObject<Biome> SUBTROPICAL_DRY_FOREST = register("subtropical_dry_forest");
    public static final RegistryObject<Biome> SUBTROPICAL_MOIST_FOREST = register("subtropical_moist_forest");
    public static final RegistryObject<Biome> SUBTROPICAL_WET_FOREST = register("subtropical_wet_forest");
    public static final RegistryObject<Biome> SUBTROPICAL_RAIN_FOREST = register("subtropical_rain_forest");

    public static final RegistryObject<Biome> TROPICAL_DESERT = register("tropical_desert");
    public static final RegistryObject<Biome> TROPICAL_DESERT_SCRUB = register("tropical_desert_scrub");
    public static final RegistryObject<Biome> TROPICAL_THORN_STEPPE = register("tropical_thorn_steppe");
    public static final RegistryObject<Biome> TROPICAL_VERY_DRY_FOREST = register("tropical_very_dry_forest");
    public static final RegistryObject<Biome> TROPICAL_DRY_FOREST = register("tropical_dry_forest");
    public static final RegistryObject<Biome> TROPICAL_MOIST_FOREST = register("tropical_moist_forest");
    public static final RegistryObject<Biome> TROPICAL_WET_FOREST = register("tropical_wet_forest");
    public static final RegistryObject<Biome> TROPICAL_RAIN_FOREST = register("tropical_rain_forest");

    public static ArrayList<RegistryKey<Biome>> getAllBaseBiomes() {
        return biomes;
    }

    public static RegistryObject<Biome> register(String name) {
        for(HoldridgeLifeZones.Biomes biome : HoldridgeLifeZones.Biomes.values()) {
            if(Objects.equals(name, biome.getName())) {

                RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Survivalism.MOD_ID, name));

                biome.addRegistryKey(key);

                return BiomeRegistry.BIOMES.register(name, biome.getBiomeSupplier());
            }
        }
        return null;
    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);

        //TODO Game Logger & experimental warning removal
        worldType.setRegistryName(new ResourceLocation("survivalism"));
        ForgeRegistries.WORLD_TYPES.register(worldType);

        Registry.register(Registry.BIOME_SOURCE, "survivalism_overworld", SurvivalismBiomeProvider.CODEC);
    }
}
