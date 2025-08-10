package ml.unbreakinggold.datapackinstaller.mixin.client;

import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.nio.file.Path;

@Mixin(CreateWorldScreen.class)
public interface CreateWorldScreenAccessor {
    @Accessor("dataPackTempDir")
    void setDataPackTempDir(Path path);
}
