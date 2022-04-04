package io.github.zumeita.survivalism.block.blocktypes;

import javax.annotation.Nullable;

import io.github.zumeita.survivalism.block.BlockVars;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class HerbBushBlock extends BushBlock implements IPlantable
{
    protected static final VoxelShape NORMAL = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    protected static final VoxelShape SHORT = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);

    public HerbBushBlock(Block.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext) {
        return NORMAL;
    }

    @Override
    public void playerDestroy(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        /*if (!worldIn.isClientSide && stack.getItem() == Items.SHEARS)
        {
           player.awardStat(Stats.BLOCK_MINED.get(this));
           player.causeFoodExhaustion(0.005F);
           this.popResource(worldIn, pos, new ItemStack(this));
        }*/

        super.playerDestroy(worldIn, player, pos, state, te, stack);
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos){
        BlockState groundState = worldIn.getBlockState(pos.below());
        Block ground = groundState.getBlock();

        return ground == Blocks.GRASS_BLOCK || ground == Blocks.DIRT || ground == Blocks.PODZOL || ground == Blocks.COARSE_DIRT || super.canSurvive(state,  worldIn,  pos);
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XYZ;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {

        if(this.getBlock() == BlockVars.sagebrush) {
            return PlantType.DESERT;
        }
        return PlantType.PLAINS;
    }
}