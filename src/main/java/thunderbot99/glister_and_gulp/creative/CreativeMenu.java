package thunderbot99.glister_and_gulp.creative;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import thunderbot99.glister_and_gulp.block.ModBlocks;

public class CreativeMenu {
    public static void registerItemGroup() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.addAfter(Items.SPIDER_EYE, ModBlocks.GLISTER_MELON_BLOCK.asItem());
            content.addAfter(Items.SPIDER_EYE, Blocks.MELON.asItem());
        });
    }
}
