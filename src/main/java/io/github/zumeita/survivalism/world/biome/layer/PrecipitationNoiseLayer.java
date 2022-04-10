package io.github.zumeita.survivalism.world.biome.layer;

import java.util.Random;

import io.github.zumeita.survivalism.world.biome.layer.traits.IClimateAreaTransformer0;
import io.github.zumeita.survivalism.world.biome.layer.traits.IClimateContextExtended;
import io.github.zumeita.survivalism.world.biome.noise.SimplexNoise;

public enum PrecipitationNoiseLayer implements IClimateAreaTransformer0
{
    LARGE_ZONES(0.01D);

    private final double scale;

    PrecipitationNoiseLayer(double scale)
    {
        this.scale = scale;
    }

    @Override
    public int applyPixel(IClimateContextExtended context, int x, int z)
    {
        long seed = context.getWorldSeed();
        double noiseVal = SimplexNoise.noise(seed ^ 0xE157A1DC3B2A298CL, x * this.scale + SimplexNoise.TRIANGLE_START_Y, z * this.scale + SimplexNoise.TRIANGLE_START_X);

        // 8 possible outcomes, grouped equally from SimplexNoise vals. (8 outcomes = 8 possible precipitation levels)

        if(noiseVal < -0.7804209166984755) return 0;
        else if(noiseVal < -0.520280611132317) return 1;
        else if(noiseVal < -0.2601403055661585) return 2;
        else if(noiseVal < 0.0) return 3;
        else if(noiseVal < 0.2601403055661585) return 4;
        else if(noiseVal < 0.520280611132317) return 5;
        else if(noiseVal < 0.7804209166984755) return 6;
        else return 7;
    }
}