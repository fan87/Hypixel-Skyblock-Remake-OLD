package me.fan87.commonplugin.utils;

import lombok.SneakyThrows;
import net.minecraft.server.v1_8_R3.Item;
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

    /**
     * Get the crafting result, basically means when you craft something, it will still be on the crafting table. E.g. Milk, while crafting cake, milk will turn into bucket
     * @param item Item to get its crafting result
     * @return The crafting result, or `containerItem` in MCP mapping
     */
    public static Item getCraftingResult(Item item) {
        return item.q();
    }

    /**
     * @see ReflectionUtils#getCraftingResult(Item)
     */
    public static void setCraftingResult(Item item, Item craftingResult) {
        item.c(craftingResult);
    }

    /**
     * @see ReflectionUtils#getCraftingResult(Item)
     */
    public static void hasCraftingResult(Item item, Item craftingResult) {
        item.r();
    }

}
