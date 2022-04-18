package io.github.zumeita.survivalism.registration.blocktypes;

import io.github.zumeita.survivalism.definitions.EarthBlocks;
import net.minecraft.block.*;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

import java.util.Properties;
import java.util.Random;

@SuppressWarnings("deprecation")
public class GrowingGrassBlock extends BushBlock implements IPlantable {

    // 3 Growth models, raining = 1 growth. (3).
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 15);

    public GrowingGrassBlock(Properties properties) {
        super(properties);

        int randomAge = new Random().nextInt(15);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, randomAge));
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 15;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        int age = state.getValue(AGE);

        if(age < 15 && worldIn.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state,random.nextInt(15) == 0)) {

            if(worldIn.isRaining()) {
                age+= 3;
                if(age > 15) {
                    age = 15;
                }
            } else {
                age += 1;
            }

            worldIn.setBlock(pos, state.setValue(AGE, age), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }
    }


    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        if (state.getBlock() == this) {
            return worldIn.getBlockState(blockpos).canSustainPlant(worldIn, blockpos, Direction.UP, this);
        }
        return this.mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        if(state.is(EarthBlocks.Type.HUMUS.getBlock())) {
            if (state.getValue(HumusBlock.GRASS)) { // Only allow grass to grow on grassy version of Humus, not the bare version.
                return true;
            }
        }
        return false;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state) {
        state.add(AGE);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state2, IWorld worldIn, BlockPos pos, BlockPos pos2) {
        return !state.canSurvive(worldIn, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, state2, worldIn, pos, pos2);
    }
}
