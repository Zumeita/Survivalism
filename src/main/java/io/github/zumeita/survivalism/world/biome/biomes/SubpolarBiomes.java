package io.github.zumeita.survivalism.world.biome.biomes;

import io.github.zumeita.survivalism.helpers.biome.BiomeHelpers;
import io.github.zumeita.survivalism.world.surfacebuilders.SurvivalismConfiguredSurfaceBuilders;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class SubpolarBiomes {
    public static Biome DryTundra() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 0.1F;
        Float precipitation = 0.0F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_SUBPOLAR);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.SNOW).biomeCategory(Biome.Category.DESERT).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }

    public static Biome MoistTundra() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 0.2F;
        Float precipitation = 0.5F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_SUBPOLAR);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.SNOW).biomeCategory(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }

    public static Biome WetTundra() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 0.2F;
        Float precipitation = 0.7F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_SUBPOLAR);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }

    public static Biome RainTundra() {

        int skyColor = 12638463;
        int grassColor = 5140788;
        Float temperature = 0.2F;
        Float precipitation = 0.9F;

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurvivalismConfiguredSurfaceBuilders.CSB_SUBPOLAR);
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();

        BiomeHelpers.setupStandardMobSpawnInfo(new MobSpawnInfo.Builder());
        BiomeHelpers.addStandardVanillaFeatures(builder);
        BiomeHelpers.addStandardSurvivalismFeatures(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(temperature).downfall(precipitation).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).grassColorOverride(grassColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(builder.build()).build();
    }
}
