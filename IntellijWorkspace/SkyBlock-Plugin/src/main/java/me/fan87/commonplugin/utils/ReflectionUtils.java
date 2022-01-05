package me.fan87.commonplugin.utils;

import lombok.SneakyThrows;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

public class ReflectionUtils {

    /**
     * Get the command map of the server
     * @param server Server instance
     * @return The command map
     */
    @SneakyThrows
    public static CommandMap getServerCommandMap(Server server) {
        Field commandMap1 = server.getClass().getDeclaredField("commandMap");
        commandMap1.setAccessible(true);
        Object commandMap = commandMap1.get(server);
        return ((CommandMap) commandMap);
    }

}
