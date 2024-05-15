package io.github.yuko1101.setprop.prop;

import emu.lunarcore.GameConstants;
import emu.lunarcore.game.player.Player;

import javax.annotation.Nullable;
import java.util.List;

public enum DefinedProp {
    UNLIMITED_MP(List.of("ump"), new BooleanProp("unlimited_mp", false, null, (prop, newValue) -> {
        if (newValue) {
            assert prop.player != null;
            prop.player.getCurrentLineup().addMp(GameConstants.MAX_MP);
        }
    })),;

    public final List<String> aliases;
    private final Prop<?> prop;

    DefinedProp(List<String> aliases, Prop<?> prop) {
        this.aliases = aliases;
        this.prop = prop;
    }

    public Prop<?> createProp(@Nullable Player player) {
        return prop.copyWith(player);
    }

}
