package io.github.zumeita.survivalism.world.biome.provider;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class SurvivalismBiomeProvider extends BiomeProvider {

    public static final Codec<SurvivalismBiomeProvider> CODEC = RecordCodecBuilder.create((builder ->
    {
        return builder.group(
            Codec.LONG.fieldOf("seed").stable().forGetter((biomeProvider) -> biomeProvider.seed),
            RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((biomeProvider) -> biomeProvider.biomes)
        ).apply(builder, builder.stable(SurvivalismBiomeProvider::new));
    });

    //TODO Finish the provider. needs a stream of biomes - gotten from BiomeRegistry - the biomes arraylist. Contains registry key of type Biome and Weight.
    public SurvivalismBiomeProvider(long seed, Registry<Biome> biomes) {
        super(Stream.concat())
    })

}
