package me.fan87.commonplugin.npc.impl.village;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.npc.NPCVillager;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.*;

public abstract class NPCIntroVillager extends NPCVillager {
    public NPCIntroVillager(SkyBlock skyBlock) {
        super(skyBlock);
    }

    protected abstract List<String> getDialogues();
    protected abstract List<String> getSecondTalkDialogue();

    private List<Player> talkingTo = new ArrayList<>();

    @Override
    protected void onInteract(SBPlayer player) {
        Random random = new Random();
        if (talkingTo.contains(player.getPlayer())) return;
        if ((!player.hasTalkedTo(this) && getDialogues().size() > 0) || getSecondTalkDialogue().size() < 1) {
            talkingTo.add(player.getPlayer());
            for (int i = 0; i < getDialogues().size(); i++) {
                int finalI = i;
                Bukkit.getScheduler().runTaskLater(skyBlock, () -> {
                    player.getPlayer().sendMessage(ChatColor.YELLOW + String.format("[NPC] %s%s: %s", getNPCRegistry(getClass()).name(), ChatColor.RESET, getDialogues().get(finalI)));
                    if (random.nextBoolean()) {
                        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_YES, 1f, 1f);
                    } else {
                        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
                    }
                    if (finalI == getDialogues().size() - 1) {
                        talkingTo.remove(player.getPlayer());
                        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.ORB_PICKUP, 1f, 1.5f);
                        if (!player.hasTalkedTo(this)) {
                            onFirstTalkFinish(player);
                        }
                        player.setTalkedTo(this, true);
                    }
                }, 30L *i);
            }
        } else {
            player.setTalkedTo(this, true);
            player.getPlayer().sendMessage(ChatColor.YELLOW + String.format("[NPC] %s%s: %s", getNPCRegistry(getClass()).name(), ChatColor.RESET, getSecondTalkDialogue().get(random.nextInt(getSecondTalkDialogue().size()-1))));
            if (random.nextBoolean()) {
                player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_YES, 1f, 1f);
            } else {
                player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
            }
        }

    }

    @Override
    protected void onTick() {
        for (Player viewer : getViewers()) {
            SBPlayer player = skyBlock.getPlayersManager().getPlayer(viewer);
            if (viewer.getTicksLived() % 8 == 0 && !player.hasTalkedTo(this)) {
                ParticleBuilder builder = new ParticleBuilder(ParticleEffect.VILLAGER_HAPPY, asCraftCopy().getLocation().add(0, getNpcEntity().getHeadHeight() + 1.25, 0));
                builder.setAmount(3);
                builder.display(player.getPlayer());
            }
        }
        Player player = getViewers().stream().sorted(Comparator.comparingDouble(o -> MathUtils.distanceBetween(o.getLocation(), asCraftCopy().getLocation()))).findFirst().orElse(null);
        if (player != null) {
            tryFace(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
        }
    }

    @Override
    protected void onHide(Player player) {
        talkingTo.remove(player);
    }

    @Override
    public List<String> getLore(Player player) {
        return Arrays.asList(getNPCRegistry(this.getClass()).name(), ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK");
    }

    public void onFirstTalkFinish(SBPlayer player) {}

}
