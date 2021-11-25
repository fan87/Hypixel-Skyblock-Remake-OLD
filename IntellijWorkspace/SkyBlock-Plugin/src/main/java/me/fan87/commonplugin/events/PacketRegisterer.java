package me.fan87.commonplugin.events;

import io.github.retrooper.packetevents.event.PacketEvent;
import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.impl.*;

public class PacketRegisterer extends PacketListenerAbstract {

    @Override
    public void onPacketStatusReceive(PacketStatusReceiveEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

    @Override
    public void onPacketStatusSend(PacketStatusSendEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

    @Override
    public void onPacketHandshakeReceive(PacketHandshakeReceiveEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

    @Override
    public void onPacketLoginReceive(PacketLoginReceiveEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

    @Override
    public void onPacketLoginSend(PacketLoginSendEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

    @Override
    public void onPacketPlaySend(PacketPlaySendEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

    @Override
    public void onPostPacketPlayReceive(PostPacketPlayReceiveEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

    @Override
    public void onPostPacketPlaySend(PostPacketPlaySendEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

    @Override
    public void onPacketEventExternal(PacketEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {
        EventManager.EVENT_BUS.post(event);
    }

}
