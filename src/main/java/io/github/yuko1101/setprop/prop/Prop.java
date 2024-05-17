package io.github.yuko1101.setprop.prop;

import dev.morphia.annotations.Entity;
import emu.lunarcore.game.player.Player;
import io.github.yuko1101.setprop.util.TriConsumer;

import javax.annotation.Nullable;
import java.util.List;

@Entity(useDiscriminator = false)
public abstract class Prop<T> {
    private final String name;
    private final List<String> aliases;
    private final T defaultValue;

    /**
     * This will be called right before the value is changed.
     * The first argument is the prop that is being changed.
     * The second argument is the new value.
     */
    @Nullable
    protected TriConsumer<Prop<T>, T, Player> onChange;

    private T value;

    public Prop(String name, List<String> aliases, T defaultValue, @Nullable TriConsumer<Prop<T>, T, Player> onChange) {
        this.name = name;
        this.aliases = aliases;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.onChange = onChange;
    }

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public T set(T value, Player player) {
        if (this.value != value && onChange != null) onChange.accept(this, value, player);
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
    abstract public T parse(String value, Player player);

    /**
     * This method does not copy the value from the original.
     */
    abstract public Prop<T> clone();
}
