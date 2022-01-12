package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.enchantment.SBEnchantments;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CmdEnchant extends SBCommand {
    public CmdEnchant() {
        super("enchant", "Enchant the item on your hand", "skyblock.enchant", "/enchant <enchantment> [level]", "/ench");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only player can execute this command!");
            return true;
        }
        if (args.length == 0) {
            return false;
        }
        SBEnchantment enchantment = SBEnchantments.getEnchantment(SBNamespace.fromString(args[0]));
        int level = 1;
        try {
            if (args.length >= 2) level = Integer.parseInt(args[1]);
        } catch (Exception ignored) {return false;}
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItem(player.getInventory().getHeldItemSlot());
        if (item == null || item.getType() == Material.AIR) {
            sender.sendMessage(ChatColor.RED + "You are not holding an item!");
            return true;
        }
        SBItemStack itemStack = new SBItemStack(item);
        itemStack.addEnchantment(enchantment, level);
        return true;
    }

    @Override
    protected List<String> onTabComplete(CommandSender sender, String alias, String[] args) {
        List<String> out = new ArrayList<>();
        for (SBEnchantment registeredEnchantment : SBEnchantments.getRegisteredEnchantments()) {
            if (registeredEnchantment.getNamespace().toString().startsWith(args[0])) {
                out.add(registeredEnchantment.getNamespace().toString());
            }
        }
        return out;
    }
}
