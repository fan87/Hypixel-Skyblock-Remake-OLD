package me.fan87.commonplugin.players;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mojang.authlib.properties.Property;
import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.areas.SBArea;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.gui.impl.GuiYourProfile;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBMaterial;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.players.collections.SBPlayerCollections;
import me.fan87.commonplugin.players.skill.SBPlayerSkills;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import me.fan87.commonplugin.players.tradings.SBTrading;
import me.fan87.commonplugin.players.tradings.SBTradings;
import me.fan87.commonplugin.recipes.SBRecipe;
import me.fan87.commonplugin.utils.BukkitSerialization;
import me.fan87.commonplugin.utils.IngameDate;
import me.fan87.commonplugin.utils.NumberUtils;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.greenrobot.eventbus.Subscribe;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
    @JsonProperty("coins")
    private double coins;

    public void setCoins(double coins) {
        this.coins = Math.max(0, coins);
    }

    @Getter
    @Setter
    @JsonProperty("bankCoins")
    private double bankCoins;

    @JsonProperty("unlockedRecipeData")
    private List<String> unlockedRecipeData = new ArrayList<>();

    @JsonProperty("unlockedTradingData")
    private List<String> unlockedTradingData = new ArrayList<>();

    @Getter
    private SkyBlock skyBlock;
    @Getter
    private String skin;

    @Setter
    @Getter
    private boolean debugging = false;

    @Setter
    @Getter
    private boolean building = false;

    @Getter
    private JPerPlayerMethodBasedScoreboard scoreboard;

    @Getter
    @JsonProperty("lastWorld")
    private WorldsManager.WorldType currentWorldType;

    @JsonProperty("inventory")
    private String inventory = "";

    public boolean showActionBar = true;

    private final List<SBItemStack> activeItems = new ArrayList<>();

    public void addCoins(double coins) {
        this.coins += coins;
    }

    public SBPlayer() {
    }

    public void init(Player player, SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        this.player = player;
        player.closeInventory();
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
        for (SBCustomItem value : SBItems.getRegisteredItems().values()) {
            if (value.isRecipeUnlockedByDefault()) unlockRecipe(value);
        }
        for (SBTrading value : SBTradings.getRegisteredTradings().values()) {
            if (value.isUnlockedByDefault()) unlockTrading(value);
        }

        scoreboard = new JPerPlayerMethodBasedScoreboard();
        scoreboard.addPlayer(player.getPlayer());

        if (currentWorldType != null) {
            send(currentWorldType);
        }

        try {
            Inventory itemStacks = BukkitSerialization.fromBase64(inventory);
            for (int i = 0; i < itemStacks.getSize(); i++) {
                player.getInventory().setItem(i, itemStacks.getItem(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        player.sendMessage(ChatColor.GREEN + "You are playing on profile: " + ChatColor.YELLOW + player.getName());
    }

    protected static SBPlayer newPlayer(Player player, SkyBlock skyBlock) {
        MongoCollection players = skyBlock.getDatabaseManager().getCollection("players");
        if (players.count("{uuid: \"" + player.getUniqueId().toString() + "\"}") == 0) {
            player.sendMessage(ChatColor.YELLOW + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n" +
                    ChatColor.WHITE + ChatColor.BOLD + "                     Welcome to SkyBlock, " + ChatColor.RESET + player.getDisplayName() + ChatColor.WHITE + ChatColor.BOLD + "!\n" +
                    "\n" +
                    ChatColor.YELLOW + "                    This is your island! The SkyBlock\n" +
                    ChatColor.YELLOW + "                 universe has many lands to discover,\n" +
                    ChatColor.YELLOW + "               secrets to uncover, and people to meet.\n" +
                    ChatColor.YELLOW + "                  Collect resources, craft items, and\n" +
                    ChatColor.YELLOW + "              complete objectives to advance your way\n" +
                    ChatColor.YELLOW + "                             through SkyBlock.\n" +
                    "\n" +
                    ChatColor.YELLOW + "                                   Have fun!\n" +
                    "\n" +
                    ChatColor.YELLOW + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        }
        Iterator<SBPlayer> iterator = players.find("{uuid: \"" + player.getUniqueId().toString() + "\"}").as(SBPlayer.class).iterator();
        while (iterator.hasNext()) {
            SBPlayer sbPlayer = iterator.next();
            players.remove(String.format("{\"uuid\": \"%s\", \"_id\": {\"$ne\": {\"$oid\": \"%s\"}}}", player.getUniqueId().toString(), sbPlayer._id));
            sbPlayer.init(player, skyBlock);
            return sbPlayer;
        }
        SBPlayer sbPlayer = new SBPlayer();
        sbPlayer.init(player, skyBlock);
        players.insert(sbPlayer);
        return sbPlayer;
    }


    public ItemStack[] getAllInventoryItems() {
        ItemStack[] out = new ItemStack[45];
        out[5] = player.getInventory().getArmorContents()[0];
        out[6] = player.getInventory().getArmorContents()[1];
        out[7] = player.getInventory().getArmorContents()[2];
        out[8] = player.getInventory().getArmorContents()[3];
        for (int i = 0; i < 9; i++) {
            out[36 + i] = player.getInventory().getItem(i);
        }
        for (int i = 9; i < 36; i++) {
            out[i] = player.getInventory().getItem(i);
        }
        return out;
    }

    /**
     * Update the inventory and save custom NBT Data to every items
     */
    @SneakyThrows
    public void updateInventory() {
        activeItems.clear();
        for (SBStat stat : stats.getStats()) {
            stat.getBonusValue().clear();
        }
        ItemStack[] allInventoryItems = getAllInventoryItems();
        for (int i = 0; i < allInventoryItems.length; i++) {
            if (allInventoryItems[i] == null || allInventoryItems[i].getType() == Material.AIR) continue;
            ItemStack item = allInventoryItems[i];
            SBItemStack sbItemStack = new SBItemStack(item);
            if (sbItemStack.getType().getType() != SBMaterial.ItemType.CUSTOM) {
                sbItemStack.updatePlayerStats(this, i);
            } else {
                if (sbItemStack.getType().getItem().isInActive(i, this)) {
                    sbItemStack.updatePlayerStats(this, i);
                    activeItems.add(sbItemStack);;
                }
            }
        }

    }

    public List<SBItemStack> getActiveItems() {
        return new ArrayList<>(activeItems);
    }

    public CraftPlayer getCraftPlayer() {
        return (CraftPlayer) player;
    }

    @Subscribe
    public void onTick(ServerTickEvent event) {
        scoreboard.setTitle(player.getPlayer(), getScoreboardTitle());
        scoreboard.setLines(player.getPlayer(), getScoreboardContent());
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
                if (sbItemStack.getType().getItem().isInActive(i, this) && sbItemStack.getType().getItem() == customItem) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Opens the skyblock menu
     */
    public void openSkyBlockMenu() {
        GuiSkyBlockMenu menu = new GuiSkyBlockMenu(this);
        getPlayer().openInventory(menu.getInventory());
    }

    /**
     * Open the profile menu
     * @deprecated Use {@link GuiYourProfile#open(Player)} instead
     */
    @Deprecated
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

    public boolean isRecipeUnlocked(SBCustomItem item) {
        return unlockedRecipeData.contains(item.getNamespace());
    }

    public boolean isRecipeUnlocked(SBRecipe recipe) {
        if (recipe == null) return false;
        if (recipe.getOutputType() == null) return true;
        return isRecipeUnlocked(recipe.getOutputType());
    }

    public void unlockRecipe(SBCustomItem item) {
        if (!unlockedRecipeData.contains(item.getNamespace())) {
            unlockedRecipeData.add(item.getNamespace());
        }
    }

    public List<SBCustomItem> getAllUnlockedRecipes() {
        List<SBCustomItem> items = new ArrayList<>();
        for (String namespace : new ArrayList<>(unlockedRecipeData)) {
            SBCustomItem item = SBItems.getItem(namespace);
            if (item == null) {
                unlockedRecipeData.remove(namespace);
                continue;
            }
            if (isRecipeUnlocked(item)) items.add(item);
        }
        return items;
    }

    public List<SBTrading> getAllUnlockedTradings() {
        List<SBTrading> tradings = new ArrayList<>();
        for (String namespace : new ArrayList<>(unlockedTradingData)) {
            SBTrading item = SBTradings.getTradingByNamespace(namespace);
            if (item == null) {
                unlockedRecipeData.remove(namespace);
                continue;
            }
            if (isTradingUnlocked(item)) tradings.add(item);
        }
        return tradings;
    }

    public void unlockTrading(SBTrading trading) {
        if (!unlockedTradingData.contains(trading.getNamespace().toString())) {
            unlockedTradingData.add(trading.getNamespace().toString());
        }
    }

    public boolean isTradingUnlocked(SBTrading trading) {
        for (String unlockedTradingDatum : unlockedTradingData) {
            if (unlockedTradingDatum.equals(trading.getNamespace().toString())) {
                return true;
            }
        }
        return false;
    }

    public void save() {
        this.inventory = BukkitSerialization.toBase64(player.getInventory());
        MongoCollection players = skyBlock.getDatabaseManager().getCollection("players");
        players.update(String.format("{\"uuid\": \"%s\"}", uuid)).upsert().multi().with(this);
    }

    public void openEnderChest() {
        player.openInventory(player.getEnderChest());
        player.playSound(player.getLocation(), Sound.CHEST_OPEN, 0.5f, 0.69f);
        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 0.5f, 0.69f);
    }

    public SBWorld getWorld() {
        return skyBlock.getWorldsManager().getWorld(player.getWorld().getName());
    }

    public SBArea getArea() {
        return skyBlock.getAreasManager().getAreaOf(player.getLocation());
    }

    public List<String> getScoreboardContent() {
        List<String> out = new ArrayList<>();
        if (getWorld() == null || getWorld().getWorldType() == WorldsManager.WorldType.NONE) return out;
        Date date = new Date(System.currentTimeMillis());
        out.add(ChatColor.GRAY + new SimpleDateFormat("dd/MM/yy").format(date) + " " + ChatColor.DARK_GRAY + skyBlock.getConfigsManager().config.serverId + getWorld().getWorldID());
        out.add("");
        IngameDate ingameDate = new IngameDate(skyBlock.getDatabaseManager().getServerData().dayZero);
        out.add(String.format(" %s %s", ingameDate.getMonthDisplay().getName(), ingameDate.getDayOfMonthDisplay()));
        out.add(ChatColor.GRAY + " " + ingameDate.getTimeDisplay());
        SBArea area = getArea();
        out.add(ChatColor.GRAY + " ⏣ " + (area ==null?"None": area.getColor() + area.getName()));
        out.add("");
        out.add(ChatColor.RESET + "Purse: " + ChatColor.GOLD + NumberUtils.formatNumber(coins));
        out.add("");
        out.add("Objective");
        out.add(ChatColor.YELLOW + "Objective System coming soon!");
        out.add("");
        out.add(ChatColor.YELLOW + skyBlock.getConfigsManager().config.serverIp);
        return out;
    }


    public String getScoreboardTitle() {
        int tick = player.getTicksLived();
        int firstDuration = 120;
        int animationDuration = 2;
        int keepWhiteDuration = 20;
        int blinkDuration = 12;
        int totalTick = firstDuration + animationDuration + keepWhiteDuration + blinkDuration*3;
        if (tick % totalTick <= firstDuration) {
            return ChatColor.YELLOW + ChatColor.BOLD.toString() + "SKYBLOCK";
        }
        for (int i = 0; i < "SKYBLOCK".length(); i++) {
            if (tick % totalTick <= firstDuration + animationDuration*(i + 2)) {
                String first = "SKYBLOCK".substring(0, i + 1);
                String second = "SKYBLOCK".substring(i + 1);
                return ChatColor.WHITE + ChatColor.BOLD.toString() + first + ChatColor.YELLOW + ChatColor.BOLD.toString() + second;
            }
        }
        if (tick % totalTick <= firstDuration + animationDuration*"SKYBLOCK".length() + keepWhiteDuration) {
            return ChatColor.WHITE + ChatColor.BOLD.toString() + "SKYBLOCK";
        }
        if (tick % totalTick <= firstDuration + animationDuration*"SKYBLOCK".length() + keepWhiteDuration + blinkDuration) {
            return ChatColor.YELLOW + ChatColor.BOLD.toString() + "SKYBLOCK";
        }
        if (tick % totalTick <= firstDuration + animationDuration*"SKYBLOCK".length() + keepWhiteDuration + blinkDuration*2) {
            return ChatColor.WHITE + ChatColor.BOLD.toString() + "SKYBLOCK";
        }
        return "UNKNOWN";
    }

    public boolean send(WorldsManager.WorldType worldType) {
        for (SBWorld world : skyBlock.getWorldsManager().getWorlds()) {
            if (world.getWorldType() == worldType) {
                World w = skyBlock.getServer().getWorld(world.getWorldName());
                player.sendMessage(ChatColor.GRAY + "Sending to server " + skyBlock.getConfigsManager().config.serverId + world.getWorldID() + "...");
                Location spawnLocation = w.getSpawnLocation();
                spawnLocation.setYaw(180);
                player.teleport(spawnLocation);
                currentWorldType = worldType;
                return true;
            }
        }
        player.sendMessage(ChatColor.RED + "Unable to send you to " + worldType.getName() + "! Please try again later.");
        return false;
    }

}
