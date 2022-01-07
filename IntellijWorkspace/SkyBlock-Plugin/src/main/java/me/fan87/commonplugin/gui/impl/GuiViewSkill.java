package me.fan87.commonplugin.gui.impl;

import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.impl.RewardFortune;
import me.fan87.commonplugin.players.skill.SBSkill;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.LoreUtils;
import me.fan87.commonplugin.utils.NumberUtils;
import me.fan87.commonplugin.utils.RomanUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class GuiViewSkill extends Gui {

    private final SBSkill skill;
    private final SBPlayer player;
    private final int page;

    public GuiViewSkill(SBSkill skill, SBPlayer player, int page) {
        super(skill.getSkillType().getName() + " Skill", 6);
        this.skill = skill;
        this.player = player;
        this.page = page;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        renderGoBackItems(new GuiSkillsMenu(player), player.getPlayer());

        int currentX = 1;
        int currentY = 0;
        int currentLevel = 0; // Didn't ask.
        for (int i = 0; i < 4; i++) {
            currentY++;
            setLevelItem(currentX, currentY, currentLevel++);
        }
        for (int i = 0; i < 2; i++) {
            currentX++;
            setLevelItem(currentX, currentY, currentLevel++);
        }
        for (int i = 0; i < 3; i++) {
            currentY--;
            setLevelItem(currentX, currentY, currentLevel++);
        }
        for (int i = 0; i < 2; i++) {
            currentX++;
            setLevelItem(currentX, currentY, currentLevel++);
        }
        for (int i = 0; i < 3; i++) {
            currentY++;
            setLevelItem(currentX, currentY, currentLevel++);
        }
        for (int i = 0; i < 2; i++) {
            currentX++;
            setLevelItem(currentX, currentY, currentLevel++);
        }
        for (int i = 0; i < 3; i++) {
            currentY--;
            setLevelItem(currentX, currentY, currentLevel++);
        }
        for (int i = 0; i < 2; i++) {
            currentX++;
            setLevelItem(currentX, currentY, currentLevel++);
        }
        for (int i = 0; i < 5; i++) {
            currentY++;
            setLevelItem(currentX, currentY, currentLevel++);
        }
    }

    public void setLevelItem(int x, int y, int level) {
        ItemStackBuilder builder;
        if (level == 0) {
            builder = new ItemStackBuilder(skill.getSkillType().getIconMaterial(), skill.getSkillType().getIconData());
            builder.addAllItemFlags();
            builder.setDisplayName(ChatColor.GREEN + skill.getSkillType().getName() + " Skill");
            builder.addLore(LoreUtils.splitLoreForLine(ChatColor.GRAY + skill.getSkillType().getObtainMethod()));
            builder.addLore("");
            double a = skill.getExtraExp() * 1f / skill.getRequiredExp(skill.getLevel() + 1);
            double d = Math.floor(a * 100 * 10) / 10f;
            builder.addLore(ChatColor.GRAY + "Progress to Level " + skill.getLevelDisplay(skill.getLevel() + 1) + ": " + (Double.toString(d).endsWith(".0")?(int) d:d) + "%");
            StringBuilder progressBar = new StringBuilder(ChatColor.GREEN.toString());
            for (int unused = 0; unused < Math.floor(a * 20); unused++) {
                progressBar.append("-");
            }
            progressBar.append(ChatColor.WHITE);
            for (int j = 0; j < 20 - Math.floor(a * 20); j++) {
                progressBar.append("-");
            }
            progressBar.append(" ").append(ChatColor.YELLOW).append(NumberUtils.formatNumber(skill.getExtraExp())).append(ChatColor.GOLD).append("/").append(ChatColor.YELLOW).append(NumberUtils.formatLargeNumber(skill.getRequiredExp(skill.getLevel() + 1), false));
            builder.addLore(progressBar.toString());
            builder.addLore("");
            if (skill.getLevel() > 0) {
                builder.addLore(new RewardFortune(skill, skill.getLevel()) {
                    @Override
                    public SBPlayer getPlayer() {
                        return player;
                    }
                }.toLore());
                builder.addLore("");
            }
            builder.addLore(LoreUtils.splitLoreForLine(ChatColor.DARK_GRAY + skill.getSkillType().getPerks()));
            set(x, y, new GuiItem(builder.build()));
            return;
        } else {
            if (skill.getLevel() >= level) {
                if (level % 5 == 0) {
                    builder = new ItemStackBuilder(skill.getSkillType().getAlternativeIcon());
                } else {
                    builder = new ItemStackBuilder(Material.STAINED_GLASS_PANE, 5);
                }
                builder.setDisplayName(ChatColor.GREEN + skill.getSkillType().getName() + " " + skill.getLevelDisplay(level));
            } else {
                if (skill.getLevel() + 1 == level) {
                    builder = new ItemStackBuilder(Material.STAINED_GLASS_PANE, 4);
                    builder.setDisplayName(ChatColor.YELLOW + skill.getSkillType().getName() + " " + skill.getLevelDisplay(level));
                } else {
                    builder = new ItemStackBuilder(Material.STAINED_GLASS_PANE, 14);
                    builder.setDisplayName(ChatColor.RED + skill.getSkillType().getName() + " " + skill.getLevelDisplay(level));
                }
            }
        }
        builder.addLore(LoreUtils.splitLoreForLine(ChatColor.GRAY + skill.getSkillType().getObtainMethod()))
                .addLore("");

        if (skill.getLevel() + 1 == level) {
            double a = skill.getExtraExp() * 1f / skill.getRequiredExp(skill.getLevel() + 1);
            double d = Math.floor(a * 100 * 10) / 10f;
            builder.addLore(ChatColor.GRAY + "Progress to Level " + skill.getLevelDisplay(skill.getLevel() + 1) + ": " + (Double.toString(d).endsWith(".0")?(int) d:d) + "%");
            StringBuilder progressBar = new StringBuilder(ChatColor.GREEN.toString());
            for (int unused = 0; unused < Math.floor(a * 20); unused++) {
                progressBar.append("-");
            }
            progressBar.append(ChatColor.WHITE);
            for (int j = 0; j < 20 - Math.floor(a * 20); j++) {
                progressBar.append("-");
            }
            progressBar.append(" ").append(ChatColor.YELLOW).append(NumberUtils.formatNumber(skill.getExtraExp())).append(ChatColor.GOLD).append("/").append(ChatColor.YELLOW).append(NumberUtils.formatLargeNumber(skill.getRequiredExp(skill.getLevel() + 1), false));
            builder.addLore(progressBar.toString());
            builder.addLore("");
        }
        builder.addLore(skill.getRewardLore(ChatColor.GRAY + "Level " + RomanUtils.toRoman(skill.getLevel()+1) + " Rewards:", level, player));
        builder.addAllItemFlags();
        builder.setAmount(level);
        set(x, y, new GuiItem(builder.build()));
    }
}
