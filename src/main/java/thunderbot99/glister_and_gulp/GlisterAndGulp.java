package thunderbot99.glister_and_gulp;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thunderbot99.glister_and_gulp.block.ModBlocks;

public class GlisterAndGulp implements ModInitializer {
	public static final String MOD_ID = "glister_and_gulp";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
//		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
//			BlockPos pos = hitResult.getBlockPos();
//		});
//		Blocks.MELON = Blocks.register(BlockKeys.MELON, );
	}
}