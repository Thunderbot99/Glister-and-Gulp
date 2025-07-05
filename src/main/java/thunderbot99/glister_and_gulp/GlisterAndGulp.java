package thunderbot99.glister_and_gulp;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thunderbot99.glister_and_gulp.block.ModBlocks;
import thunderbot99.glister_and_gulp.event.ModEvents;

public class GlisterAndGulp implements ModInitializer {
	public static final String MOD_ID = "glister_and_gulp";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModEvents.registerEvents();
	}
}