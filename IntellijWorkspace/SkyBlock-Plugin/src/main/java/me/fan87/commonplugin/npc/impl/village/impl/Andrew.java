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
        namespace = "INTRO_ANDREW",
        name = "Andrew",
        addonName = "default"
)
public class Andrew extends NPCIntroVillager {
    public Andrew(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList();
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList(
                String.format("This game is still under heavy development, don't forget to check the %sforums %soften for updates!", ChatColor.GREEN, ChatColor.RESET),
                String.format("If you'd like to discuss SkyBlock with other players then check out the SkyBlock section of the %sforums%s!", ChatColor.GREEN, ChatColor.RESET)
        );
    }
}
