package io.github.zumeita.survivalism.world.surfacebuilders;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class SoilSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public SoilSurfaceBuilder(Codec<SurfaceBuilderConfig> config) {
        super(config);
    }

    @Override
    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        this.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTopMaterial(), config.getUnderMaterial(), config.getUnderwaterMaterial(), seaLevel);
    }

    protected void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel) {
        BlockState top_block = top;
        BlockState middleBlock = middle;
        BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
        int i = -1;
        int j = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D); // 3 + 8 the 8 is the depth of middle block (Dirt for example)
        int k = x & 15;
        int l = z & 15;

        for(int i1 = startHeight; i1 >= 0; --i1) {
            blockpos$mutableblockpos.set(k, i1, l);
            BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutableblockpos);
            if (blockstate2.isAir()) {
                i = -1;
            } else if (blockstate2.getBlock() == defaultBlock.getBlock()) {
                if (i == -1) {
                    if (j <= 0) {
                        top_block = Blocks.AIR.defaultBlockState();
                        middleBlock = defaultBlock;
                    } else if (i1 >= sealevel - 4 && i1 <= sealevel + 1) {
                        top_block = top;
                        middleBlock = middle;
                    }

                    if (i1 < sealevel && (top_block == null || top_block.isAir())) {
                        if (biomeIn.getTemperature(blockpos$mutableblockpos.set(x, i1, z)) < 0.15F) {
                            top_block = Blocks.ICE.defaultBlockState();
                        } else {
                            top_block = defaultFluid;
                        }

                        blockpos$mutableblockpos.set(k, i1, l);
                    }

                    i = j;
                    if (i1 >= sealevel - 1) {
                        chunkIn.setBlockState(blockpos$mutableblockpos, top_block, false);
                    } else if (i1 < sealevel - 7 - j) {
                        top_block = Blocks.AIR.defaultBlockState();
                        middleBlock = defaultBlock;
                        chunkIn.setBlockState(blockpos$mutableblockpos, bottom, false);
                    } else {
                        chunkIn.setBlockState(blockpos$mutableblockpos, middleBlock, false);
                    }
                } else if (i > 0) {
                    --i;
                    chunkIn.setBlockState(blockpos$mutableblockpos, middleBlock, false);
                    if (i == 0 && middleBlock.getBlock() == Blocks.SAND && j > 1) {
                        i = random.nextInt(4) + Math.max(0, i1 - 63);
                        middleBlock = middleBlock.getBlock() == Blocks.RED_SAND ? Blocks.RED_SANDSTONE.defaultBlockState() : Blocks.SANDSTONE.defaultBlockState();
                    }
                }
            }
        }

    }
}
