package thunderbot99.glister_and_gulp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class EatenMelon extends Block {
    public static final IntProperty BITES = IntProperty.of("bites", 1, 3);
    public static final EnumProperty<Direction> FACING = EnumProperty.of("facing", Direction .class, new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST});
    public EatenMelon(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(BITES, 1));
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES, FACING);
    }

    void shiftShape(int num, VoxelShape[] parts){
        VoxelShape temp;
        for(int i = 0; i < num; i++) {
            temp = parts[0];
            for(int j = 1; j < 4; j++) {
                parts[j - 1] = parts[j];
            }
            parts[3] = temp;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int bites = state.get(BITES);
        Direction direction = state.get(FACING);
        VoxelShape[] parts = new VoxelShape[4];
        parts[0] = Block.createCuboidShape(0, 0, 0, 8, 16, 8);
        parts[1] = Block.createCuboidShape(0, 0, 8, 8, 16, 16);
        parts[2] = Block.createCuboidShape(8, 0, 8, 16, 16, 16);
        parts[3] = Block.createCuboidShape(8, 0, 0, 16, 16, 8);
        if (direction == Direction.WEST) {
            shiftShape(1, parts);
        }
        if (direction == Direction.SOUTH) {
            shiftShape(2, parts);
        }
        if (direction == Direction.EAST) {
            shiftShape(3, parts);
        }
        VoxelShape combined = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
        for (int i = bites; i < 4; i++) {
            combined = VoxelShapes.union(combined, parts[i]);
        }
        return combined;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int bites = state.get(BITES);
        Direction direction = state.get(FACING);
        VoxelShape[] parts = new VoxelShape[4];
        parts[0] = Block.createCuboidShape(0, 0, 0, 8, 16, 8);
        parts[1] = Block.createCuboidShape(0, 0, 8, 8, 16, 16);
        parts[2] = Block.createCuboidShape(8, 0, 8, 16, 16, 16);
        parts[3] = Block.createCuboidShape(8, 0, 0, 16, 16, 8);
        if (direction == Direction.WEST) {
            shiftShape(1, parts);
        }
        if (direction == Direction.SOUTH) {
            shiftShape(2, parts);
        }
        if (direction == Direction.EAST) {
            shiftShape(3, parts);
        }
        VoxelShape combined = Block.createCuboidShape(0, 0, 0, 0, 0, 0);
        for (int i = bites; i < 4; i++) {
            combined = VoxelShapes.union(combined, parts[i]);
        }
        return combined;
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
