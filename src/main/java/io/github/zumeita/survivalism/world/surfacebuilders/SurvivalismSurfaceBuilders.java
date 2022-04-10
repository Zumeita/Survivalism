package io.github.zumeita.survivalism.world.surfacebuilders;


import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.world.surfacebuilders.surfacetypes.surfaceDesert;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class SurvivalismSurfaceBuilders {

    // Blockstates
    private static final BlockState DIRT = Blocks.DIRT.defaultBlockState();
    private static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();
    private static final BlockState GRAVEL = Blocks.GRAVEL.defaultBlockState();
    private static final BlockState STONE = Blocks.STONE.defaultBlockState();
    private static final BlockState SNOW_BLOCK = Blocks.SNOW_BLOCK.defaultBlockState();
    private static final BlockState SNOW = Blocks.SNOW.defaultBlockState();
    private static final BlockState PODZOL = Blocks.PODZOL.defaultBlockState();
    private static final BlockState GRASS = Blocks.GRASS.defaultBlockState();
    private static final BlockState SAND = Blocks.SAND.defaultBlockState();

    // Configurations
    public static final SurfaceBuilderConfig CONFIG_POLAR = new SurfaceBuilderConfig(SNOW_BLOCK, SNOW_BLOCK, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_SUBPOLAR = new SurfaceBuilderConfig(SNOW, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_BOREAL = new SurfaceBuilderConfig(PODZOL, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_COOL_TEMPERATE = new SurfaceBuilderConfig(GRASS, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_WARM_TEMPERATE = new SurfaceBuilderConfig(GRASS, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_SUBTROPICAL = new SurfaceBuilderConfig(GRASS, DIRT, SAND);
    public static final SurfaceBuilderConfig CONFIG_TROPICAL = new SurfaceBuilderConfig(GRASS, DIRT, SAND);

    // Builders
    public static final SurfaceBuilder<SurfaceBuilderConfig> SURFACE_POLAR = register("surface_polar", new DefaultSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SURFACE_SUBPOLAR = register("surface_subpolar", new DefaultSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SURFACE_BOREAL = register("surface_boreal", new DefaultSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SURFACE_COOL_TEMPERATE = register("surface_cool_temperate", new DefaultSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SURFACE_WARM_TEMPERATE = register("surface_warm_temperate", new DefaultSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SURFACE_SUBTROPICAL = register("surface_subtropical", new DefaultSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SURFACE_TROPICAL = register("surface_tropical", new DefaultSurfaceBuilder(SurfaceBuilderConfig.CODEC));

    // Methods
    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builder)
    {
        builder.setRegistryName(new ResourceLocation(Survivalism.MOD_ID, key));
        ForgeRegistries.SURFACE_BUILDERS.register(builder);
        return builder;
    }
}
