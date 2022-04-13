package io.github.zumeita.survivalism.world.biome.layer;

import com.mojang.datafixers.util.Pair;
import io.github.zumeita.survivalism.world.biome.classifications.HoldridgeLifeZones;
import io.github.zumeita.survivalism.world.biome.classifications.LifeZones;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum ClimateLayer implements IAreaTransformer2, IDimOffset0Transformer
{
    INSTANCE;

    private final int[] climateMapping;

    ClimateLayer()
    {
        this.climateMapping = LifeZones.getLifeZonesInts();
    }

    @Override
    public int applyPixel(INoiseRandom context, IArea latitudinalRegionValue, IArea humidityValue, int x, int z)
    {
        int latitudinalRegionIndex = latitudinalRegionValue.get(x, z);
        int humidityIndex = humidityValue.get(x, z);

        // temperature values from 0 (Polar) to 6 (Tropical) and rainfall values from 0 (Super Arid) to 7 (Super Humid), index is (temperatureValue * 8) + rainfallValue
        // clamp as a precaution against potential rounding errors due to use of doubles/floats in noise calculations
        // this guarantees index is between 0 and 55 (= 7 * 8), the range of indexes in BOPClimates.getClimateMappingInts()
        int index = ( MathHelper.clamp(latitudinalRegionIndex, 0, 6) * 8 ) + MathHelper.clamp(humidityIndex, 0, 7);
        return this.climateMapping[index];
    }
}