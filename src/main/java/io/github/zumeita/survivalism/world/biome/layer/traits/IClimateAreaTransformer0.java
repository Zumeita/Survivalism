package io.github.zumeita.survivalism.world.biome.layer.traits;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;

/***
 * An area transformer that takes 0 existing AreaDimensions. Classes implementing
 * this interface are not required to implement any further Transformer interfaces.
 */
public interface IClimateAreaTransformer0
{
    default <R extends IArea> IAreaFactory<R> run(IExtendedNoiseRandom<R> context)
    {
        if (!(context instanceof IClimateContextExtended))
            throw new IllegalArgumentException("Context must be an IClimateContextExtended");

        IClimateContextExtended<R> climateContext = (IClimateContextExtended<R>)context;

        return () ->
                context.createResult((x, z) ->
                {
                    context.initRandom((long)(x), (long)(z));
                    return this.applyPixel(climateContext, x, z);
                });
    }

    int applyPixel(IClimateContextExtended context, int x, int z);
}
