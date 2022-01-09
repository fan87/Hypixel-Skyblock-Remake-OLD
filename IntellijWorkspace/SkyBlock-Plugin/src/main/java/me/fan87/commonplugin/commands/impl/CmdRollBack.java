package me.fan87.commonplugin.commands.impl;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdRollBack extends SBCommand {
    public CmdRollBack() {
        super("rollback", "Rollback the world to the last save", "skyblock.admin", "/rollback");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            SBWorld world = skyBlock.getWorldsManager().getWorld(((Player) sender).getWorld().getName());
            if (world.getWorldType() == WorldsManager.WorldType.NONE) {
                sender.sendMessage(ChatColor.RED + "World is not a part of SkyBlock!");
                return true;            }
            List<SBPlayer> players = new ArrayList<>();
            for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {
                loadedPlayer.getPlayer().kickPlayer(ChatColor.RED + "Server restarting...");
            }
            Bukkit.getScheduler().runTaskLater(skyBlock, new Runnable() {
                @Override
                public void run() {
                    rollback(players, world);
                }
            }, 10);
            return true;
        }
        return false;
    }

    public void rollback(List<SBPlayer> players, SBWorld world) {
        World world1 = Bukkit.getServer().getWorld(world.getWorldName());
        WorldServer handle = ((CraftWorld) world1).getHandle();
        handle.players.clear();
        if(Bukkit.getServer().unloadWorld(world1, false)){
            Bukkit.getLogger().info("Successfully unloaded " + world.getWorldName());
            Bukkit.getServer().createWorld(new WorldCreator(world.getWorldName()));
            for (SBPlayer player : players) {
                Location spawnLocation = Bukkit.getWorld(world.getWorldName()).getSpawnLocation();
                spawnLocation.setYaw(180);
                spawnLocation.setPitch(0);
                player.getPlayer().teleport(spawnLocation);
            }
        }else{
            Bukkit.getLogger().severe("COULD NOT UNLOAD " + world.getWorldName());
        }
    }
}
