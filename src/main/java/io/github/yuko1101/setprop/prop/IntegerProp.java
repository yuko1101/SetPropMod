package io.github.yuko1101.setprop.prop;

import emu.lunarcore.game.player.Player;
import io.github.yuko1101.setprop.util.TriConsumer;

import javax.annotation.Nullable;
import java.util.List;

public class IntegerProp extends Prop<Integer> {

    public IntegerProp(String name, List<String> aliases, Integer defaultValue, @Nullable TriConsumer<Prop<Integer>, Integer, Player> onChange) {
        super(name, aliases, defaultValue, onChange);
    }

    @Nullable
    @Override
    public Integer parse(String value, Player player) {
        try {
            return this.set(Integer.parseInt(value), player);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Prop<Integer> clone() {
        return new IntegerProp(getName(),getAliases(), getDefaultValue(), onChange);
    }
}
