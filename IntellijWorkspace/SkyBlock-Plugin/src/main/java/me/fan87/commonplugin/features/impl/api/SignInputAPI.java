package me.fan87.commonplugin.features.impl.api;

import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import lombok.AllArgsConstructor;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.util.Vector;

import java.util.*;

public class SignInputAPI extends SBFeature {

    private static Map<Vector, SignEditor> signCallBackMap = new HashMap<>();

    public SignInputAPI() {
        super("Sign Input", "Basically sign input API", true);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    public static void showSignEditor(SBPlayer player, SignCallBack signCallBack, String... defaultContent) {
        Random random = new Random();
        Vector key = new Vector(random.nextInt(Integer.MAX_VALUE), random.nextInt(Integer.MAX_VALUE), random.nextInt(Integer.MAX_VALUE));
        signCallBackMap.put(key, new SignEditor(signCallBack, player, System.currentTimeMillis() + 30000));
        BlockPosition blockPosition = new BlockPosition(key.getX(), key.getY(), key.getZ());
        PacketPlayOutOpenSignEditor editor = new PacketPlayOutOpenSignEditor(blockPosition);
        List<IChatBaseComponent> components = new ArrayList<>();
        for (String text : defaultContent) {
            components.add(new ChatComponentText(text));
        }
        PacketPlayOutUpdateSign update = new PacketPlayOutUpdateSign(((CraftWorld) player.getPlayer().getWorld()).getHandle(), blockPosition, components.toArray(new IChatBaseComponent[0]));
        player.sendPacket(editor);
        player.sendPacket(update);
    }

    private static SignInputAPI getInstance() {
        return SkyBlock.getPlugin(SkyBlock.class).getFeaturesManager().getFeature(SignInputAPI.class);
    }

    @Subscribe
    public void onPacket(PacketPlayReceiveEvent event) {
        Object rawNMSPacket = event.getNMSPacket().getRawNMSPacket();
        if (rawNMSPacket instanceof PacketPlayInUpdateSign) {
            PacketPlayInUpdateSign packet = (PacketPlayInUpdateSign) rawNMSPacket;
            Vector vec = new Vector(packet.a().getX(), packet.a().getY(), packet.a().getZ());
            SignEditor signEditor = signCallBackMap.get(vec);
            if (event.getPlayer().equals(signEditor.player.getPlayer())) {
                signCallBackMap.remove(vec);
            }
        }
    }

    @Subscribe
    public void onTick(ServerTickEvent event) {

    }

    @AllArgsConstructor
    private static class SignEditor {
        SignCallBack signCallBack;
        SBPlayer player;
        long timeOutTime;
    }

    public interface SignCallBack {
        void onFinishedTyping(String[] content);
    }

}
