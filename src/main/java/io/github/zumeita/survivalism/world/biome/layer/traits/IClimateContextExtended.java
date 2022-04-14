package io.github.zumeita.survivalism.world.biome.layer.traits;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;

/***
 * Provides extra information beyond that used by Mojang.
 */
public interface IClimateContextExtended<R extends IArea> extends IExtendedNoiseRandom<R>
{
    long getWorldSeed();
}