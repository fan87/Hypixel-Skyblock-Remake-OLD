package me.fan87.commonplugin.gui.impl.skill;

import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.ButtonHandler;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.skill.SBSkill;
import me.fan87.commonplugin.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import me.fan87.commonplugin.events.Subscribe;

public class GuiSkillTypesMenu extends Gui {

    private final SBPlayer player;

    public GuiSkillTypesMenu(SBPlayer player) {
        super("Your Skills", 6);
        this.player = player;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        set(5, 1, new GuiItem(
                new ItemStackBuilder(Material.DIAMOND_SWORD)
                        .setDisplayName(ChatColor.GREEN + "Your Skills")
                        .addLore(LoreUtils.splitLoreForLine(ChatColor.GRAY + "View your Skill progression and rewards."))
                        .build()
        ));
        SBSkill[] skills = player.getSkills().getSkills();
        for (int i = 0; i < skills.length; i++) {
            SBSkill skill = skills[i];
            ItemStackBuilder itemStackBuilder = new ItemStackBuilder(skill.getSkillType().getIconMaterial(), skill.getSkillType().getIconData())
                    .setDisplayName(ChatColor.GREEN + skill.getSkillType().getName() + " " + skill.getLevelDisplay(skill.getLevel()))
                    .addAllItemFlags()
                    .addLore(LoreUtils.splitLoreForLine(ChatColor.GRAY + skill.getSkillType().getObtainMethod()))
                    .addLore("");
            if (skill.getLevel() >= 60) {
                itemStackBuilder.addLore(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You have reached the max level for this Skill!");
            } else {
                double a = skill.getExtraExp() * 1f / skill.getRequiredExp(skill.getLevel() + 1);
                double d = Math.floor(a * 100 * 10) / 10f;
                itemStackBuilder.addLore(ChatColor.GRAY + "Progress to Level " + skill.getLevelDisplay(skill.getLevel() + 1) + ": " + (Double.toString(d).endsWith(".0")?(int) d:d) + "%");
                StringBuilder progressBar = new StringBuilder(ChatColor.GREEN.toString());
                for (int unused = 0; unused < Math.floor(a * 20); unused++) {
                    progressBar.append("-");
                }
                progressBar.append(ChatColor.WHITE);
                for (int j = 0; j < 20 - Math.floor(a * 20); j++) {
                    progressBar.append("-");
                }
                progressBar.append(" ").append(ChatColor.YELLOW).append(NumberUtils.formatNumber(skill.getExtraExp())).append(ChatColor.GOLD).append("/").append(ChatColor.YELLOW).append(NumberUtils.formatLargeNumber(skill.getRequiredExp(skill.getLevel() + 1), false));
                itemStackBuilder.addLore(progressBar.toString());
                itemStackBuilder.addLore("");
                itemStackBuilder.addLore(skill.getRewardLore(ChatColor.GRAY + "Level " + RomanUtils.toRoman(skill.getLevel()+1) + " Rewards:", skill.getLevel()+1, player));
            }
            itemStackBuilder.addLore("");
            itemStackBuilder.addLore(ChatColor.YELLOW + "Click to view!");
            set(2 + i, 3, new GuiItem(itemStackBuilder.build(), new ButtonHandler() {
                @Override
                public void handleClick(InventoryClickEvent event) {
                    new GuiSkillMenu(skill, player, 1).open(((Player) event.getWhoClicked()));
                }
            }));
        }
        renderGoBackItems(new GuiSkyBlockMenu(player), player.getPlayer());
    }

    @Subscribe()
    public void onTick(ServerTickEvent event) {

    }
}
