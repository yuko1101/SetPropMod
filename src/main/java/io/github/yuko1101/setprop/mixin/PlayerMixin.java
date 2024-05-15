package io.github.yuko1101.setprop.mixin;

import emu.lunarcore.game.player.Player;
import emu.lunarcore.server.game.GameSession;
import io.github.yuko1101.setprop.extension.PlayerExtension;
import io.github.yuko1101.setprop.prop.BooleanProp;
import io.github.yuko1101.setprop.prop.DefinedProp;
import io.github.yuko1101.setprop.prop.Prop;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(value = Player.class, remap = false)
public class PlayerMixin implements PlayerExtension {
    @Unique private final Map<String, Prop<?>> props = new HashMap<>();

    @Unique
    @Override
    public <T> void setPropMod$putProp(Prop<T> prop, List<String> aliases) {
        props.put(prop.getName().toLowerCase(), prop);
        for (var alias : aliases) {
            props.put(alias.toLowerCase(), prop);
        }
    }

    @Unique
    @Override
    public Prop<?> setPropMod$getProp(String alias) {
        if (props.isEmpty()) {
            initProps();
        }

        return props.get(alias.toLowerCase());
    }

    @Unique
    @Override
    public Prop<?> setPropMod$getProp(DefinedProp definedProp) {
        return setPropMod$getProp(definedProp.createProp(null).getName());
    }

    @Unique
    @Override
    public boolean setPropMod$hasProp(String alias) {
        return setPropMod$getProp(alias) != null;
    }

    @Unique
    @Override
    public boolean setPropMod$getBoolean(DefinedProp definedProp) {
        return ((BooleanProp) setPropMod$getProp(definedProp)).get();
    }

    @Unique
    @Override
    public void setPropmod$setBoolean(DefinedProp definedProp, boolean value) {
        ((BooleanProp) setPropMod$getProp(definedProp)).set(value);
    }

    @Unique
    private void initProps() {
        for (var definedProp: DefinedProp.values()) {
            setPropMod$putProp(definedProp.createProp((Player)(Object) this), definedProp.aliases);
        }
    }
}
