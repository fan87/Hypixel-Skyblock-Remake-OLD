package me.fan87.commonplugin.events.registerers;

import io.github.retrooper.packetevents.event.PacketEvent;
import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.impl.*;
import me.fan87.commonplugin.events.EventManager;

public class PacketRegisterer extends PacketListenerAbstract {

    @Override
    public void onPacketStatusReceive(PacketStatusReceiveEvent event) {
        EventManager.post(event);
    }

    @Override
    public void onPacketStatusSend(PacketStatusSendEvent event) {
        EventManager.post(event);
    }

    @Override
    public void onPacketHandshakeReceive(PacketHandshakeReceiveEvent event) {
        EventManager.post(event);
    }

    @Override
    public void onPacketLoginReceive(PacketLoginReceiveEvent event) {
        EventManager.post(event);
    }

    @Override
    public void onPacketLoginSend(PacketLoginSendEvent event) {
        EventManager.post(event);
    }

    @Override
    public void onPacketPlaySend(PacketPlaySendEvent event) {
        EventManager.post(event);
    }

    @Override
    public void onPostPacketPlayReceive(PostPacketPlayReceiveEvent event) {
        EventManager.post(event);
    }

    @Override
    public void onPostPacketPlaySend(PostPacketPlaySendEvent event) {
        EventManager.post(event);
    }

    @Override
    public void onPacketEventExternal(PacketEvent event) {
        EventManager.post(event);
    }

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {
        EventManager.post(event);
    }

}
