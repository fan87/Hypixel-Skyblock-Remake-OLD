package me.fan87.commonplugin.commands.impl;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.fan87.commonplugin.commands.SBCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CmdArmorStandPlaceHolder extends SBCommand {
    public CmdArmorStandPlaceHolder() {
        super("armorstandplaceholder", "Summon an armorstand placeholder", "skyblock.armorstanceplaceholder", "/armorstandplaceholder <Name> [Data...]", "asph");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (args.length < 1) return false;
        if (sender instanceof Player) {
            Player player = (Player) sender;
            List<String> customData = new ArrayList<>();
            ArmorStand spawn = player.getLocation().getWorld().spawn(player.getLocation(), ArmorStand.class);
            spawn.setGravity(false);
            spawn.setCustomNameVisible(true);
            for (int i = 0; i < args.length; i++) {
                if (i != 0)
                    customData.add(args[i]);
            }
            spawn.setCustomName(ChatColor.GREEN + args[0] + " -> " + String.join(" ", customData));
            ItemStack itemStack = new ItemStack(Material.INK_SACK);
            NBTItem nbt = new NBTItem(itemStack, true);
            nbt.setString("SBArmorPlaceHolderKey", args[0]);
            nbt.getStringList("SBArmorPlaceHolderValues").addAll(customData);
            spawn.setItemInHand(itemStack);
        } else {
            sender.sendMessage(ChatColor.RED + "Only player can execute this command!");
        }
        return true;
    }
}
