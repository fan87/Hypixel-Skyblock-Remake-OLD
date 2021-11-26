package me.fan87.commonplugin.events;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.registerers.TickEventRegisterer;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.greenrobot.eventbus.EventBus;
import org.reflections.Reflections;

public class EventManager {

    private final SkyBlock instance;
    @Getter
    private TickEventRegisterer tickEventRegisterer;

    public final static EventBus EVENT_BUS = EventBus.builder()
            .sendNoSubscriberEvent(false)
            .throwSubscriberException(false)
            .sendSubscriberExceptionEvent(true)
            .logNoSubscriberMessages(false)
            .build();

    public EventManager(SkyBlock skyBlock) {
        this.instance = skyBlock;
        Reflections reflections = new Reflections("org.bukkit.event");
        for (Class<? extends Event> aClass : reflections.getSubTypesOf(Event.class)) {
            try {
                aClass.getDeclaredMethod("getHandlerList");
            } catch (Exception e) {
                continue;
            }
            this.instance.getServer().getPluginManager().registerEvent(aClass, new Listener() {
            }, EventPriority.NORMAL, new EventExecutor() {
                @Override
                public void execute(Listener listener, Event event) throws EventException {
                    EVENT_BUS.post(event);
                }
            }, skyBlock);
        }

        this.tickEventRegisterer = new TickEventRegisterer(skyBlock);
    }

}
