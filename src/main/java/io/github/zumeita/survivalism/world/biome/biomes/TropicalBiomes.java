package io.github.zumeita.survivalism.world.biome.biomes;

import io.github.zumeita.survivalism.helpers.biome.BiomeHelpers;
import io.github.zumeita.survivalism.world.surfacebuilders.SurvivalismConfiguredSurfaceBuilders;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class TropicalBiomes {

    public static Biome Desert() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 1.0F;
        Float precipitation = 0.0F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_TROPICAL);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.DESERT).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }

    public static Biome DesertScrub() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 1.0F;
        Float precipitation = 0.1F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_TROPICAL);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.DESERT).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }
    public static Biome ThornSteppe() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 1.0F;
        Float precipitation = 0.2F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_TROPICAL);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.SAVANNA).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }

    public static Biome VeryDryForest() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 1.0F;
        Float precipitation = 0.3F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_TROPICAL);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }
    
    public static Biome DryForest() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 1.0F;
        Float precipitation = 0.4F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_TROPICAL);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }

    public static Biome MoistForest() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 1.0F;
        Float precipitation = 0.6F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_TROPICAL);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }

    public static Biome WetForest() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 1.0F;
        Float precipitation = 0.8F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_TROPICAL);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }

    public static Biome RainForest() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 1.0F;
        Float precipitation = 1.0F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_TROPICAL);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.JUNGLE).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }
    
}
