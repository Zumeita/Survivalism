package io.github.zumeita.survivalism.helpers.biome;

import io.github.zumeita.survivalism.registration.RegisterConfiguredFeatures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class BiomeHelpers {

    public static Biome getBiome(RegistryKey<Biome> key)
    {
        Biome biome = ForgeRegistries.BIOMES.getValue(key.location());
        if (biome == null) throw new RuntimeException("Attempted to get unregistered biome " + key);
        return biome;
    }

    public static Biome getBiome(int id)
    {
        if (id == -1) throw new RuntimeException("Attempted to get biome with id -1");
        return getBiome(((ForgeRegistry<Biome>)ForgeRegistries.BIOMES).getKey(id));
    }


    public static int getBiomeId(Biome biome)
    {
        if (biome == null) throw new RuntimeException("Attempted to get id of null biome");
        int id = ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getID(biome);
        if (id == -1) throw new RuntimeException("Biome id is -1 for biome " + biome.delegate.name());
        return id;
    }

    public static int getBiomeId(RegistryKey<Biome> key)
    {
        return getBiomeId(getBiome(key));
    }

    public static RegistryKey<Biome> createKey(Biome biome)
    {
        return biome == null ? null : RegistryKey.create(Registry.BIOME_REGISTRY, biome.delegate.name());
    }

    public static RegistryKey<Biome> createKey(int id)
    {
        return createKey(getBiome(id));
    }

    public static void addStandardFeatures(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, RegisterConfiguredFeatures.CLAY);
    }

}
