package thunderbot99.glister_and_gulp.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import thunderbot99.glister_and_gulp.block.EatenMelon;

public class ModEvents {
    public static void registerEvents() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            var pos = hitResult.getBlockPos();
            var state = world.getBlockState(pos);

            if (state.isOf(Blocks.MELON) && player.canConsume(false)) {
                Vec3d playerPos = player.getPos();
                if (playerPos.x < (pos.getX() + 0.5) && playerPos.z < (pos.getZ() + 0.5)) {
                    world.setBlockState(pos,
                            thunderbot99.glister_and_gulp.block.ModBlocks.EATEN_MELON.getDefaultState().with(EatenMelon.FACING, Direction.NORTH),
                            Block.NOTIFY_ALL
                    );
                }
                if (playerPos.x < (pos.getX() + 0.5) && playerPos.z >= (pos.getZ() + 0.5)) {
                    world.setBlockState(pos,
                            thunderbot99.glister_and_gulp.block.ModBlocks.EATEN_MELON.getDefaultState().with(EatenMelon.FACING, Direction.WEST),
                            Block.NOTIFY_ALL
                    );
                }
                if (playerPos.x >= (pos.getX() + 0.5) && playerPos.z < (pos.getZ() + 0.5)) {
                    world.setBlockState(pos,
                            thunderbot99.glister_and_gulp.block.ModBlocks.EATEN_MELON.getDefaultState().with(EatenMelon.FACING, Direction.EAST),
                            Block.NOTIFY_ALL
                    );
                }
                if (playerPos.x >= (pos.getX() + 0.5) && playerPos.z >= (pos.getZ() + 0.5)) {
                    world.setBlockState(pos,
                            thunderbot99.glister_and_gulp.block.ModBlocks.EATEN_MELON.getDefaultState().with(EatenMelon.FACING, Direction.SOUTH),
                            Block.NOTIFY_ALL
                    );
                }

                player.getHungerManager().add(4, 0.1F);
                player.swingHand(hand);
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });
    }
}
