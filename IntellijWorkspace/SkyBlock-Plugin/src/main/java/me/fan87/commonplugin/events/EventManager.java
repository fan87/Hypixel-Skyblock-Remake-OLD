package me.fan87.commonplugin.events;

import io.github.retrooper.packetevents.PacketEvents;
import lombok.Getter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.registerers.PacketRegisterer;
import me.fan87.commonplugin.events.registerers.TickEventRegisterer;
import me.fan87.commonplugin.utils.SBMap;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    private static final Map<Object, Map<Class<?>, List<Method>>> handlers = new SBMap<Object, Map<Class<?>, List<Method>>>() {
        @Override
        public Map<Class<?>, List<Method>> getDefaultValue() {
            return new SBMap<Class<?>, List<Method>>() {
                @Override
                public List<Method> getDefaultValue() {
                    return new ArrayList<>();
                }
            };
        }
    };

    public static void unregister(Object subscriber) {
        handlers.remove(subscriber);
    }

    public static boolean isRegistered(Object subscriber) {
        return handlers.containsKey(subscriber);
    }

    public static void register(Object subscriber) {
        Map<Class<?>, List<Method>> classListMap = handlers.get(subscriber);
        for (Method declaredMethod : subscriber.getClass().getMethods()) {
            if (declaredMethod.getAnnotationsByType(Subscribe.class).length != 1 || declaredMethod.getParameterCount() != 1) continue;
            List<Method> methods = classListMap.get(declaredMethod.getParameterTypes()[0]);
            methods.add(declaredMethod);
            classListMap.put(declaredMethod.getParameterTypes()[0], methods);
        }
        handlers.put(subscriber, classListMap);
        for (Class<?> aClass : handlers.get(subscriber).keySet()) {
            handlers.get(subscriber).get(aClass).sort(Comparator.comparingInt(m -> -1 * m.getAnnotationsByType(Subscribe.class)[0].priority()));
        }
    }

    @SneakyThrows
    public static Object post(Object event) {

        try {
            for (Object subscriber : new ArrayList<>(handlers.keySet())) {
                Map<Class<?>, List<Method>> classListMap = handlers.get(subscriber);
                for (Class<?> aClass : new ArrayList<>(classListMap.keySet())) {
                    if (aClass.isAssignableFrom(event.getClass())) {
                        for (Method method : classListMap.get(aClass)) {
                            method.invoke(subscriber, event);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return event;
    }

}
