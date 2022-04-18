package io.github.zumeita.survivalism.world.feature;

import com.mojang.serialization.Codec;
import io.github.zumeita.survivalism.definitions.EarthBlocks;
import io.github.zumeita.survivalism.definitions.VegetationBlocks;
import io.github.zumeita.survivalism.registration.blocktypes.GrowingGrassBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class VegFeature extends Feature<BlockClusterFeatureConfig> {

    public VegFeature(Codec<BlockClusterFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, BlockClusterFeatureConfig config) {

        BlockPos blockpos;
        if(config.project) {
            blockpos = worldIn.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);
        } else {
            blockpos = pos;
        }

        BlockPos.Mutable blockPosMutable = new BlockPos.Mutable();
        Block grassBlock = VegetationBlocks.Default.GRASS.create();
        int placed = 0;

        for(int i = 0; i < config.tries; i++) {

            blockPosMutable.setWithOffset(blockpos, rand.nextInt(config.xspread + 1) - rand.nextInt(config.xspread + 1), rand.nextInt(config.yspread + 1) - rand.nextInt(config.yspread + 1), rand.nextInt(config.zspread + 1) - rand.nextInt(config.zspread + 1));

            if(worldIn.isEmptyBlock(blockPosMutable)) {
                BlockPos blockBelow = blockPosMutable.below();
                BlockState blockBelowState = worldIn.getBlockState(blockBelow);
                BlockState state = config.stateProvider.getState(rand, pos);

                if(isTopLayerSoil(blockBelowState)) {
                    if(state.getBlock() == VegetationBlocks.Default.GRASS.getBlock()) {
                        worldIn.setBlock(blockPosMutable, VegetationBlocks.Default.GRASS.getBlock().defaultBlockState().setValue(GrowingGrassBlock.AGE, rand.nextInt(15)), 2);
                    } else {
                        worldIn.setBlock(blockPosMutable, state, 2);
                    }

                    placed++;
                }
            }
        }
        return placed > 0;
    }

    public boolean isTopLayerSoil(BlockState state) {
        return state.is(EarthBlocks.Type.HUMUS.getBlock());
    }
}
