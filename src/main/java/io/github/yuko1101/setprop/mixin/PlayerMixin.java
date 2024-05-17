package io.github.yuko1101.setprop.mixin;

import emu.lunarcore.game.player.Player;
import io.github.yuko1101.setprop.extension.PlayerExtension;
import io.github.yuko1101.setprop.prop.BooleanProp;
import io.github.yuko1101.setprop.prop.IntegerProp;
import io.github.yuko1101.setprop.prop.Prop;
import io.github.yuko1101.setprop.prop.PropMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;

@Mixin(value = Player.class, remap = false)
public class PlayerMixin implements PlayerExtension {
    @Unique
    private transient final PropMap props = new PropMap();

    @Unique
    @Override
    public PropMap setPropMod$getProps() {
        return props;
    }

    @Unique
    @Override
    @Nullable
    public Prop<?> setPropMod$setProp(String name, int value) {
        var prop = (IntegerProp) props.getProp(name);
        if (prop != null) prop.set(value, (Player)(Object) this);
        return prop;
    }

    @Unique
    @Override
    @Nullable
    public Prop<?> setPropMod$setProp(String name, boolean value) {
        var prop = (BooleanProp) props.getProp(name);
        if (prop != null) prop.set(value, (Player)(Object) this);
        return prop;
    }
}
