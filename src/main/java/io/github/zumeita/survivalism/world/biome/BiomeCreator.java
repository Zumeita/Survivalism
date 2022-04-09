package io.github.zumeita.survivalism.world.biome;

import io.github.zumeita.survivalism.world.biome.noise.IBiomeNoiseSampler;
import io.github.zumeita.survivalism.world.biome.noise.Noise2D;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

public class BiomeCreator {

    public static BiomeCreator builder() {
        return new BiomeCreator();
    }

    private final List<BiomeDictionary.Type> dictionaryTypes;

    private BiomeType.Type type;
    private LongFunction<Noise2D> heightNoiseFactory;
    private LongFunction<IBiomeNoiseSampler> noiseFactory;

    private BiomeCreator() {

        dictionaryTypes = new ArrayList<>();

        // Set defaults here. additional are set with the below functions.
        type = BiomeType.Type.LAND;
    }


    public BiomeCreator heightmap(LongFunction<Noise2D> heightNoiseFactory) {
        this.heightNoiseFactory = heightNoiseFactory;
        this.noiseFactory = seed -> IBiomeNoiseSampler.fromHeightNoise(heightNoiseFactory.apply(seed));
        return this;
    }

    public BiomeCreator noise(LongFunction<IBiomeNoiseSampler> noiseFactory) {
        this.noiseFactory = noiseFactory;
        return this;
    }

    public BiomeCreator type(BiomeType.Type type) {
        this.type = type;
        return this;
    }

    // Not 100% sure if I need to do this, adds the type to the Forge biome dictionary.
    public void registerTypes(RegistryKey<Biome> biome) {
        dictionaryTypes.forEach(type -> BiomeDictionary.addTypes(biome, type));
    }

    public BiomeType build() {
        return new BiomeType(noiseFactory, type);
    }

}
