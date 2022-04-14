package io.github.zumeita.survivalism.world.biome.layer;

import io.github.zumeita.survivalism.world.biome.classifications.HoldridgeLifeZones;
import io.github.zumeita.survivalism.world.biome.layer.traits.IClimateAreaTransformer0;
import io.github.zumeita.survivalism.world.biome.layer.traits.IClimateContextExtended;
import net.minecraft.util.math.MathHelper;

public enum LatitudinalRegionsLayer implements IClimateAreaTransformer0
{
    INSTANCE;

    // These numbers work out, roughly 10,000 blocks between POLAR and TROPICAL.
    // I think increasing HALF PERIOD , increases the distance between.
    // Amplitude is floored, so 6.9999 = 6, which is the max index of our bioTemperature enum.

    private static final double HALF_PERIOD = 64.0D; // 16 default
    private static final double PERIOD = HALF_PERIOD * 2.0D;
    private static final double OFFSET_VARIATION = HALF_PERIOD / 10.0D;
    private static final double AMPLITUDE = 6.9999D / HALF_PERIOD; // 0.5.6249375 default 8.9999D

    @Override
    public int applyPixel(IClimateContextExtended context, int x, int z)
    {
        int offset = (int) (context.getWorldSeed() % ((int) (PERIOD * 2)));

        double yOffset = z + offset + ((context.nextRandom(1001) - 500) * OFFSET_VARIATION / 500.0D); // 500.0D
        int ret = MathHelper.floor(AMPLITUDE * Math.abs((Math.abs(yOffset % PERIOD) - HALF_PERIOD)));

        //System.out.printf("MathHelper.floor(%f * Math.abs((Math.abs(%f %% %f) - %f))); = %d%n", AMPLITUDE, yOffset, PERIOD, HALF_PERIOD, ret);

        return ret;
    }
}