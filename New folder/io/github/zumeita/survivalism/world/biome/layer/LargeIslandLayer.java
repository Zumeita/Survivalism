package io.github.zumeita.survivalism.world.biome.layer;

import io.github.zumeita.survivalism.helpers.biome.BiomeHelpers;
import io.github.zumeita.survivalism.world.biome.classifications.HoldridgeLifeZones;
import io.github.zumeita.survivalism.world.biome.classifications.LifeZones;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset1Transformer;

public enum LargeIslandLayer implements IAreaTransformer2, IDimOffset1Transformer
{
    INSTANCE;

    @Override
    public int applyPixel(INoiseRandom context, IArea landSeaArea, IArea climateArea, int x, int z)
    {
        int centerVal = landSeaArea.get(x + 1, z + 1);

        if (context.nextRandom(50) == 0)
        {
            int northVal = landSeaArea.get(x + 1, z + 0);
            int eastVal = landSeaArea.get(x + 2, z + 1);
            int southVal = landSeaArea.get(x + 1, z + 2);
            int westVal = landSeaArea.get(x + 0, z + 1);
            int climateVal = climateArea.get(x, z);

            HoldridgeLifeZones.Biomes climate;
            try
            {
                climate = LifeZones.lookup(climateVal);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                // This shouldn't happen - but apparently it (rarely) does (https://github.com/Glitchfiend/BiomesOPlenty/issues/983)
                // If it does it means that something weird happened with the climate layer / lookup
                // Rethrow with hopefully a more useful message
                String msg = "Climate lookup failed climateOrdinal: " + climateVal;
                throw new RuntimeException(msg,e);
            }

            //TODO - Islands?
            /*if (centerVal == 0 && northVal == 0 && eastVal == 0 && southVal == 0 && westVal == 0)
            {
                RegistryKey<Biome> islandBiome = climate.getRandomIslandBiome(context, null);

                if (islandBiome == null)
                {
                    return centerVal;
                }
                else
                {
                    return BiomeHelpers.getBiomeId(islandBiome);
                }
            }*/
            //else return centerVal;
            return centerVal;
        } else {
            return centerVal;
        }
    }
}