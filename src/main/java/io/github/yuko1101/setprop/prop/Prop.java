package io.github.yuko1101.setprop.prop;

import emu.lunarcore.game.player.Player;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public abstract class Prop<T> {
    private final String name;
    private final T defaultValue;
    @Nullable
    public Player player;

    /**
     * This will be called right before the value is changed.
     * The first argument is the prop that is being changed.
     * The second argument is the new value.
     */
    protected final BiConsumer<Prop<T>, T> onChange;

    private T value;

    public Prop(String name, T defaultValue, @Nullable Player player, BiConsumer<Prop<T>, T> onChange) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.player = player;
        this.value = defaultValue;
        this.onChange = onChange;
    }

    public String getName() {
        return name;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public T set(T value) {
        if (this.value != value) onChange.accept(this, value);
        this.value = value;
        return value;
    }

    public T get() {
        return value;
    }

    public void reset() {
        value = defaultValue;
    }

    @Nullable
    abstract public T parse(String value);

    abstract public Prop<T> copyWith(@Nullable Player player);
}
