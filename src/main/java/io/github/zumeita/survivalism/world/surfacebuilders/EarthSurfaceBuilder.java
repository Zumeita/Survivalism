package io.github.zumeita.survivalism.world.surfacebuilders;

import com.mojang.serialization.Codec;
import io.github.zumeita.survivalism.definitions.EarthBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class EarthSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public EarthSurfaceBuilder(Codec<SurfaceBuilderConfig> config) {
        super(config);
    }

    @Override
    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        this.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTopMaterial(), config.getUnderMaterial(), config.getUnderwaterMaterial(), seaLevel);
    }

    protected void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel) {

        BlockState topBlock = EarthBlocks.Type.HUMUS.getBlock().defaultBlockState();
        BlockState middleBlock = EarthBlocks.Type.TOPSOIL.getBlock().defaultBlockState();
        BlockState bottomBlock = EarthBlocks.Type.SUBSOIL.getBlock().defaultBlockState();
        BlockState underwaterBlock = bottom;
        BlockPos.Mutable currentBlockPos = new BlockPos.Mutable();

        int isAirAboveCurrentBlock = -1; // -1 = yes.
        int noiseResult = (int)(noise / 3.0D + 5.0D + random.nextDouble() * 0.25D); // Right 3.0 is apparently the depth of middle block
        int posX = x & 15;
        int posZ = z & 15;

        int topSoilCounter = 0;

        for(int currentHeight = startHeight; currentHeight >= 0; currentHeight--) {

            currentBlockPos.set(posX, currentHeight, posZ);
            BlockState currentBlockState = chunkIn.getBlockState(currentBlockPos);

            if(currentBlockState.isAir()) {
                isAirAboveCurrentBlock = -1; // Yes.
            }
            else if(currentBlockState.getBlock() == defaultBlock.getBlock()) { // Found solid land.
                if (isAirAboveCurrentBlock == -1) {
                    if (noiseResult <= 0) {
                        topBlock = Blocks.AIR.defaultBlockState();
                        middleBlock = defaultBlock;
                        bottomBlock = defaultBlock;
                    }

                    if (currentHeight < sealevel && (topBlock.isAir())) {
                        if (biomeIn.getTemperature(currentBlockPos.set(x, currentHeight, z)) < 0.15F) {
                            topBlock = Blocks.ICE.defaultBlockState();
                        } else {
                            topBlock = defaultFluid;
                        }

                        currentBlockPos.set(posX, currentHeight, posZ);
                    }

                    isAirAboveCurrentBlock = noiseResult;

                    if (currentHeight >= sealevel - 1) {

                        chunkIn.setBlockState(currentBlockPos, topBlock, false);

                    } else if (currentHeight < sealevel - 7 - noiseResult) {
                        topBlock = Blocks.AIR.defaultBlockState();
                        middleBlock = defaultBlock;
                        chunkIn.setBlockState(currentBlockPos, middleBlock, false);
                    } else {
                        chunkIn.setBlockState(currentBlockPos, middleBlock, false);
                    }

                } else if (isAirAboveCurrentBlock > 0) {

                    if (topSoilCounter < 2) { // 2 = Depth of Top soil. Anything under that will be Subsoil.
                        chunkIn.setBlockState(currentBlockPos, middleBlock, false);
                        topSoilCounter++;
                    } else {
                        chunkIn.setBlockState(currentBlockPos, bottomBlock, false);
                    }

                    isAirAboveCurrentBlock--;

                }
            }
            chunkIn.markPosForPostprocessing(currentBlockPos); // This took me 3+ hours to figure out. FFS. (To get grass sides to render instantly rather than slowly pop in.
        }
    }
}
