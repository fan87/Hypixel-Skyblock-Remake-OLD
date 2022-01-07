package me.fan87.commonplugin.players;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.gui.impl.GuiYourProfile;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBMaterial;
import me.fan87.commonplugin.players.collections.SBPlayerCollections;
import me.fan87.commonplugin.players.skill.SBPlayerSkills;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.greenrobot.eventbus.Subscribe;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.lang.reflect.Field;
import java.util.Iterator;

public class SBPlayer {

    @JsonProperty("_id")
    @MongoId
    @MongoObjectId
    public String _id;

    @Getter
    private Player player;

    @Getter
    @JsonProperty("stats")
    private SBPlayerStats stats = new SBPlayerStats();

    @Getter
    @JsonProperty("skills")
    private SBPlayerSkills skills = new SBPlayerSkills();

    @Getter
    @JsonProperty("collections")
    private SBPlayerCollections collections = new SBPlayerCollections();

    @Getter
    @JsonProperty("uuid")
    private String uuid;

    @Getter
    @Setter
    private double mana;

    @Getter
    @Setter
    @JsonProperty("coins")
    private double coins;

    @Getter
    private SkyBlock skyBlock;
    @Getter
    private String skin;

    public boolean showActionBar = true;

    public void addCoins(double coins) {
        this.coins += coins;
    }

    public SBPlayer() {
    }

    public void init(Player player, SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        this.player = player;
        this.uuid = player.getUniqueId().toString();
        if (!EventManager.EVENT_BUS.isRegistered(this)) {
            EventManager.EVENT_BUS.register(this);
        }
        mana = getStats().getIntelligence().getValue(this) + 100;
        for (Property textures : getCraftPlayer().getProfile().getProperties().get("textures")) {
            if (textures.getName().equals("textures")) {
                this.skin = textures.getValue();
                break;
            }
        }
    }

    protected static SBPlayer newPlayer(Player player, SkyBlock skyBlock) {
        MongoCollection players = skyBlock.getDatabaseManager().getCollection("players");
        Iterator<SBPlayer> iterator = players.find("{uuid: \"" + player.getUniqueId().toString() + "\"}").as(SBPlayer.class).iterator();
        while (iterator.hasNext()) {
            SBPlayer sbPlayer = iterator.next();
            System.out.println("Player Joined! " + sbPlayer._id);
            //
            players.remove(String.format("{\"uuid\": \"%s\", \"_id\": {\"$ne\": {\"$oid\": \"%s\"}}}", player.getUniqueId().toString(), sbPlayer._id));
            sbPlayer.init(player, skyBlock);
            return sbPlayer;
        }
        SBPlayer sbPlayer = new SBPlayer();
        sbPlayer.init(player, skyBlock);
        players.insert(sbPlayer);
        return sbPlayer;
    }


    /**
     * Update the inventory and save custom NBT Data to every items
     */
    @SneakyThrows
    public void updateInventory() {
        for (Field declaredField : stats.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            SBStat stat = (SBStat) declaredField.get(stats);
            stat.getBonusValue().clear();
        }
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
        String text = stats.getHealth().getColor() + (int) Math.floor(getPlayer().getHealth()*5) + "/" + (int) (getPlayer().getMaxHealth()*5) + stats.getHealth().getIcon() + "   ";
        if (stats.getDefence().getValue(this) > 0) {
            text += stats.getDefence().getColor() + stats.getDefence().getValueDisplay((int) stats.getDefence().getValue(this)) + stats.getDefence().getIcon() + " " + stats.getDefence().getName() + "   ";
        }
        text += stats.getIntelligence().getColor() + (int) Math.floor(mana) + "/" + (int) Math.floor(getStats().getIntelligence().getValue(this) + 100) + stats.getIntelligence().getIcon();
        getCraftPlayer().getHandle().playerConnection.networkManager.a(new PacketPlayOutChat(new ChatComponentText(text), (byte) 2), null);
    }

    public void save() {
        MongoCollection players = skyBlock.getDatabaseManager().getCollection("players");
        players.update(String.format("{\"uuid\": \"%s\"}", uuid)).upsert().multi().with(this);
    }

}
