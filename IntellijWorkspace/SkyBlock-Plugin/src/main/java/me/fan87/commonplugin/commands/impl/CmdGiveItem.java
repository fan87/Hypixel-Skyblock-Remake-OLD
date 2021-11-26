package me.fan87.commonplugin.commands.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.players.SBPlayer;
import net.minecraft.server.v1_8_R3.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdGiveItem implements CommandExecutor {

    private SkyBlock skyBlock;

    public CmdGiveItem(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length != 1) return false;
            SBCustomItem item = SBItems.getItem(strings[0]);
            if (item != null) {
                SBItemStack itemStack = new SBItemStack(item);
                player.getInventory().addItem(itemStack.getItemStack());
            }
        }
        return false;
    }
}
