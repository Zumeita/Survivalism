package io.github.zumeita.survivalism.world.feature;

import com.mojang.serialization.Codec;
import io.github.zumeita.survivalism.definitions.EarthBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class ClayFeature extends Feature<NoFeatureConfig> {

    public ClayFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, NoFeatureConfig config) {

        //TODO - Maybe we can put underwater gen here too. Not sure yet. Could include that in surface builders.

        if(!isWaterWithinRange(worldIn, blockPos, 15)) { return false; } // check this before we initialize any variables etc, otherwise waste of resources

        final BlockPos.Mutable blockPosMutable = new BlockPos.Mutable();
        int radius = 15;
        int radiusSquared = 225; // Sq = radius * radius, leave as primitive int here for speed
        int depth = random.nextInt(2) + 2; // random depth between 2 and 4 blocks. subsoil stops 4 blocks down from start of topsoil.
        int startX = blockPos.getX();
        int startY = blockPos.getY() - 2; // -2 as the top block selected will likely be Humus or similar which will not contain clay. We want to start at Topsoil.
        int startZ = blockPos.getZ();
        int tracker = 0;
        BlockState clayBlock = EarthBlocks.Type.CLAY.getBlock().defaultBlockState();

        // TODO Clay indicating plant perhaps. Need to do some research. For now this helps visualize.

        for(int x = startX - radius; x <= startX + radius; x++) {
            for(int z = startZ - radius; z <= startZ + radius; z++) {

                final int relX = x - startX;
                final int relZ = z - startZ;
                if(relX * relX + relZ * relZ <= radiusSquared) {

                    for(int y = startY - depth; y <= startY; y++) {

                        blockPosMutable.set(x, y, z);
                        final BlockState state = worldIn.getBlockState(blockPosMutable);

                        if(isTopOrSubSoil(state)) {
                            worldIn.setBlock(blockPosMutable.immutable(), clayBlock, 2);
                            tracker++;
                        }
                    }
                }
            }
        }
        return tracker > 0;
    }

    private boolean isTopOrSubSoil(BlockState state) {
        BlockState topSoilBlock = EarthBlocks.Type.TOPSOIL.getBlock().defaultBlockState();
        BlockState subSoilBlock = EarthBlocks.Type.SUBSOIL.getBlock().defaultBlockState();
        return state == topSoilBlock || state == subSoilBlock;
    }

    private boolean isWaterWithinRange(IServerWorld worldIn, BlockPos blockPos, int range) {

        int posX = blockPos.getX();
        int posY = blockPos.getY() + 1; // Start search 1 blocks above ground level.
        int posZ = blockPos.getZ();
        int relX, relZ;
        final BlockPos.Mutable blockPosMutable = new BlockPos.Mutable();
        boolean foundWater = false;

        for(int x = posX - range; x <= posX + range && !foundWater; x++) {
            for(int z = posZ - range; z <= posZ + range && !foundWater; z++) {

                relX = x - posX;
                relZ = z - posZ;

                if(relX * relX + relZ * relZ <= (range * range)) {
                    for(int y = posY - 4; y <= posY && !foundWater; y++) {

                        blockPosMutable.set(x, y, z);
                        final BlockState state = worldIn.getBlockState(blockPosMutable);
                        if(worldIn.getFluidState(blockPosMutable).is(FluidTags.WATER)) {
                            foundWater = true;
                            break;
                        }
                    }
                }
            }
        }
        return foundWater;
    }
}
