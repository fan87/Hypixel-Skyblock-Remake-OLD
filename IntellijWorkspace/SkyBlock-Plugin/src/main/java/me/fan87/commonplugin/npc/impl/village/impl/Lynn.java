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
        namespace = "INTRO_LYNN",
        name = "Lynn",
        addonName = "default"
)
public class Lynn extends NPCIntroVillager {
    public Lynn(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList(
                "If you ever get lost during a quest, open your Quest Log in your SkyBlock Menu!"
        );
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList();
    }

    // Todo: Finish rogue sword
}
