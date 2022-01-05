package me.fan87.devcmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.world.SBWorld;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CmdWorldInfo extends SBCommand {
    public CmdWorldInfo() {
        super("world-info", "Shows the world info (Type etc.)", "skyblock.admin", "/world-info");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            SBWorld world = skyBlock.getWorldsManager().getWorld(((Player) sender).getWorld().getName());
            sender.sendMessage(world==null?"Unknown":world.getWorldType().getName());
        }
        return true;
    }
}
