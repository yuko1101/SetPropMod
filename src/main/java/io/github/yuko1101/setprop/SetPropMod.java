package io.github.yuko1101.setprop;

import emu.lunarcore.LunarCore;
import io.github.yuko1101.setprop.command.SetPropCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;

public class SetPropMod implements ModInitializer {

    public static final String MOD_NAME = "SetPropMod";

    public static final LogCategory LOG_CATEGORY = LogCategory.create(MOD_NAME);

    public static SetPropMod INSTANCE;

    @Override
    public void onInitialize() {
        Log.info(LOG_CATEGORY, "Initializing " + MOD_NAME);
        INSTANCE = this;
    }

    public void onLoadPlugins() {
        LunarCore.getCommandManager().registerCommand(new SetPropCommand());
    }
}
