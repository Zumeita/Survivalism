package io.github.zumeita.survivalism.registration;

import com.mojang.serialization.Codec;
import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.world.feature.ClayFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class RegisterFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Survivalism.MOD_ID);

    public static final Feature<NoFeatureConfig> CLAY = register("clay_feature", new ClayFeature(NoFeatureConfig.CODEC.stable()));

    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value)
    {
        value.setRegistryName(new ResourceLocation(Survivalism.MOD_ID, key));
        ForgeRegistries.FEATURES.register(value);
        return value;
    }

}
