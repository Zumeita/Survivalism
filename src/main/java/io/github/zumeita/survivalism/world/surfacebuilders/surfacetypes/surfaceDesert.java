package io.github.zumeita.survivalism.world.surfacebuilders.surfacetypes;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class surfaceDesert extends SurfaceBuilder<SurfaceBuilderConfig> {

    public surfaceDesert(Codec<SurfaceBuilderConfig> p_i51315_1_) {
        super(p_i51315_1_);
    }

    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        this.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTopMaterial(), config.getUnderMaterial(), config.getUnderwaterMaterial(), seaLevel);
    }

    /* BlockState STONE = Blocks.STONE.defaultBlockState();
        BlockState DIRT = Blocks.DIRT.defaultBlockState();
        BlockState SNOW_BLOCK = Blocks.SNOW_BLOCK.defaultBlockState();
        BlockState SNOW = Blocks.SNOW.defaultBlockState();
        BlockState PODZOL = Blocks.PODZOL.defaultBlockState();
        BlockState GRASS = Blocks.GRASS.defaultBlockState();
        BlockState GRAVEL = Blocks.GRAVEL.defaultBlockState();
        BlockState SAND = Blocks.SAND.defaultBlockState();

        BlockState top;
        BlockState middle = DIRT; // Default
        BlockState bottom = GRAVEL; // Default

        switch(latitudinalRegion) {
            case POLAR:
                top = Blocks.WHITE_WOOL.defaultBlockState();
                //top = SNOW_BLOCK;
                middle = SNOW_BLOCK;

            case SUBPOLAR:
                top = Blocks.GRAY_WOOL.defaultBlockState();
                //top = SNOW;

            case BOREAL:
                top = Blocks.BROWN_WOOL.defaultBlockState();
                //top = PODZOL;

            case COOL_TEMPERATE:
                top = Blocks.LIGHT_BLUE_WOOL.defaultBlockState();
            case WARM_TEMPERATE:
                top = Blocks.GREEN_WOOL.defaultBlockState();
            case SUBTROPICAL:
                top = Blocks.LIME_WOOL.defaultBlockState();
                bottom = SAND;
            case TROPICAL:
                top = Blocks.YELLOW_WOOL.defaultBlockState();
                bottom = SAND;
        }
    }

     */

    @SuppressWarnings("deprecation")
    protected void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel) {
        BlockState blockstate = top;
        BlockState blockstate1 = middle;
        BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
        int i = -1;
        int j = (int) (noise / 3.0D + 8.0D + random.nextDouble() * 0.25D); // 3 + 8 the 8 is the depth of middle block (Dirt for example)
        int k = x & 15;
        int l = z & 15;

        for (int i1 = startHeight; i1 >= 0; --i1) {
            blockpos$mutableblockpos.set(k, i1, l);
            BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutableblockpos);
            if (blockstate2.isAir()) {
                i = -1;
            } else if (blockstate2.getBlock() == defaultBlock.getBlock()) {
                if (i == -1) {
                    if (j <= 0) {
                        blockstate = Blocks.AIR.defaultBlockState();
                        blockstate1 = defaultBlock;
                    } else if (i1 >= sealevel - 4 && i1 <= sealevel + 1) {
                        blockstate = top;
                        blockstate1 = middle;
                    }

                    if (i1 < sealevel && (blockstate == null || blockstate.isAir())) {
                        if (biomeIn.getTemperature(blockpos$mutableblockpos.set(x, i1, z)) < 0.15F) {
                            blockstate = Blocks.ICE.defaultBlockState();
                        } else {
                            blockstate = defaultFluid;
                        }

                        blockpos$mutableblockpos.set(k, i1, l);
                    }

                    i = j;
                    if (i1 >= sealevel - 1) {
                        chunkIn.setBlockState(blockpos$mutableblockpos, blockstate, false);
                    } else if (i1 < sealevel - 7 - j) {
                        blockstate = Blocks.AIR.defaultBlockState();
                        blockstate1 = defaultBlock;
                        chunkIn.setBlockState(blockpos$mutableblockpos, bottom, false);
                    } else {
                        chunkIn.setBlockState(blockpos$mutableblockpos, blockstate1, false);
                    }
                } else if (i > 0) {
                    --i;
                    chunkIn.setBlockState(blockpos$mutableblockpos, blockstate1, false);
                    if (i == 0 && blockstate1.getBlock() == Blocks.SAND && j > 1) {
                        i = random.nextInt(4) + Math.max(0, i1 - 63);
                        blockstate1 = blockstate1.getBlock() == Blocks.RED_SAND ? Blocks.RED_SANDSTONE.defaultBlockState() : Blocks.SANDSTONE.defaultBlockState();
                    }
                }
            }
        }
    }
}
