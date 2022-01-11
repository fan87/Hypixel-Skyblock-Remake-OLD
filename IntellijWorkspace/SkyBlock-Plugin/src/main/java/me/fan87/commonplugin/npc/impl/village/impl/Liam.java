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
        namespace = "INTRO_LIAM",
        name = "Liam",
        addonName = "default"
)
public class Liam extends NPCIntroVillager {
    public Liam(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList();
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList(
                "One day those houses in the Village will be rentable for Coins!",
                "Anyone will be able to rent them, players, co-ops, even Guilds!"
        );
    }

    // Todo: Finish rogue sword
}
