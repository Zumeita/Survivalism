package io.github.zumeita.survivalism.block.blocktypes;

import net.minecraft.block.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.lighting.LightEngine;

public interface IGrassOntop {

    default boolean canBeCoveredInGrass(BlockState thisBlockState, IWorldReader worldIn, BlockPos thisBlockPos) {

        BlockPos positionOfBlockAbove = thisBlockPos.above();
        BlockState stateOfBlockAbove = worldIn.getBlockState(positionOfBlockAbove);

        if(stateOfBlockAbove.getFluidState().getAmount() == 8) {
            return false;
        }
        boolean tempReturn = LightEngine.getLightBlockInto(worldIn, thisBlockState, thisBlockPos, stateOfBlockAbove, positionOfBlockAbove, Direction.UP, stateOfBlockAbove.getLightBlock(worldIn, positionOfBlockAbove)) < worldIn.getMaxLightLevel();
        return tempReturn;
    }

    default boolean canPropagate(BlockState thisBlockState, IWorldReader worldIn, BlockPos thisBlockPos) {
        BlockPos positionOfBlockAbove = thisBlockPos.above();
        return canBeCoveredInGrass(thisBlockState, worldIn, thisBlockPos) && !worldIn.getFluidState(positionOfBlockAbove).is(FluidTags.WATER);
    }
}
