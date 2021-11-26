package me.fan87.commonplugin.players.stats.impl;

import com.mysql.jdbc.NotImplemented;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatHealth extends SBStat {


    @Override
    public String getValueDisplay(double value) {
        return (int) Math.round(value) + "";
    }

    @Override
    public double getDefaultValue() {
        return 100;
    }

    @Override
    public String getNamespace() {
        return "HEALTH";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Health is your total maximum health. Your natural regeneration gives " + ChatColor.GREEN + getValue()*0.015 + " HP " + ChatColor.RESET + "every " + ChatColor.GREEN + "2s.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Health by leveling your Farming and Fishing Skills and contributing to the " + ChatColor.LIGHT_PURPLE + "Fairy " + ChatColor.RESET + "in the " + ChatColor.DARK_GREEN + "Wilderness" + ChatColor.RESET + ".";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Health by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Health";
    }

    @Override
    public String getIcon() {
        return "❤";
    }

    @Override
    public String getColor() {
        return ChatColor.RED + "";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.GOLDEN_APPLE);
    }

    @Override
    public void onTick(SBPlayer player) {
        player.getPlayer().setMaxHealth(getValue()/5f);
    }
}
