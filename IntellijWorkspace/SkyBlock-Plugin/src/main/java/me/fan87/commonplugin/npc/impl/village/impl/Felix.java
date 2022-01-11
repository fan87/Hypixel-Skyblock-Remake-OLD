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
        namespace = "INTRO_FELIX",
        name = "Felix",
        addonName = "default"
)
public class Felix extends NPCIntroVillager {
    public Felix(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList(
                String.format("You can access your %sEnder Chest %sin your %sSkyBlock Menu%s.", ChatColor.GREEN, ChatColor.RESET, ChatColor.GREEN, ChatColor.RESET),
                "Store items in this chest and access them at any time!"
        );
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList();
    }
}
