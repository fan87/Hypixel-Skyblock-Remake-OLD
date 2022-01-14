package me.fan87.commonplugin.npc;

import me.fan87.commonplugin.SkyBlock;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AbstractNPC.NPCRegistry(
        addonName = "default",
        namespace = "HOLOGRAM",
        name = "Hologram",
        skin = "",
        shown = false
)
public class Hologram extends AbstractNPC<EntityArmorStand> {

    private EntityArmorStand npcEntity;
    private Location location;
    private final List<String> text = new ArrayList<>();

    public Hologram(SkyBlock skyBlock, Location location, String... text) {
        super(skyBlock);
        this.text.addAll(Arrays.asList(text));
    }


    @Override
    public EntityArmorStand getNpcEntity() {
        return npcEntity;
    }

    @Override
    public Hologram create(World world) {
        npcEntity = new EntityArmorStand(((CraftWorld) world).getHandle());
        npcEntity.n(true);
        npcEntity.setGravity(true);
        npcEntity.setInvisible(true);
        skyBlock.getNpcManager().addNpc(this);
        if (displayToAll()) {
            for (Player player : world.getPlayers()) {
                display(player);
            }
        }
        return this;
    }

    @Override
    public Hologram display(Player... players) {
        if (npcEntity == null) throw new IllegalStateException("NPC is not created! Please use NPC.create() first!");
        for (Player player : players) {
            Bukkit.broadcastMessage("Displaying to: " + player.getName());
            displayLore(npcEntity, player);
        }
        updatePosition();
        return this;
    }

    @Override
    public Hologram updatePosition(Player... players) {
        if (npcEntity == null) throw new IllegalStateException("NPC is not created! Please use NPC.create() first!");
        for (Player viewer : players) {
            updateLorePosition(viewer, npcEntity);
        }
        return this;
    }

    @Override
    public Hologram hide(Player... players) {
        if (npcEntity == null) throw new IllegalStateException("NPC is not created! Please use NPC.create() first!");
        try {
            for (Player player : players) {
                Bukkit.broadcastMessage("Hidden from player: " + player.getName());
                if (viewers.contains(player)) {
                    viewers.remove(player);
                    hideLore(player);
                    onHide(player);
                }
            }
        } catch (Exception ignored) {}
        return this;
    }

    @Override
    public List<String> getLore(Player player) {
        return new ArrayList<>(text);
    }
}
