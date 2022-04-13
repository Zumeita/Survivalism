package io.github.zumeita.survivalism.world.biome.layer;

import io.github.zumeita.survivalism.helpers.biome.BiomeHelpers;
import io.github.zumeita.survivalism.world.biome.layer.traits.LazyAreaLayerContextSurvivalism;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;

import java.util.function.LongFunction;

public class SurvivalismLayerUtil {

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createInitialLandAndSeaFactory(LongFunction<C> contextFactory)
    {
        IAreaFactory<T> factory = IslandLayer.INSTANCE.run(contextFactory.apply(1L));
        factory = ZoomLayer.FUZZY.run(contextFactory.apply(2000L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(1L), factory);
        factory = ZoomLayer.NORMAL.run(contextFactory.apply(2001L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(2L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(50L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(70L), factory);
        factory = RemoveTooMuchOceanLayer.INSTANCE.run(contextFactory.apply(2L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(3L), factory);
        factory = ZoomLayer.NORMAL.run(contextFactory.apply(2002L), factory);
        factory = ZoomLayer.NORMAL.run(contextFactory.apply(2003L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(4L), factory);
        return factory;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createClimateFactory(LongFunction<C> contextFactory)
    {
        IAreaFactory<T> temperatureFactory;
        IAreaFactory<T> rainfallFactory;
        temperatureFactory = LatitudinalRegionsLayer.INSTANCE.run(contextFactory.apply(2L));//2L is old val
        rainfallFactory = PrecipitationNoiseLayer.LARGE_ZONES.run(contextFactory.apply(9L));

        return ClimateLayer.INSTANCE.run(contextFactory.apply(103L), temperatureFactory, rainfallFactory); // default 103L
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createBiomeFactory(IAreaFactory<T> landSeaAreaFactory, IAreaFactory<T> climateAreaFactory, LongFunction<C> contextFactory)
    {
        IAreaFactory<T> biomeFactory = SurvivalismBiomeLayer.INSTANCE.run(contextFactory.apply(200L), landSeaAreaFactory, climateAreaFactory);
        //biomeFactory = AddBambooForestLayer.INSTANCE.run(contextFactory.apply(1001L), biomeFactory);
        biomeFactory = LayerUtil.zoom(1000L, ZoomLayer.NORMAL, biomeFactory, 2, contextFactory);
        //biomeFactory = BOPBiomeEdgeLayer.INSTANCE.run(contextFactory.apply(1000L), biomeFactory);
        return biomeFactory;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createAreaFactories(LongFunction<C> contextFactory)
    {
        // Create the initial land and sea layer. Is also responsible for adding deep oceans
        // and mushroom islands
        IAreaFactory<T> landSeaFactory = createInitialLandAndSeaFactory(contextFactory);

        // Determines positions for all of the new ocean subbiomes added in 1.13
        IAreaFactory<T> oceanBiomeFactory = OceanLayer.INSTANCE.run(contextFactory.apply(2L));
        oceanBiomeFactory = LayerUtil.zoom(2001L, ZoomLayer.NORMAL, oceanBiomeFactory, 6, contextFactory);

        int biomeSize = 4;
        int riverSize = biomeSize;

        // Create the climates
        IAreaFactory<T> climateFactory = createClimateFactory(contextFactory);

        // Add islands and deep oceans
        landSeaFactory = AddMushroomIslandLayer.INSTANCE.run(contextFactory.apply(5L), landSeaFactory);
        landSeaFactory = LargeIslandLayer.INSTANCE.run(contextFactory.apply(5L), landSeaFactory, climateFactory);
        landSeaFactory = DeepOceanLayer.INSTANCE.run(contextFactory.apply(4L), landSeaFactory);

        // Allocate the biomes
        IAreaFactory<T> biomesFactory = createBiomeFactory(landSeaFactory, climateFactory, contextFactory);

        // Fork off a new branch as a seed for rivers and sub biomes
        IAreaFactory<T> riverAndSubBiomesInitFactory = StartRiverLayer.INSTANCE.run(contextFactory.apply(100L), landSeaFactory);
        riverAndSubBiomesInitFactory = LayerUtil.zoom(1000L, ZoomLayer.NORMAL, riverAndSubBiomesInitFactory, 2, contextFactory);
        //biomesFactory = SubBiomeLayer.INSTANCE.run(contextFactory.apply(1000L), biomesFactory, riverAndSubBiomesInitFactory);

        // Develop the rivers branch
        IAreaFactory<T> riversInitFactory = LayerUtil.zoom(1000L, ZoomLayer.NORMAL, riverAndSubBiomesInitFactory, riverSize, contextFactory);
        riversInitFactory = RiverLayer.INSTANCE.run(contextFactory.apply(1L), riversInitFactory);
        riversInitFactory = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), riversInitFactory);

        // Mix in rare biomes into biomes branch
        //biomesFactory = RareBiomeLayer.INSTANCE.run(contextFactory.apply(1001L), biomesFactory);

        // Zoom more based on the biome size
        for (int i = 0; i < biomeSize; ++i)
        {
            biomesFactory = ZoomLayer.NORMAL.run(contextFactory.apply((long)(1000 + i)), biomesFactory);
            if (i == 0) biomesFactory = AddIslandLayer.INSTANCE.run(contextFactory.apply(3L), biomesFactory);
            //TODO - Implement Shore Biomes and add the ShoreLayer
            //if (i == 1 || biomeSize == 1) biomesFactory = BOPShoreLayer.INSTANCE.run(contextFactory.apply(1000L), biomesFactory);
        }

        biomesFactory = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), biomesFactory);

        // Mix rivers into the biomes branch
        biomesFactory = RiverMixLayer.INSTANCE.run(contextFactory.apply(100L), biomesFactory, riversInitFactory);

        climateFactory = LayerUtil.zoom(2001L, ZoomLayer.NORMAL, climateFactory, biomeSize + 2, contextFactory);
        biomesFactory = MixOceansLayer.INSTANCE.run(contextFactory.apply(100L), biomesFactory, oceanBiomeFactory, climateFactory);
        return biomesFactory;
    }

    public static Layer createGenLayers(long seed)
    {
        IAreaFactory<LazyArea> factory = createAreaFactories((seedModifier) ->
        {
            return new LazyAreaLayerContextSurvivalism(1, seed, seedModifier);
        });
        return new Layer(factory);
    }

    public static final int WARM_OCEAN = BiomeHelpers.getBiomeId(Biomes.WARM_OCEAN);
    public static final int LUKEWARM_OCEAN = BiomeHelpers.getBiomeId(Biomes.LUKEWARM_OCEAN);
    public static final int OCEAN = BiomeHelpers.getBiomeId(Biomes.OCEAN);
    public static final int COLD_OCEAN = BiomeHelpers.getBiomeId(Biomes.COLD_OCEAN);
    public static final int FROZEN_OCEAN = BiomeHelpers.getBiomeId(Biomes.FROZEN_OCEAN);
    public static final int DEEP_WARM_OCEAN = BiomeHelpers.getBiomeId(Biomes.DEEP_WARM_OCEAN);
    public static final int DEEP_LUKEWARM_OCEAN = BiomeHelpers.getBiomeId(Biomes.DEEP_LUKEWARM_OCEAN);
    public static final int DEEP_OCEAN = BiomeHelpers.getBiomeId(Biomes.DEEP_OCEAN);
    public static final int DEEP_COLD_OCEAN = BiomeHelpers.getBiomeId(Biomes.DEEP_COLD_OCEAN);
    public static final int DEEP_FROZEN_OCEAN = BiomeHelpers.getBiomeId(Biomes.DEEP_FROZEN_OCEAN);

    public static boolean isOcean(int biomeIn) {
        return biomeIn == WARM_OCEAN || biomeIn == LUKEWARM_OCEAN || biomeIn == OCEAN || biomeIn == COLD_OCEAN || biomeIn == FROZEN_OCEAN || biomeIn == DEEP_WARM_OCEAN || biomeIn == DEEP_LUKEWARM_OCEAN || biomeIn == DEEP_OCEAN || biomeIn == DEEP_COLD_OCEAN || biomeIn == DEEP_FROZEN_OCEAN;
    }

}
