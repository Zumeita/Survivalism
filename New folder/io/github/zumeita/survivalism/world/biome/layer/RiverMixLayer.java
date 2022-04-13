package io.github.zumeita.survivalism.world.biome.layer;

import io.github.zumeita.survivalism.helpers.biome.BiomeHelpers;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum RiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer
{
    INSTANCE;

    private static final int FROZEN_RIVER = BiomeHelpers.getBiomeId(Biomes.FROZEN_RIVER);
    private static final int SNOWY_TUNDRA = BiomeHelpers.getBiomeId(Biomes.SNOWY_TUNDRA);
    private static final int MUSHROOM_FIELDS = BiomeHelpers.getBiomeId(Biomes.MUSHROOM_FIELDS);
    private static final int MUSHROOM_FIELD_SHORE = BiomeHelpers.getBiomeId(Biomes.MUSHROOM_FIELD_SHORE);
    private static final int RIVER = BiomeHelpers.getBiomeId(Biomes.RIVER);

    @Override
    public int applyPixel(INoiseRandom context, IArea biomeArea, IArea riverArea, int x, int z)
    {
        int biomeId = biomeArea.get(x, z);
        int riverId = riverArea.get(x, z);
        RegistryKey<Biome> biome = BiomeHelpers.createKey(biomeId);

        if (SurvivalismLayerUtil.isOcean(biomeId))
        {
            return biomeId;
        }
        else if (riverId == RIVER)
        {
            //TODO - Icy river biome to be implemented within Polar regions
            /*if (biomeId == SNOWY_TUNDRA)
            {
                return FROZEN_RIVER;
            }*/
            //TODO - Biome Metadata
            /*if (BiomeHelpers.hasMetadata(biome))
            {
                BiomeMetadata meta = BiomeHelpers.getMetadata(biome);

                if (meta.getRiverBiome() != null)
                    return BiomeHelpers.getBiomeId(meta.getRiverBiome());
                else
                    return biomeId;
            }
            else
            {
                return riverId & 255;
            }*/
            return riverId & 255;
        }
        else
        {
            return biomeId;
        }
    }
}