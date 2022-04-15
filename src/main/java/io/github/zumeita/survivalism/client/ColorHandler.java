package io.github.zumeita.survivalism.client;
import java.util.function.ToIntFunction;

import javax.annotation.Nullable;

import io.github.zumeita.survivalism.Survivalism;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public final class ColorHandler {

    private static final int COLORMAP_SIZE = 256 * 256;
    public static final ResourceLocation FOLIAGE_COLORS_LOCATION = new ResourceLocation(Survivalism.MOD_ID, "textures/colormap/foliage.png");
    private static int[] FOLIAGE_COLORS_CACHE = new int[COLORMAP_SIZE];

    public static void setFoliageColors(int[] foliageColorsCache) {
        FOLIAGE_COLORS_CACHE = foliageColorsCache;
    }

    public static int getFoliageColor(@Nullable BlockPos pos, int tintIndex) {
        if (tintIndex == 0) {
            if (pos != null) {
                return getClimateColor(FOLIAGE_COLORS_CACHE, pos);
            }
            return getClimateColor(FOLIAGE_COLORS_CACHE, 10f, 250f); // Default values
        }
        return -1;
    }

    private static int getClimateColor(int[] colorCache, BlockPos pos)
    {
        final Minecraft minecraft = Minecraft.getInstance();
        final ClientWorld level = minecraft.level;
        final Biome current_biome = level.getBiome(pos);
        final Float temperature = current_biome.getTemperature(pos);
        final Float downfall = current_biome.getDownfall();
        return getClimateColor(colorCache, temperature, downfall);
    }

    // Temperature: Horizontal, left = high
    // Downfall: Vertical, up = high
    private static int getClimateColor(int[] colorCache, float temperature, float downfall)
    {
        final int tempIdx = (int) (temperature * 128);
        final int downfallIdx = (int) (downfall * 256);
        int ret = (tempIdx | (downfallIdx << 8));

        if(ret < 0) { ret = 0; }

        return colorCache[ret];
    }
}