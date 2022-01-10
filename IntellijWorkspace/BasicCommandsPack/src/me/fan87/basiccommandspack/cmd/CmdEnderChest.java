package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdEnderChest extends SBCommand {
    public CmdEnderChest() {
        super("enderchest", "open your own chest", "", "/enderchest", "ec");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (sender instanceof Player) {

            ((Player) sender).getPlayer().openInventory(((Player) sender).getEnderChest());
            ((Player) sender).playSound(((Player) sender).getLocation(), Sound.CHEST_OPEN, 0.5f, 0.69f);
            ((Player) sender).playSound(((Player) sender).getLocation(), Sound.ENDERMAN_TELEPORT, 0.5f, 0.69f);
            return true;

        }
        return false;

    }
}
