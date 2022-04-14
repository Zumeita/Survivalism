package io.github.zumeita.survivalism.world.biome;

import io.github.zumeita.survivalism.world.biome.noise.*;

public class BiomeNoise {

    private static int SEA_LEVEL = 96;

    public static Noise2D hills(long seed, int minHeight, int maxHeight)
    {
        return new OpenSimplex2D(seed).octaves(4).spread(0.05f).scaled(SEA_LEVEL + minHeight, SEA_LEVEL + maxHeight);
    }

}
