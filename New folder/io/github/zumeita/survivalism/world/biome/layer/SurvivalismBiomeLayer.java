package io.github.zumeita.survivalism.world.biome.layer;

import io.github.zumeita.survivalism.helpers.biome.BiomeHelpers;
import io.github.zumeita.survivalism.world.biome.classifications.HoldridgeLifeZones;
import io.github.zumeita.survivalism.world.biome.classifications.LifeZones;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum SurvivalismBiomeLayer implements IAreaTransformer2, IDimOffset0Transformer
{
    INSTANCE;

    private static final int DEEP_OCEAN = BiomeHelpers.getBiomeId(Biomes.DEEP_OCEAN);

    @Override
    public int applyPixel(INoiseRandom context, IArea area1, IArea area2, int x, int z)
    {
        int landSeaVal = area1.get(x, z);
        int climateVal = area2.get(x, z);

        HoldridgeLifeZones.Biomes climate;
        try
        {
            climate = LifeZones.lookup(climateVal);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            String msg = "Climate lookup failed climateOrdinal: " + climateVal;
            throw new RuntimeException(msg,e);
        }

        // At this point, oceans and land have been assigned
        if (landSeaVal == DEEP_OCEAN)
        {
            return BiomeHelpers.getBiomeId(Biomes.DEEP_OCEAN);
        }
        //TODO - Island Biomes
        /*else if ((landSeaVal == MUSHROOM_FIELDS || ModBiomes.islandBiomeIds.contains(landSeaVal)) && !(climate == BOPClimates.ICE_CAP || climate == BOPClimates.TUNDRA))
        {
            // keep islands, unless it's in an icy climate in which case, replace
            return landSeaVal;
        }*/
        else if (landSeaVal == 0)
        {
            return BiomeHelpers.getBiomeId(Biomes.DEEP_OCEAN);
        }
        else
        {
            return BiomeHelpers.getBiomeId(climate.getBiomeRegistryKey());
        }
    }
}