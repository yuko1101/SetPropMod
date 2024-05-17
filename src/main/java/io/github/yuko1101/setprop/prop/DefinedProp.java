package io.github.yuko1101.setprop.prop;

import emu.lunarcore.GameConstants;
import java.util.List;

public enum DefinedProp {
    UNLIMITED_MP(new BooleanProp("unlimited_mp", List.of("ump"), false, (prop, newValue, player) -> {
        if (newValue) {
            player.getCurrentLineup().addMp(GameConstants.MAX_MP);
        }
    }));


    private final Prop<?> prop;

    DefinedProp(Prop<?> prop) {
        this.prop = prop;
    }

    public Prop<?> createProp() {
        return prop.clone();
    }

}
