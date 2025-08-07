package thunderbot99.glister_and_gulp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class GlisterAndGulpClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricLoader.getInstance()
                        .getModContainer(GlisterAndGulp.MOD_ID)
                                .ifPresent(modContainer -> {
                                    ResourceManagerHelper.registerBuiltinResourcePack(
                                            Identifier.of(GlisterAndGulp.MOD_ID, "wallpaper"),
                                            modContainer,
                                            ResourcePackActivationType.NORMAL
                                    );
                                });
    }
}
