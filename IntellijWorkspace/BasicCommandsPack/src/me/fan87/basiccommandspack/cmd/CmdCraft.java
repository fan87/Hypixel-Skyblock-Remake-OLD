package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.gui.impl.GuiCraftingTable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCraft extends SBCommand {
    public CmdCraft() {
        super("craft", "Opens the crafting Gui", "", "/craft", "craftingtable", "craftingmenu");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {

            new GuiCraftingTable(skyBlock, skyBlock.getPlayersManager().getPlayer((Player) sender)).open(((Player) sender));
        }
        return true;
    }
}
