package me.fan87.commonplugin.positionplaceholder;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.utils.SBMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArmorstandPlaceholderManager {

    private final SkyBlock skyBlock;


    public final Map<String, List<ArmorStandHandler>> handlers = new SBMap<String, List<ArmorStandHandler>>() {
        @Override
        public List<ArmorStandHandler> getDefaultValue() {
            return new ArrayList<>();
        }
    };

    public ArmorstandPlaceholderManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        EventManager.register(this);

        registerArmorStandHandler("TEST", new ArmorStandHandler() {
            @Override
            public boolean onFound(SBArmorStandPlaceHolder armorStandPlaceHolder) {
                return true;
            }
        });
    }

    @Subscribe
    public void onTick(ServerTickEvent event) {
        if (skyBlock.getConfigsManager().config.doNotReplacePlaceHolder) return;
        for (World world : skyBlock.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (!(entity instanceof ArmorStand)) continue;
                ArmorStand armorStand = (ArmorStand) entity;
                ItemStack itemInHand = armorStand.getItemInHand();
                if (itemInHand == null) continue;
                if (itemInHand.getType() == Material.AIR) continue;
                NBTItem nbt = new NBTItem(itemInHand, true);
                if (nbt.hasKey("SBArmorPlaceHolderKey")) {
                    String namespace = nbt.getString("SBArmorPlaceHolderKey");
                    String[] data = nbt.getStringList("SBArmorPlaceHolderValues").toArray(new String[0]);
                    for (ArmorStandHandler armorStandHandler : handlers.get(namespace)) {
                        if (armorStandHandler.onFound(new SBArmorStandPlaceHolder(namespace, data, armorStand.getLocation()))) {
                            entity.remove();
                        }
                    }
                }
            }
        }
    }

    public void registerArmorStandHandler(String name, ArmorStandHandler signHandler) {
        List<ArmorStandHandler> signHandlers = handlers.get(name);
        signHandlers.add(signHandler);
        handlers.put(name, signHandlers);
    }

    @AllArgsConstructor
    public static class SBArmorStandPlaceHolder {
        private String namespace;
        private String[] value;
        @Getter
        private Location location;

        public String getPlaceHolderName() {
            return namespace;
        }

        public String getData(int index) {
            return value[index];
        }

        public int getDataLength(int index) {return value.length;}

    }

    public interface ArmorStandHandler {
        boolean onFound(SBArmorStandPlaceHolder armorStandPlaceHolder);
    }

}
