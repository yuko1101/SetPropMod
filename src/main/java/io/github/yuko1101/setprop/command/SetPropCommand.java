package io.github.yuko1101.setprop.command;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import io.github.yuko1101.setprop.extension.PlayerExtension;

@Command(
        label = "setprop",
        aliases = {"prop"},
        desc = "/setprop [prop] [value]. Set a property for a player",
        permission = "player.setprop",
        requireTarget = true
)
public class SetPropCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        if (args.size() < 2) {
            args.sendMessage("Usage: /setprop [prop] [value]");
            return;
        }
        var propName = args.get(0);
        var value = args.get(1);
        var player = args.getTarget();
        var playerMixin = (PlayerExtension) player;

        var prop = playerMixin.setPropMod$getProp(propName);
        if (prop == null) {
            args.sendMessage("Unknown property: " + propName);
            return;
        }

        var parsedValue = prop.parse(value);
        if (parsedValue == null) {
            args.sendMessage("Invalid value: " + value);
        } else {
            args.sendMessage("Set " + propName + " to " + parsedValue);
        }
    }
}
