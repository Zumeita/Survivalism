package io.github.zumeita.survivalism.world.biome.biomes;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class CoolTemperateBiomes {

    public static Biome PolarDesert() {

        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.plainsSpawns(mobspawninfo$builder);

        mobspawninfo$builder.setPlayerCanSpawn();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.DESERT);

        //biomegenerationsettings$builder.addStructureStart(StructureFeatures.VILLAGE_PLAINS).addStructureStart(StructureFeatures.PILLAGER_OUTPOST);

        DefaultBiomeFeatures.addDefaultOverworldLandStructures(biomegenerationsettings$builder);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        DefaultBiomeFeatures.addDefaultCarvers(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addPlainGrass(biomegenerationsettings$builder);

        //biomegenerationsettings$builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_SUNFLOWER);

        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addPlainVegetation(biomegenerationsettings$builder);

        //biomegenerationsettings$builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_SUGAR_CANE);

        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);

        //biomegenerationsettings$builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN);

        DefaultBiomeFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder);

        DefaultBiomeFeatures.addDefaultSprings(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);

        int skyColor = 12638463;

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(skyColor).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobspawninfo$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
    }

}
