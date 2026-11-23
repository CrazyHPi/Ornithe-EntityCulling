package dev.tr7zw.entityculling.mixin;

import dev.tr7zw.entityculling.EntityCullingMod;
import dev.tr7zw.entityculling.access.Cullable;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntityRendererDispatcher.class)
public class BlockEntityRenderDispatcherMixin {
    
    @Inject(method = "render(Lnet/minecraft/tileentity/TileEntity;DDDFIF)V", at = @At("HEAD"), cancellable = true)
    public void renderTileEntityAt(TileEntity blockEntity, double x, double y, double z, float partialTicks, int destroyStage, float p_192854_10_, CallbackInfo info) {
        if (!((Cullable) blockEntity).isForcedVisible() && ((Cullable) blockEntity).isCulled()) {
            EntityCullingMod.instance.skippedBlockEntities++;
            info.cancel();
            return;
        }
        EntityCullingMod.instance.renderedBlockEntities++;
    }
    
    @Inject(method = "render(Lnet/minecraft/tileentity/TileEntity;FI)V", at = @At("HEAD"), cancellable = true)
    public void render(TileEntity blockEntity, float partialTicks, int destroyStage, CallbackInfo info) {
        if (!((Cullable) blockEntity).isForcedVisible() && ((Cullable) blockEntity).isCulled()) {
            EntityCullingMod.instance.skippedBlockEntities++;
            info.cancel();
        }
    }

}
