package dev.tr7zw.entityculling.mixin.event;

import dev.tr7zw.entityculling.EntityCullingMod;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(
            method = "runTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/profiler/Profiler;startSection(Ljava/lang/String;)V",
                    ordinal = 0
            )
    )
    private void onPreClientTick(CallbackInfo ci) {
        if (EntityCullingMod.instance != null) {
            EntityCullingMod.instance.clientTick();
        }
    }

    @Inject(
            method = "runTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;getSystemTime()J"
            )
    )
    private void onPostClientTick(CallbackInfo ci) {
        if (EntityCullingMod.instance != null) {
            EntityCullingMod.instance.clientTick();
        }
    }
}
