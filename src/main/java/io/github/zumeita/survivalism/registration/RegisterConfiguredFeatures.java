package io.github.zumeita.survivalism.registration;

import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.definitions.VegetationBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class RegisterConfiguredFeatures {


    public static final ConfiguredFeature<?, ?> CLAY = register("clay", RegisterFeatures.CLAY.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));

    public static final BlockState GRASS_BLOCK = VegetationBlocks.Default.GRASS.getBlock().defaultBlockState();
    public static final ConfiguredFeature<?, ?> GRASS = register("grass", RegisterFeatures.VEG
            .configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GRASS_BLOCK), SimpleBlockPlacer.INSTANCE)).tries(16).build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(50));

    public static final BlockState CARROT = VegetationBlocks.Default.WILD_CARROT.getBlock().defaultBlockState();
    public static final ConfiguredFeature<?, ?> WILD_CARROT = register("wild_carrots", RegisterFeatures.VEG
            .configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CARROT), SimpleBlockPlacer.INSTANCE)).tries(1).build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1));

    public static final BlockState GARLIC = VegetationBlocks.Default.WILD_GARLIC.getBlock().defaultBlockState();
    public static final ConfiguredFeature<?, ?> WILD_GARLIC = register("wild_garlic", RegisterFeatures.VEG
            .configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GARLIC), SimpleBlockPlacer.INSTANCE)).tries(1).build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1));

    public static final BlockState ONION = VegetationBlocks.Default.WILD_ONION.getBlock().defaultBlockState();
    public static final ConfiguredFeature<?, ?> WILD_ONION = register("wild_onion", RegisterFeatures.VEG
            .configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ONION), SimpleBlockPlacer.INSTANCE)).tries(1).build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1));

    public static final BlockState POTATO = VegetationBlocks.Default.WILD_POTATO.getBlock().defaultBlockState();
    public static final ConfiguredFeature<?, ?> WILD_POTATO = register("wild_potato", RegisterFeatures.VEG
            .configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(POTATO), SimpleBlockPlacer.INSTANCE)).tries(1).build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1));

    public static final BlockState CABBAGE = VegetationBlocks.Default.WILD_CABBAGE.getBlock().defaultBlockState();
    public static final ConfiguredFeature<?, ?> WILD_CABBAGE = register("wild_cabbage", RegisterFeatures.VEG
            .configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CABBAGE), SimpleBlockPlacer.INSTANCE)).tries(1).build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1));

    public static final BlockState SPINACH = VegetationBlocks.Default.WILD_SPINACH.getBlock().defaultBlockState();
    public static final ConfiguredFeature<?, ?> WILD_SPINACH = register("wild_spinach", RegisterFeatures.VEG
            .configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(SPINACH), SimpleBlockPlacer.INSTANCE)).tries(1).build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1));



    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature){
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Survivalism.MOD_ID, key), feature);
    }

}
