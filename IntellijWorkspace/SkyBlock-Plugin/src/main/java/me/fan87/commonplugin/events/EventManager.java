package me.fan87.commonplugin.events;

import io.github.retrooper.packetevents.PacketEvents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.registerers.PacketRegisterer;
import me.fan87.commonplugin.events.registerers.TickEventRegisterer;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.EventExecutor;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class EventManager {

    @Getter
    private final SkyBlock skyBlock;

    @Getter
    private TickEventRegisterer tickEventRegisterer;


    public EventManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        Reflections reflections = new Reflections("org.bukkit.event");
        for (Class<? extends Event> eventClazz : reflections.getSubTypesOf(Event.class)) {
            try {
                eventClazz.getDeclaredMethod("getHandlerList");
            } catch (Exception e) {
                continue;
            }
            this.skyBlock.getServer().getPluginManager().registerEvent(eventClazz, new Listener() {
            }, EventPriority.NORMAL, new EventExecutor() {
                @Override
                public void execute(Listener listener, Event event) throws EventException {
                    post(event);
                }
            }, skyBlock);
        }
        register(this);
        this.tickEventRegisterer = new TickEventRegisterer(skyBlock);
        PacketEvents.get().registerListener(new PacketRegisterer());
    }

    private static final Map<Class<?>, List<EventHandler>> handlers = new HashMap<>();

    // Fields below are for performance indicating
    public static final Map<String, List<Long>> pp = new HashMap<>();
    public static final Map<String, Long> postedTimes = new HashMap<>();
    public static final Map<String, List<Long>> eventProcessTime = new HashMap<>();

    public static void unregister(Object subscriber) {
        for (Class<?> aClass : handlers.keySet()) {
            handlers.get(aClass).removeIf(eventHandler -> eventHandler.object == subscriber);
        }
    }

    public static boolean isRegistered(Object subscriber) {
        for (Class<?> aClass : handlers.keySet()) {
            for (EventHandler eventHandler : handlers.get(aClass)) {
                if (eventHandler.object == subscriber) return true;
            }
        }
        return false;
    }

    public static void register(Object subscriber) {
        Class<?> clazz = subscriber.getClass();
        while (clazz != null) {
            for (Method declaredMethod : clazz.getDeclaredMethods()) {
                if (Modifier.isStatic(declaredMethod.getModifiers())) continue;
                if (declaredMethod.getAnnotationsByType(Subscribe.class).length != 1) continue;
                if (declaredMethod.getParameterTypes().length != 1) continue;
                Class<?> eventType = declaredMethod.getParameterTypes()[0];
                List<EventHandler> list = handlers.getOrDefault(eventType, new ArrayList<>());
                list.add(new EventHandler(subscriber, declaredMethod));
                handlers.put(eventType, list);
            }
            clazz = clazz.getSuperclass();
        }
    }


    @SneakyThrows
    public static Object post(Object event) {
        postedTimes.put(event.getClass().getName(), postedTimes.getOrDefault(event.getClass().getName(), 0L) + 1);
        long before = System.currentTimeMillis();
        List<EventHandler> methods = new ArrayList<>();
        for (Class<?> aClass : new ArrayList<>(handlers.keySet())) {
            if (aClass.isAssignableFrom(event.getClass())) {
                methods.addAll(new ArrayList<>(handlers.get(aClass)));
            }
        }
        methods.sort(Comparator.comparingInt(handler -> -1 * handler.method.getAnnotationsByType(Subscribe.class)[0].priority()));
        for (EventHandler method : methods) {
            long start = System.currentTimeMillis();
            method.invoke(event);
            if (event instanceof PlayerJoinEvent) continue;
            long took = System.currentTimeMillis() - start;
            List<Long> longs = pp.get(method.toString());
            if (longs == null) longs = new ArrayList<>();
            longs.add(took);
            pp.put(method.toString(), longs);
        }
        ArrayList<Long> defaultValue = new ArrayList<>();
        List<Long> orDefault = eventProcessTime.getOrDefault(event.getClass().getName(), defaultValue);
        orDefault.add(System.currentTimeMillis() - before);
        eventProcessTime.put(event.getClass().getName(), orDefault);
        return event;
    }

    @AllArgsConstructor
    private static class EventHandler {
        private Object object;
        private Method method;

        @Override
        public String toString() {
            return method.toString();
        }

        @SneakyThrows
        public void invoke(Object event) {
            method.setAccessible(true);
            method.invoke(object, event);
        }
    }

}
