package io.github.yuko1101.setprop.mixin;

import emu.lunarcore.plugin.PluginManager;
import io.github.yuko1101.setprop.SetPropMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PluginManager.class, remap = false)
public class PluginManagerMixin {
    @Inject(method = "loadPlugins", at = @At("HEAD"))
    private void loadPlugins(CallbackInfo ci) {
        SetPropMod.INSTANCE.onLoadPlugins();
    }
}
