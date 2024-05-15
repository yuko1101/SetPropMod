package io.github.yuko1101.setprop.prop;

import emu.lunarcore.game.player.Player;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class BooleanProp extends Prop<Boolean> {

    public BooleanProp(String name, Boolean defaultValue, @Nullable Player player, BiConsumer<Prop<Boolean>, Boolean> onChange) {
        super(name, defaultValue, player, onChange);
    }

    @Nullable
    @Override
    public Boolean parse(String value) {
        if (value.equalsIgnoreCase("true")) {
            return this.set(true);
        } else if (value.equalsIgnoreCase("false")) {
            return this.set(false);
        }
        try {
            var i = Integer.parseInt(value);
            if (i == 0) {
                return this.set(false);
            } else if (i == 1) {
                return this.set(true);
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Prop<Boolean> copyWith(Player player) {
        var prop = new BooleanProp(getName(), getDefaultValue(), player, onChange);
        prop.set(get());
        return prop;
    }
}
