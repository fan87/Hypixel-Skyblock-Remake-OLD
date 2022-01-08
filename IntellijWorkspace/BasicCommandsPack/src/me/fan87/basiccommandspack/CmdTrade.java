package me.fan87.basiccommandspack;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.impl.trading.GuiTradings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTrade extends SBCommand {
    public CmdTrade() {
        super("trademenu", "Open the trade menu", "", "/trademenu");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (sender instanceof Player) {

            new GuiTradings(skyBlock.getPlayersManager().getPlayer(((Player) sender)), 1).open(((Player) sender));
            return true;

        }

        return false;
    }
}
