package thunderbot99.glister_and_gulp.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ActionResult;

public class ModEvents {
    public static void registerEvents() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            var pos = hitResult.getBlockPos();
            var state = world.getBlockState(pos);

            if (state.isOf(Blocks.MELON) && player.canConsume(false)) {
                world.setBlockState(pos,
                        thunderbot99.glister_and_gulp.block.ModBlocks.EATEN_MELON.getDefaultState(),
                        Block.NOTIFY_ALL
                );
                player.getHungerManager().add(4, 0.1F);
                player.swingHand(hand);
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });
    }
}
