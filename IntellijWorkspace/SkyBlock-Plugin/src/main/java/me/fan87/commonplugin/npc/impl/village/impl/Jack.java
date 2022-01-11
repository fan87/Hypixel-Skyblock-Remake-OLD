package me.fan87.commonplugin.npc.impl.village.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.npc.AbstractNPC;
import me.fan87.commonplugin.npc.impl.village.NPCIntroVillager;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

@AbstractNPC.NPCRegistry(
        world = WorldsManager.WorldType.SKYBLOCK_HUB,
        skin = "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQwMWQxZWEyNjMwYmEwNjI3N2RhNDNjNzY3YWFiNDE0MWFjNzYxM2FmYjRhZmNlZDE5ZDUzMzJhZmU5ZmFkMyJ9fX0=",
        namespace = "INTRO_JACK",
        name = "Jack",
        addonName = "default"
)
public class Jack extends NPCIntroVillager {
    public Jack(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList(
                String.format("I will teach you the %sPromising Axe %sRecipe to get you started!", ChatColor.GREEN, ChatColor.RESET),
                String.format("All SkyBlock recipes can be found by opening the %sRecipe Book %sin your %sSkyBlock Menu%s.", ChatColor.GREEN, ChatColor.RESET, ChatColor.GREEN, ChatColor.RESET)
        );
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList(
                String.format("Did you know that you have a %sCrafting Table %sin your %sSkyBlock Menu%s?", ChatColor.YELLOW, ChatColor.RESET, ChatColor.GREEN, ChatColor.RESET)
        );
    }
}
