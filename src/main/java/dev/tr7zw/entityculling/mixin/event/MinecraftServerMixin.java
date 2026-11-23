package dev.tr7zw.entityculling.mixin.event;

import com.llamalad7.mixinextras.sugar.Local;
import dev.tr7zw.entityculling.EntityCullingMod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(
            method = "updateTimeLightAndEntities",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/profiler/Profiler;startSection(Ljava/lang/String;)V",
                    ordinal = 2,
                    shift = At.Shift.AFTER
            )
    )
    private void onPreWorldTick(CallbackInfo ci, @Local WorldServer world) {
        if (world.isRemote && EntityCullingMod.instance != null) {
            EntityCullingMod.instance.worldTick();
        }
    }

    @Inject(
            method = "updateTimeLightAndEntities",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/profiler/Profiler;endSection()V",
                    ordinal = 1
            )
    )
    private void onPostWorldTick(CallbackInfo ci, @Local WorldServer world) {
        if (world.isRemote && EntityCullingMod.instance != null) {
            EntityCullingMod.instance.worldTick();
        }
    }
}
