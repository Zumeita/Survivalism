package io.github.zumeita.survivalism.world.biome.layer;

import io.github.zumeita.survivalism.world.biome.classifications.HoldridgeLifeZones;
import io.github.zumeita.survivalism.world.biome.layer.traits.IClimateAreaTransformer0;
import io.github.zumeita.survivalism.world.biome.layer.traits.IClimateContextExtended;
import net.minecraft.util.math.MathHelper;

public enum LatitudinalRegionsLayer implements IClimateAreaTransformer0
{
    INSTANCE;

    private static final double HALF_PERIOD = 16.0D; // 16 default
    private static final double PERIOD = HALF_PERIOD * 2.0D;
    private static final double OFFSET_VARIATION = HALF_PERIOD / 10.0D;
    private static final double AMPLITUDE = 8.9999D / HALF_PERIOD; // 0.5.6249375 default 8.9999D

    @Override
    public int applyPixel(IClimateContextExtended context, int x, int z)
    {
        int offset = (int) (context.getWorldSeed() % ((int) (PERIOD * 2)));

        double yOffset = z + offset + ((context.nextRandom(1001) - 500) * OFFSET_VARIATION / 500.0D);
        int ret = MathHelper.floor(AMPLITUDE * Math.abs((Math.abs(yOffset % PERIOD) - HALF_PERIOD)));

        // Temporary until I figure out the above algorithm, avoids any out of bounds errors.

        if(ret > HoldridgeLifeZones.BioTemperature.values().length) {
            ret = HoldridgeLifeZones.BioTemperature.values().length;
        }

        return ret;
    }
}