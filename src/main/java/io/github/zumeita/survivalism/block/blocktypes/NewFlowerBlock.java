package io.github.zumeita.survivalism.block.blocktypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class NewFlowerBlock extends FlowerBlock
{
    protected static final VoxelShape NORMAL = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    protected static final VoxelShape LARGE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

    public NewFlowerBlock(Effect p_i49984_1_, int effectDuration, Block.Properties properties)
    {
        super(p_i49984_1_, 0, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext)
    {
        Block block = state.getBlock();
        /*if(block == BlockVars.adonis) {
            return LARGE;
        }*/
        return NORMAL;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Block ground = worldIn.getBlockState(pos.below()).getBlock();
        return ground == Blocks.GRASS_BLOCK || ground == Blocks.DIRT || ground == Blocks.PODZOL || ground == Blocks.COARSE_DIRT || super.canSurvive(state,  worldIn,  pos);
    }
}
