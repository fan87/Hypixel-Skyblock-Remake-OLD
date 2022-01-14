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
        Vector key = player.getPlayer().getLocation().toVector();
        key.setX(key.getBlockX()).setY(0).setZ(key.getBlockZ());
        signCallBackMap.put(key, new SignEditor(signCallBack, player, System.currentTimeMillis()));
        BlockPosition blockPosition = new BlockPosition(key.getBlockX(), key.getBlockY(), key.getZ());
        PacketPlayOutOpenSignEditor editor = new PacketPlayOutOpenSignEditor(blockPosition);
        List<IChatBaseComponent> components = new ArrayList<>();
        for (String text : defaultContent) {
            components.add(new ChatComponentText(text));
        }
        PacketPlayOutUpdateSign update = new PacketPlayOutUpdateSign(((CraftWorld) player.getPlayer().getWorld()).getHandle(), blockPosition, components.toArray(new IChatBaseComponent[0]));
        PacketPlayOutBlockChange change = new PacketPlayOutBlockChange(((CraftWorld) player.getCraftPlayer().getWorld()).getHandle(), blockPosition);
        change.block = Blocks.STANDING_SIGN.getBlockData();
        player.sendPacket(change);
        player.sendPacket(update);
        player.sendPacket(editor);

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
            if (signEditor != null && event.getPlayer().equals(signEditor.player.getPlayer())) {
                SBPlayer player = signEditor.player;
                signCallBackMap.remove(vec);
                IChatBaseComponent[] b = packet.b();
                List<String> list = new ArrayList<>();
                for (IChatBaseComponent iChatBaseComponent : b) {
                    list.add(iChatBaseComponent.getText());
                }
                signEditor.signCallBack.onFinishedTyping(list.toArray(new String[0]));
                PacketPlayOutBlockChange changeBack = new PacketPlayOutBlockChange(((CraftWorld) player.getCraftPlayer().getWorld()).getHandle(), new BlockPosition(vec.getBlockX(), vec.getBlockY(), vec.getZ()));
                player.sendPacket(changeBack);
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
