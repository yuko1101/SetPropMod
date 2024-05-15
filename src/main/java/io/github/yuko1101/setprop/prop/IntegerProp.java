package io.github.yuko1101.setprop.prop;

import emu.lunarcore.game.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class IntegerProp extends Prop<Integer> {

    public IntegerProp(String name, Integer defaultValue, @Nullable Player player, BiConsumer<Prop<Integer>, Integer> onChange) {
        super(name, defaultValue, player, onChange);
    }

    @Nullable
    @Override
    public Integer parse(String value) {
        try {
            return this.set(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Prop<Integer> copyWith(Player player) {
        var prop = new IntegerProp(getName(), getDefaultValue(), player, onChange);
        prop.set(get());
        return prop;
    }
}
