package io.github.zumeita.survivalism.world.biome;

import io.github.zumeita.survivalism.world.biome.noise.IBiomeNoiseSampler;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.LongFunction;

public class BiomeType {

    private final Type type;
    private final LongFunction<IBiomeNoiseSampler> noiseFactory;

    public BiomeType(LongFunction<IBiomeNoiseSampler> noiseFactory, Type type) {
        this.noiseFactory = noiseFactory;
        this.type = type;
    }

    public IBiomeNoiseSampler createNoiseSampler(long seed) {
        return noiseFactory.apply(seed);
    }

    public enum Type {
        LAND, OCEAN, RIVER;
    }
}
