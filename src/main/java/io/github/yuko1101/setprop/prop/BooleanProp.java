package io.github.yuko1101.setprop.prop;

import emu.lunarcore.game.player.Player;
import io.github.yuko1101.setprop.util.TriConsumer;

import javax.annotation.Nullable;
import java.util.List;

public class BooleanProp extends Prop<Boolean> {

    public BooleanProp(String name, List<String> aliases, Boolean defaultValue, @Nullable TriConsumer<Prop<Boolean>, Boolean, Player> onChange) {
        super(name, aliases, defaultValue, onChange);
    }

    @Nullable
    @Override
    public Boolean parse(String value, Player player) {
        if (value.equalsIgnoreCase("true")) {
            return this.set(true, player);
        } else if (value.equalsIgnoreCase("false")) {
            return this.set(false, player);
        }
        try {
            var i = Integer.parseInt(value);
            if (i == 0) {
                return this.set(false, player);
            } else if (i == 1) {
                return this.set(true, player);
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Prop<Boolean> clone() {
        return new BooleanProp(getName(), getAliases(), getDefaultValue(), onChange);
    }
}
