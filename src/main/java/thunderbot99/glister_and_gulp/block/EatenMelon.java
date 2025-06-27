package thunderbot99.glister_and_gulp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class EatenMelon extends Block {
    public static final IntProperty BITES = IntProperty.of("bites", 1, 3);
    public EatenMelon(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(BITES, 1));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int bites = state.get(BITES);
        if (bites == 1) {
            VoxelShape part1 = Block.createCuboidShape(8, 0, 0, 16, 16, 8);
            VoxelShape part2 = Block.createCuboidShape(0, 0, 8, 16, 16, 16);
            return VoxelShapes.union(part1, part2);
        }
        else if (bites == 2) {
            return Block.createCuboidShape(8, 0, 0, 16, 16, 16);
        }
        else {
            return Block.createCuboidShape(8, 0, 0, 16, 16, 8);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int bites = state.get(BITES);
        if (bites == 1) {
            VoxelShape part1 = Block.createCuboidShape(8, 0, 0, 16, 16, 8);
            VoxelShape part2 = Block.createCuboidShape(0, 0, 8, 16, 16, 16);
            return VoxelShapes.union(part1, part2);
        }
        else if (bites == 2) {
            return Block.createCuboidShape(8, 0, 0, 16, 16, 16);
        }
        else {
            return Block.createCuboidShape(8, 0, 0, 16, 16, 8);
        }
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.getHungerManager().add(4, 0.1F);
            int i = state.get(BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (i < 3) {
                world.setBlockState(pos, (BlockState)state.with(BITES, i + 1), 3);
            } else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return ActionResult.SUCCESS;
        }
    }
}
