package io.github.zumeita.survivalism.world.biome;

import com.google.common.collect.Lists;
import io.github.zumeita.survivalism.Survivalism;
import static io.github.zumeita.survivalism.world.biome.BiomeCreator.builder;


import io.github.zumeita.survivalism.world.biome.classifications.HoldridgeLifeZones;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Locale;

public class BiomeRegistry {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Survivalism.MOD_ID);

    private ArrayList<?> biomes = Lists.newArrayList(); // Contains all registered biomes

    public static final BiomeType FLAT = register("flat", builder().heightmap(seed -> BiomeNoise.hills(seed, 4, 10)));
    public static final BiomeType HILLS = register("hills", builder().heightmap(seed -> BiomeNoise.hills(seed, -5, 16)));
    //public static final BiomeType LOWLANDS = register("lowlands", builder().heightmap(seed -> BiomeNoise.hills(seed, 4, 10))); // Aka. Swamp
    //public static final BiomeType MOUNTAINS = register("mountains", builder().heightmap(seed -> BiomeNoise.hills(seed, 10, 70)));
    //public static final BiomeType CANYONS = register("canyons", builder().heightmap(seed -> BiomeNoise.hills(seed, -2, 40))); // Like the Mesa in Vanilla

    //public static final BiomeType OCEAN = register("ocean", builder().heightmap(seed -> BiomeNoise.hills(seed, 4, 10)));
    //public static final BiomeType DEEP_OCEAN = register("deep_ocean", builder().heightmap(seed -> BiomeNoise.hills(seed, 4, 10)));
    //public static final BiomeType OCEAN_REEF = register("ocean_reef", builder().heightmap(seed -> BiomeNoise.hills(seed, 4, 10))); // Will use in subtropics & tropics

    //public static final BiomeType RIVER = register("river", builder().heightmap(seed -> BiomeNoise.hills(seed, 4, 10)));
   //public static final BiomeType LAKE = register("lake", builder().heightmap(seed -> BiomeNoise.hills(seed, 4, 10)));
    //public static final BiomeType SHORE = register("shore", builder().heightmap(seed -> BiomeNoise.hills(seed, 4, 10))); // Maybe

    public static BiomeType register(String name, BiomeCreator creator) {
        BiomeType type = creator.build();

        for(HoldridgeLifeZones.BioTemperature temp : HoldridgeLifeZones.BioTemperature.values()) {

            HoldridgeLifeZones.Biomes[] validBiomes = temp.getValidBiomes();

            for(HoldridgeLifeZones.Biomes biome : validBiomes) {

                String registryName = (temp.getName() + "_" + biome.getName() + "_" + name).toLowerCase(Locale.ROOT);

                ResourceLocation id = new ResourceLocation(Survivalism.MOD_ID, registryName);
                RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, id);

                BiomeRegistry.BIOMES.register(registryName, BiomeMaker::theVoidBiome);

                //TODO Add the key above to the arraylist biomes, for use in the BiomeProvider, along with a weight.
            }
        }
        return type;
    }




}
