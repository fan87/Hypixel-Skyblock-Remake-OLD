package me.fan87.commonplugin.utils;

import lombok.SneakyThrows;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTReadLimiter;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;

import java.io.DataInput;
import java.io.DataOutput;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

    @SneakyThrows
    public static void writeNBTCompound(NBTBase nbt, DataOutput output) {
        Method write = NBTBase.class.getDeclaredMethod("write", DataOutput.class);
        write.setAccessible(true);
        write.invoke(nbt, output);
    }

    @SneakyThrows
    public static void loadNBTCompound(NBTBase nbt, DataInput input, int depth, NBTReadLimiter readLimiter) {
        Method write = NBTBase.class.getDeclaredMethod("load", DataInput.class, int.class, NBTReadLimiter.class);
        write.setAccessible(true);
        write.invoke(nbt, input, depth, readLimiter);
    }

    @SneakyThrows
    public static <T> T get(Object obj, String fieldName, Class<T> type) {
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.getName().equals(fieldName)) {
                    declaredField.setAccessible(true);
                    Object o = declaredField.get(obj);
                    return (T) o;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    @SneakyThrows
    public static void set(Object obj, String fieldName, Object value) {
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.getName().equals(fieldName)) {
                    declaredField.setAccessible(true);
                    declaredField.set(obj, value);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

}
