package io.github.zumeita.survivalism.world.biome.layer.traits;

import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.LazyArea;

public class LazyAreaLayerContextSurvivalism extends LazyAreaLayerContext implements IClimateContextExtended<LazyArea>
{
    private long worldSeed;

    public LazyAreaLayerContextSurvivalism(int maxCacheSize, long seed, long seedModifier)
    {
        super(maxCacheSize, seed, seedModifier);
        this.worldSeed = seed;
    }

    @Override
    public long getWorldSeed()
    {
        return this.worldSeed;
    }
}