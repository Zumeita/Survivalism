package io.github.zumeita.survivalism.helpers.biome;

import io.github.zumeita.survivalism.registration.RegisterConfiguredFeatures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
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

    public static void addStandardSurvivalismFeatures(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, RegisterConfiguredFeatures.CLAY);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RegisterConfiguredFeatures.GRASS);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RegisterConfiguredFeatures.WILD_CARROT);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RegisterConfiguredFeatures.WILD_GARLIC);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RegisterConfiguredFeatures.WILD_ONION);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RegisterConfiguredFeatures.WILD_POTATO);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RegisterConfiguredFeatures.WILD_CABBAGE);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RegisterConfiguredFeatures.WILD_SPINACH);

    }

    public static void addStandardVanillaFeatures(BiomeGenerationSettings.Builder builder) {
        //biomegenerationsettings$builder.addStructureStart(StructureFeatures.VILLAGE_PLAINS).addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        DefaultBiomeFeatures.addDefaultCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
    }

    public static void setupStandardMobSpawnInfo(MobSpawnInfo.Builder builder) {
        DefaultBiomeFeatures.plainsSpawns(builder);
        builder.setPlayerCanSpawn();
    }

}
