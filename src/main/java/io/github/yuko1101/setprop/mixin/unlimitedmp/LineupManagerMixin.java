package io.github.yuko1101.setprop.mixin.unlimitedmp;

import emu.lunarcore.game.player.Player;
import emu.lunarcore.game.player.lineup.LineupManager;
import emu.lunarcore.proto.StaminaInfoScNotifyOuterClass;
import emu.lunarcore.server.packet.send.PacketSceneCastSkillMpUpdateScNotify;
import emu.lunarcore.server.packet.send.PacketSyncLineupNotify;
import io.github.yuko1101.setprop.extension.PlayerExtension;
import io.github.yuko1101.setprop.prop.DefinedProp;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LineupManager.class, remap = false)
public class LineupManagerMixin {
    @Shadow private transient Player player;

    @Inject(method = "addMp", at = @At("HEAD"), cancellable = true)
    private void addMp(int i, CallbackInfo ci) {
        var playerMixin = (PlayerExtension) player;
        if (playerMixin.setPropMod$getBoolean(DefinedProp.UNLIMITED_MP)) {
            sendPacket();
            ci.cancel();
        }
    }

    @Inject(method = "removeMp", at = @At("HEAD"), cancellable = true)
    private void removeMp(int i, CallbackInfo ci) {
        var playerMixin = (PlayerExtension) player;
        if (playerMixin.setPropMod$getBoolean(DefinedProp.UNLIMITED_MP)) {
            sendPacket();
            ci.cancel();
        }
    }

    @Inject(method = "setMp", at = @At("HEAD"), cancellable = true)
    private void setMp(int i, CallbackInfo ci) {
        var playerMixin = (PlayerExtension) player;
        if (playerMixin.setPropMod$getBoolean(DefinedProp.UNLIMITED_MP)) {
            sendPacket();
            ci.cancel();
        }
    }

    @Unique
    private void sendPacket() {
        player.sendPacket(new PacketSyncLineupNotify(player.getCurrentLineup()));
    }
}
