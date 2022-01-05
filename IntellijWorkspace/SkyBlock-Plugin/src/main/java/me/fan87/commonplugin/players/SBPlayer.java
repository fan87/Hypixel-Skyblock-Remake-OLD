package me.fan87.commonplugin.players;

import com.mojang.authlib.properties.Property;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.gui.impl.GuiYourProfile;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBMaterial;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;

public class SBPlayer {

    @Getter
    private final Player player;

    @Getter
    private SBPlayerStats stats = new SBPlayerStats();
    @Getter
    @Setter
    private double mana;
    @Getter
    @Setter
    private double coins;

    @Getter
    private SkyBlock skyBlock;
    @Getter
    private String skin;

    public boolean showActionBar = true;


    public SBPlayer(Player player, SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        this.player = player;
        EventManager.EVENT_BUS.register(this);
        mana = getStats().getIntelligence().getValue() + 100;
        for (Property textures : getCraftPlayer().getProfile().getProperties().get("textures")) {
            if (textures.getName().equals("textures")) {
                this.skin = textures.getValue();
                break;
            }
        }
    }

    /**
     * Update the inventory and save custom NBT Data to every items
     */
    public void updateInventory() {
        stats = new SBPlayerStats();
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) == null || player.getInventory().getItem(i).getType() == Material.AIR) continue;
            ItemStack item = player.getInventory().getItem(i);
            SBItemStack sbItemStack = new SBItemStack(item);
            if (sbItemStack.getType().getType() != SBMaterial.ItemType.CUSTOM) {
                sbItemStack.updatePlayerStats(this, i);
            } else {
                if (sbItemStack.getType().getItem().activateForSlot(i, this)) {
                    sbItemStack.updatePlayerStats(this, i);
                }
            }
        }

    }


    public CraftPlayer getCraftPlayer() {
        return (CraftPlayer) player;
    }

    @Subscribe
    public void onTick(ServerTickEvent event) {
        tickStats();
        if (showActionBar) {
            displayActionBar();
        }
    }



    public void tickStats() {
        for (Field declaredField : stats.getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                ((SBStat) declaredField.get(stats)).onTick(this);
            } catch (Exception e) {
                System.out.println("Failed to update stat of " + player.getUniqueId());
                e.printStackTrace();
            }
        }
    }

    /**
     * DEPRECATED!
     * TODO: Write a replacement
     */
    @Deprecated
    public boolean isItemActive(SBCustomItem customItem) {
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) == null || player.getInventory().getItem(i).getType() == Material.AIR) continue;
            ItemStack item = player.getInventory().getItem(i);
            SBItemStack sbItemStack = new SBItemStack(item);
            if (sbItemStack.getType().getType() != SBMaterial.ItemType.CUSTOM) {
                continue;
            } else {
                if (sbItemStack.getType().getItem().activateForSlot(i, this) && sbItemStack.getType().getItem() == customItem) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Open the skyblock menu
     * TODO: Un hard code it
     */
    public void openSkyBlockMenu() {
        GuiSkyBlockMenu menu = new GuiSkyBlockMenu(this);
        getPlayer().openInventory(menu.getInventory());
    }

    /**
     * Open the profile menu
     * TODO: Un hard code it
     */
    public void openProfileMenu() {
        GuiYourProfile profileGui = new GuiYourProfile(this);
        profileGui.open(player.getPlayer());
    }

    /**
     * Displays the bottom text of the screen
     */
    public void displayActionBar() {
        String text = stats.getHealth().getColor() + (int) Math.floor(getPlayer().getHealth()) + "/" + (int) (getPlayer().getMaxHealth()) + stats.getHealth().getIcon() + "   ";
        if (stats.getDefence().getValue() > 0) {
            text += stats.getDefence().getColor() + stats.getDefence().getValueDisplay((int) stats.getDefence().getValue()) + stats.getDefence().getIcon() + " " + stats.getDefence().getName() + "   ";
        }
        text += stats.getIntelligence().getColor() + (int) Math.floor(mana) + "/" + (int) Math.floor(getStats().getIntelligence().getValue() + 100) + stats.getIntelligence().getIcon();
        getCraftPlayer().getHandle().playerConnection.networkManager.a(new PacketPlayOutChat(new ChatComponentText(text), (byte) 2), null);
    }



}
