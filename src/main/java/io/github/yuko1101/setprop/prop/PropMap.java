package io.github.yuko1101.setprop.prop;

import dev.morphia.annotations.Entity;
import emu.lunarcore.game.player.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

@Entity(useDiscriminator = false)
public class PropMap {

    @NotNull
    private final Map<String, Prop<?>> props = new HashMap<>();

    private boolean isPropsLoaded = false;

    public void registerProp(DefinedProp definedProp) {
        var prop = definedProp.createProp();
        props.put(prop.getName(), prop);
        for (String alias : prop.getAliases()) {
            props.put(alias, prop);
        }
    }

    @Nullable
    public Prop<?> getProp(String name) {
        if (!isPropsLoaded) {
            isPropsLoaded = true;
            for (var definedProp : DefinedProp.values()) {
                registerProp(definedProp);
            }
        }

        return props.get(name);
    }

    @NotNull
    public Prop<?> getProp(DefinedProp definedProp) {
        return Objects.requireNonNull(getProp(definedProp.createProp().getName()));
    }

    public boolean hasProp(String name) {
        return getProp(name) != null;
    }

    public boolean getBoolean(DefinedProp definedProp) {
        return ((BooleanProp) getProp(definedProp)).get();
    }

    public void setBoolean(DefinedProp definedProp, boolean value, Player player) {
        ((BooleanProp) getProp(definedProp)).set(value, player);
    }
}
