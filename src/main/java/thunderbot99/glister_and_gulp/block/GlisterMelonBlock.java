package thunderbot99.glister_and_gulp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GlisterMelonBlock extends Block {
    public GlisterMelonBlock(Settings settings) {
        super(settings);
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        Vec3d playerPos = player.getPos();
        if (playerPos.x < (pos.getX() + 0.5) && playerPos.z < (pos.getZ() + 0.5)) {
            world.setBlockState(pos,
                    thunderbot99.glister_and_gulp.block.ModBlocks.EATEN_GLISTER_MELON.getDefaultState().with(EatenGlisterMelon.FACING, Direction.NORTH),
                    Block.NOTIFY_ALL
            );
        }
        if (playerPos.x < (pos.getX() + 0.5) && playerPos.z >= (pos.getZ() + 0.5)) {
            world.setBlockState(pos,
                    thunderbot99.glister_and_gulp.block.ModBlocks.EATEN_GLISTER_MELON.getDefaultState().with(EatenGlisterMelon.FACING, Direction.WEST),
                    Block.NOTIFY_ALL
            );
        }
        if (playerPos.x >= (pos.getX() + 0.5) && playerPos.z < (pos.getZ() + 0.5)) {
            world.setBlockState(pos,
                    thunderbot99.glister_and_gulp.block.ModBlocks.EATEN_GLISTER_MELON.getDefaultState().with(EatenGlisterMelon.FACING, Direction.EAST),
                    Block.NOTIFY_ALL
            );
        }
        if (playerPos.x >= (pos.getX() + 0.5) && playerPos.z >= (pos.getZ() + 0.5)) {
            world.setBlockState(pos,
                    thunderbot99.glister_and_gulp.block.ModBlocks.EATEN_GLISTER_MELON.getDefaultState().with(EatenGlisterMelon.FACING, Direction.SOUTH),
                    Block.NOTIFY_ALL
            );
        }

        player.heal(2f);
        return ActionResult.SUCCESS;
    }
}
