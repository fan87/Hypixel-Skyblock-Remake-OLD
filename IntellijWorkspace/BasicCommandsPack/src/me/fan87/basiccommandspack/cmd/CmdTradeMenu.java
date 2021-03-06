package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.gui.impl.trading.GuiTradings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTradeMenu extends SBCommand {
    public CmdTradeMenu() {
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
