package io.github.zumeita.survivalism.world.biome.layer;

import io.github.zumeita.survivalism.world.biome.classifications.HoldridgeLifeZones;
import io.github.zumeita.survivalism.world.biome.classifications.LifeZones;
import io.github.zumeita.survivalism.world.biome.layer.traits.IAreaTransformer3;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum MixOceansLayer implements IAreaTransformer3, IDimOffset0Transformer
{
    INSTANCE;

    @Override
    public int applyPixel(INoiseRandom context, IArea biomeArea, IArea oceanArea, IArea climateArea, int x, int z)
    {
        int biomeId = biomeArea.get(x, z);
        int oceanId = oceanArea.get(x, z);
        int climateVal = climateArea.get(x, z);
        HoldridgeLifeZones.Biomes climate = LifeZones.lookup(climateVal);

        if (!SurvivalismLayerUtil.isOcean(biomeId))
        {
            return biomeId;
        }
        else
        {
            switch (climate)
            {
                case POLAR_DESERT:
                    oceanId = SurvivalismLayerUtil.FROZEN_OCEAN;
                    break;

                case SUBPOLAR_DRY_TUNDRA:
                case SUBPOLAR_MOIST_TUNDRA:
                case SUBPOLAR_RAIN_TUNDRA:
                case SUBPOLAR_WET_TUNDRA:
                case BOREAL_DESERT:
                case BOREAL_DRY_SCRUB:
                case BOREAL_MOIST_FOREST:
                case BOREAL_RAIN_FOREST:
                case BOREAL_WET_FOREST:
                case COOL_TEMPERATE_DESERT:
                case COOL_TEMPERATE_DESERT_SCRUB:
                case COOL_TEMPERATE_MOIST_FOREST:
                case COOL_TEMPERATE_RAIN_FOREST:
                case COOL_TEMPERATE_STEPPE:
                case COOL_TEMPERATE_WET_FOREST:

                    oceanId = SurvivalismLayerUtil.COLD_OCEAN;
                    break;

                case WARM_TEMPERATE_DESERT:
                case WARM_TEMPERATE_DESERT_SCRUB:
                case WARM_TEMPERATE_DRY_FOREST:
                case WARM_TEMPERATE_MOIST_FOREST:
                case WARM_TEMPERATE_RAIN_FOREST:
                case WARM_TEMPERATE_THORN_STEPPE:
                case WARM_TEMPERATE_WET_FOREST:
                case SUBTROPICAL_DESERT:
                case SUBTROPICAL_DESERT_SCRUB:
                case SUBTROPICAL_DRY_FOREST:
                case SUBTROPICAL_MOIST_FOREST:
                case SUBTROPICAL_RAIN_FOREST:
                case SUBTROPICAL_THORN_STEPPE:
                case SUBTROPICAL_WET_FOREST:
                    oceanId = SurvivalismLayerUtil.LUKEWARM_OCEAN;
                    break;

                case TROPICAL_DESERT:
                case TROPICAL_DESERT_SCRUB:
                case TROPICAL_DRY_FOREST:
                case TROPICAL_MOIST_FOREST:
                case TROPICAL_RAIN_FOREST:
                case TROPICAL_THORN_STEPPE:
                case TROPICAL_VERY_DRY_FOREST:
                case TROPICAL_WET_FOREST:
                    oceanId = SurvivalismLayerUtil.WARM_OCEAN;
                    break;

                default:
                    oceanId = SurvivalismLayerUtil.OCEAN;
                    break;
            }

            // When far from land, warm oceans should become lukewarm and frozen oceans should become cold
            /*for (int xOff = -8; xOff <= 8; xOff += 4)
            {
                for (int zOff = -8; zOff <= 8; zOff += 4)
                {
                    int offsetBiomeId = biomeArea.get(x + xOff, z + zOff);
                    if (!BOPLayerUtil.isOcean(offsetBiomeId))
                    {
                        if (oceanId == BOPLayerUtil.WARM_OCEAN)
                        {
                            return BOPLayerUtil.LUKEWARM_OCEAN;
                        }
                        if (oceanId == BOPLayerUtil.FROZEN_OCEAN)
                        {
                            return BOPLayerUtil.COLD_OCEAN;
                        }
                    }
                }
            }*/

            if (biomeId == SurvivalismLayerUtil.DEEP_OCEAN)
            {
                if (oceanId == SurvivalismLayerUtil.WARM_OCEAN)
                {
                    return SurvivalismLayerUtil.DEEP_WARM_OCEAN;
                }

                if (oceanId == SurvivalismLayerUtil.LUKEWARM_OCEAN)
                {
                    return SurvivalismLayerUtil.DEEP_LUKEWARM_OCEAN;
                }

                if (oceanId == SurvivalismLayerUtil.OCEAN)
                {
                    return SurvivalismLayerUtil.DEEP_OCEAN;
                }

                if (oceanId == SurvivalismLayerUtil.COLD_OCEAN)
                {
                    return SurvivalismLayerUtil.DEEP_COLD_OCEAN;
                }

                if (oceanId == SurvivalismLayerUtil.FROZEN_OCEAN)
                {
                    return SurvivalismLayerUtil.DEEP_FROZEN_OCEAN;
                }
            }

            return oceanId;
        }
    }
}