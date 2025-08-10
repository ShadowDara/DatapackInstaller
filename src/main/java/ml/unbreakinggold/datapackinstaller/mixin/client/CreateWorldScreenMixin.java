package ml.unbreakinggold.datapackinstaller.mixin.client;

import ml.unbreakinggold.datapackinstaller.client.DatapackInstallerClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.world.GeneratorOptionsHolder;
import net.minecraft.world.level.LevelInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {
    @Unique
    private static final Logger LOGGER = LogManager.getLogger(CreateWorldScreenMixin.class);

    /**
     * Inject after CreateWorldScreen.create(...) finishes to replace the data pack directory with our persistent one.
     */
    @Inject(
            method = "create",
            at = @At("RETURN")
    )
    private static void onCreateReturn(MinecraftClient client, Screen parent, LevelInfo levelInfo, GeneratorOptionsHolder generatorOptionsHolder, Path dataPackTempDir, CallbackInfoReturnable<CreateWorldScreen> cir) {
        System.out.println("Running on Create Return");
        CreateWorldScreen screen = cir.getReturnValue();
        ((CreateWorldScreenAccessor) screen).setDataPackTempDir(DatapackInstallerClient.MAIN_PATH);
    }

    /**
     * @author Jomar Milan - July 31st, 2024 - Minecraft 1.20.5
     * @reason Because dataPackTempDir is now persistent and not temporary, the directory should no longer be cleared.
     */
    @Overwrite
    private void clearDataPackTempDir() {
        LOGGER.info("Suppressing attempt to clear data pack directory");
    }
}
