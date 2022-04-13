package io.github.zumeita.survivalism.world.surfacebuilders;

import io.github.zumeita.survivalism.Survivalism;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class SurvivalismConfiguredSurfaceBuilders {

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CSB_POLAR = register("csb_polar", new ConfiguredSurfaceBuilder(
            SurvivalismSurfaceBuilders.SURFACE_POLAR,
            SurvivalismSurfaceBuilders.CONFIG_POLAR));

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CSB_SUBPOLAR = register("csb_subpolar", new ConfiguredSurfaceBuilder(
            SurvivalismSurfaceBuilders.SURFACE_SUBPOLAR,
            SurvivalismSurfaceBuilders.CONFIG_SUBPOLAR));

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CSB_BOREAL = register("csb_boreal", new ConfiguredSurfaceBuilder(
            SurvivalismSurfaceBuilders.SURFACE_BOREAL,
            SurvivalismSurfaceBuilders.CONFIG_BOREAL));

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CSB_COOL_TEMPERATE = register("csb_cool_temperate", new ConfiguredSurfaceBuilder(
            SurvivalismSurfaceBuilders.SURFACE_COOL_TEMPERATE,
            SurvivalismSurfaceBuilders.CONFIG_COOL_TEMPERATE));

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CSB_WARM_TEMPERATE = register("csb_warm_temperate", new ConfiguredSurfaceBuilder(
            SurvivalismSurfaceBuilders.SURFACE_WARM_TEMPERATE,
            SurvivalismSurfaceBuilders.CONFIG_WARM_TEMPERATE));

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CSB_SUBTROPICAL = register("csb_subtropical", new ConfiguredSurfaceBuilder(
            SurvivalismSurfaceBuilders.SURFACE_SUBTROPICAL,
            SurvivalismSurfaceBuilders.CONFIG_SUBTROPICAL));

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CSB_TROPICAL = register("csb_tropical", new ConfiguredSurfaceBuilder(
            SurvivalismSurfaceBuilders.SURFACE_TROPICAL,
            SurvivalismSurfaceBuilders.CONFIG_TROPICAL));


    private static <C extends ISurfaceBuilderConfig, F extends ConfiguredSurfaceBuilder<C>> F register(String key, F builder) {
        return Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(Survivalism.MOD_ID, key), builder);
    }

}
