package thunderbot99.glister_and_gulp.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import thunderbot99.glister_and_gulp.block.EatenMelon;

public class ModEvents {
    public static void registerEvents() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            var pos = hitResult.getBlockPos();
            var state = world.getBlockState(pos);
            var use = false;
            if (player.isSneaking() && (player.getMainHandStack().getItem() == Items.AIR) && (player.getOffHandStack().getItem() == Items.AIR)) {
                use = true;
            }
            if (!player.isSneaking()) {
                use = true;
            }
            if (use) {
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
            }

            return ActionResult.PASS;
        });
        UseItemCallback.EVENT.register(((playerEntity, world, hand) -> {
            if (playerEntity.getMainHandStack().getItem() == Items.GLISTERING_MELON_SLICE) {
                if (!playerEntity.getMainHandStack().getComponents().contains(DataComponentTypes.CONSUMABLE)) {
                    playerEntity.getMainHandStack().set(DataComponentTypes.CONSUMABLE, ConsumableComponent.builder().consumeSeconds(1.0f).build());
                    return ActionResult.PASS;
                }
            }
            if (playerEntity.getOffHandStack().getItem() == Items.GLISTERING_MELON_SLICE) {
                if (!playerEntity.getOffHandStack().getComponents().contains(DataComponentTypes.CONSUMABLE)) {
                    playerEntity.getOffHandStack().set(DataComponentTypes.CONSUMABLE, ConsumableComponent.builder().consumeSeconds(1.0f).build());
                    return ActionResult.PASS;
                }
            }
            return ActionResult.PASS;
        }));

    }
}
