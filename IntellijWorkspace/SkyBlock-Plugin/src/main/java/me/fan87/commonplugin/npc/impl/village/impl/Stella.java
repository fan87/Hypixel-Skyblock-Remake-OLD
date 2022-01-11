package me.fan87.commonplugin.npc.impl.village.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.npc.AbstractNPC;
import me.fan87.commonplugin.npc.impl.village.NPCIntroVillager;
import me.fan87.commonplugin.world.WorldsManager;

import java.util.Arrays;
import java.util.List;

@AbstractNPC.NPCRegistry(
        world = WorldsManager.WorldType.SKYBLOCK_HUB,
        skin = "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQwMWQxZWEyNjMwYmEwNjI3N2RhNDNjNzY3YWFiNDE0MWFjNzYxM2FmYjRhZmNlZDE5ZDUzMzJhZmU5ZmFkMyJ9fX0=",
        namespace = "INTRO_STELLA",
        name = "Stella",
        addonName = "default"
)
public class Stella extends NPCIntroVillager {
    public Stella(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList(
                "At any time you can create a Co-op with your friends!",
                "Simply go in your SkyBlock Menu where you can find the Profile Menu.",
                "This is where you can create, delete or switch SkyBlock Profiles.",
                "Enter §b/coop §rfollowed by the name of all the friends you want to invite!",
                "All your friends have to be online to accept!"
        );
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList();
    }

    // Todo: Finish rogue sword
}
