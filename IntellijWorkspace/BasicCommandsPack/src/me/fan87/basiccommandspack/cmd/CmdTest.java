package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.SBWorld;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class CmdTest extends SBCommand {

    public CmdTest() {
        super("a", "Just a test command", "skyblock.debug", "/testa");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        SBPlayer player = skyBlock.getPlayersManager().getPlayer((Player) sender);
        SBWorld world = player.getWorld();


        Zombie spawn = world.getWorld().spawn(player.getPlayer().getLocation(), Zombie.class);

        return true;
    }
}
