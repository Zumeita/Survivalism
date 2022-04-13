package io.github.zumeita.survivalism.block.blocktypes;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("deprecation")
public class HumusBlock extends Block implements IGrassOntop {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final Map<Direction, BooleanProperty> PROPERTIES = ImmutableMap.of(Direction.NORTH, NORTH, Direction.EAST, EAST, Direction.WEST, WEST, Direction.SOUTH, SOUTH);

    public static final BooleanProperty GRASS =  BooleanProperty.create("grass"); // if the block has grass ontop or not.
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

    public HumusBlock(Properties properties) {
        super(properties.hasPostProcess(HumusBlock::always));
        registerDefaultState(stateDefinition.any().setValue(SOUTH, false).setValue(EAST, false).setValue(NORTH, false).setValue(WEST, false).setValue(SNOWY, false).setValue(GRASS, true));
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (facing == Direction.UP) {
            if(facingState.getBlock() instanceof HumusBlock) {
                if (facingState.getValue(SNOWY) || facingState == Blocks.SNOW.defaultBlockState() || facingState == Blocks.SNOW_BLOCK.defaultBlockState()) {
                    return stateIn.setValue(SNOWY, true);
                }
            }
        } else if (facing != Direction.DOWN) {
            return updateStateFromDirection(worldIn, currentPos, stateIn, facing);
        }
        return stateIn;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
    {
        worldIn.getBlockTicks().scheduleTick(pos, this, 0);
    }

    @Override
    public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        for (Direction direction : Direction.Plane.HORIZONTAL)
        {
            worldIn.getBlockTicks().scheduleTick(pos.relative(direction).above(), this, 0);
        }
    }

    @Override
    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        for (Direction direction : Direction.Plane.HORIZONTAL)
        {
            worldIn.getBlockTicks().scheduleTick(pos.relative(direction).above(), this, 0);
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (!canBeCoveredInGrass(state, worldIn, pos))
        {
            if (worldIn.isAreaLoaded(pos, 3))
            {
                // Turn to not-grass
                worldIn.setBlockAndUpdate(pos, state.setValue(GRASS, false));
            }
        }
        else
        {
            if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9)
            {
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos posAt = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    BlockState stateAt = worldIn.getBlockState(posAt);
                    if (stateAt.getBlock() instanceof IGrassOntop && !stateAt.getValue(GRASS))
                    {
                        // TODO - This doesn't seem to be working. Needs more debug.
                        BlockState grassState = stateAt.getBlockState();
                        if (canPropagate(grassState, worldIn, posAt))
                        {
                            stateAt.setValue(GRASS, true);
                            worldIn.setBlockAndUpdate(posAt, updateStateFromNeighbors(worldIn, posAt, grassState));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        if (worldIn.isAreaLoaded(pos, 2))
        {
            worldIn.setBlock(pos, updateStateFromNeighbors(worldIn, pos, state), 2);
            //updateSurroundingGrassConnections(worldIn, pos);
        }
    }

    @Override // No grass when placed manually.
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState stateUp = context.getLevel().getBlockState(context.getClickedPos().above());
        return updateStateFromNeighbors(context.getLevel(), context.getClickedPos(), defaultBlockState()).setValue(GRASS, false);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(NORTH, EAST, SOUTH, WEST, GRASS, SNOWY);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType)
    {
        if (toolType == ToolType.HOE) // farmland != null
        {
            return Blocks.FARMLAND.defaultBlockState(); // TODO custom farmland in the future.
        }
        else if (toolType == ToolType.SHOVEL)
        {
            return Blocks.GRASS_PATH.defaultBlockState();
        }
        return state;
    }

    protected void updateSurroundingGrassConnections(IWorld world, BlockPos pos)
    {
        if (world.isAreaLoaded(pos, 2))
        {
            for (Direction direction : Direction.Plane.HORIZONTAL)
            {
                BlockPos targetPos = pos.above().relative(direction);
                BlockState targetState = world.getBlockState(targetPos);
                if (targetState.getBlock() instanceof IGrassOntop)
                {
                    world.setBlock(targetPos, updateStateFromDirection(world, targetPos, targetState, direction.getOpposite()), 2);
                }
            }
        }
    }

    protected BlockState updateStateFromNeighbors(IBlockReader worldIn, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.Plane.HORIZONTAL)
        {
            state = updateStateFromDirection(worldIn, pos, state, direction);
        }
        return state;
    }

    protected BlockState updateStateFromDirection(IBlockReader worldIn, BlockPos pos, BlockState stateIn, Direction direction) {
        BlockState blockStateBelow = worldIn.getBlockState(pos.relative(direction).below());
        BlockState blockStateReturn = stateIn;

        if(blockStateBelow.getBlock() instanceof IGrassOntop || blockStateBelow.getBlock() instanceof SoilBlock  || blockStateBelow.is(Tags.Blocks.DIRT)) {
            blockStateReturn = stateIn.setValue(PROPERTIES.get(direction), true);
        } else {
            blockStateReturn = stateIn.setValue(PROPERTIES.get(direction), false);
        }
        return blockStateReturn;
    }
    public static boolean always(BlockState state, IBlockReader world, BlockPos pos) { return true; }




}
