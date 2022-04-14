package io.github.zumeita.survivalism.registration;

import io.github.zumeita.survivalism.Survivalism;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class RegisterConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> CLAY = register("clay", RegisterFeatures.CLAY.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature){
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Survivalism.MOD_ID, key), feature);
    }

}
