package me.fan87.commonplugin.players;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mojang.authlib.properties.Property;
import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.areas.SBArea;
import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.PlayerPostPortalEvent;
import me.fan87.commonplugin.events.impl.PlayerTickEvent;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.gui.impl.GuiYourProfile;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBMaterial;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.npc.AbstractNPC;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.collections.SBPlayerCollections;
import me.fan87.commonplugin.players.skill.SBPlayerSkills;
import me.fan87.commonplugin.players.skill.SBSkill;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import me.fan87.commonplugin.players.tradings.SBTrading;
import me.fan87.commonplugin.players.tradings.SBTradings;
import me.fan87.commonplugin.recipes.SBRecipe;
import me.fan87.commonplugin.utils.BukkitSerialization;
import me.fan87.commonplugin.utils.IngameDate;
import me.fan87.commonplugin.utils.NumberUtils;
import me.fan87.commonplugin.utils.SBNamespace;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import me.fan87.commonplugin.world.privateisland.PrivateIsland;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

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
    protected double purseCoins;

    @Getter
    @JsonProperty("personalBankAccount")
    private SBBankAccount bankAccount = new SBBankAccount();

    public boolean setPurseCoins(double coins) {
        this.purseCoins = Math.max(0, coins);
        return true;
    }


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
    private WorldsManager.WorldType currentWorldType = WorldsManager.WorldType.PRIVATE_ISLAND;

    @JsonProperty("inventory")
    private String inventoryData = "";

    @JsonProperty("enderChest")
    private String enderChestData = "";

    @JsonProperty("unlockedAreas")
    private List<String> unlockedAreas = new ArrayList<>();

    @JsonProperty("talkedNpcs")
    private List<String> talkedNpcs = new ArrayList<>();

    @JsonProperty("xp")
    private Float xp = 0F;

    @JsonProperty("privateIslandData")
    @Getter
    private PrivateIsland privateIsland;

    @Getter
    private final SBItemStack[] inventory = new SBItemStack[45];


    @JsonProperty("persistentStorage")
    @Getter
    private Map<String, String> persistentStorage = new HashMap<>();

    @JsonProperty("soldItems")
    private Deque<String> soldItems = new ArrayDeque<>();

    private String extraActionBar = "";
    private long extraActionBarTime = System.currentTimeMillis();

    public boolean showActionBar = true;

    private final List<SBItemStack> activeItems = new ArrayList<>();

    public void addCoins(double coins) {
        this.purseCoins += coins;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SBPlayer && ((SBPlayer) obj).getUuid().equals(getUuid());
    }

    public SBPlayer() {

    }

    @SneakyThrows
    public void addSoldItem(SBItemStack itemStack) {
        soldItems.addFirst(BukkitSerialization.itemStackArrayToBase64(new ItemStack[] {itemStack.getItemStack()}));
        while (soldItems.size() > 10) {
            soldItems.removeLast();
        }
    }

    @SneakyThrows
    public SBItemStack peekSoldItem() {
        String peek = soldItems.peek();
        if (peek == null) return null;
        return new SBItemStack(BukkitSerialization.itemStackArrayFromBase64(peek)[0]);
    }

    @SneakyThrows
    public SBItemStack pollSoldItem() {
        String poll = soldItems.poll();
        if (poll == null) return null;
        return new SBItemStack(BukkitSerialization.itemStackArrayFromBase64(poll)[0]);
    }

    public void showExtraActionBar(String message) {
        extraActionBarTime = System.currentTimeMillis() + 3000;
        extraActionBar = message;
    }

    public void init(Player player, SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        this.player = player;
        if (privateIsland == null) {
            privateIsland = new PrivateIsland();
        }
        privateIsland.init(this);
        privateIsland.load();
        player.closeInventory();
        this.uuid = player.getUniqueId().toString();
        if (!EventManager.isRegistered(this)) {
            EventManager.register(this);
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
            player.setExp(xp);

            Inventory itemStacks = BukkitSerialization.fromBase64(inventoryData);
            for (int i = 0; i < itemStacks.getSize(); i++) {
                player.getInventory().setItem(i, itemStacks.getItem(i));
            }

            Inventory e = BukkitSerialization.fromBase64(enderChestData);
            for (int i = 0; i < e.getSize(); i++) {
                player.getEnderChest().setItem(i, e.getItem(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            player.getInventory().clear();
            player.getEnderChest().clear();
        }

        Bukkit.getScheduler().runTaskLater(skyBlock, () -> {
            player.sendMessage(ChatColor.YELLOW + "Welcome to " + ChatColor.GREEN + "Hypixel SkyBlock" + ChatColor.YELLOW + "!");
        }, 20);

    }

    public void onDestroy() {
        privateIsland.unload();
        EventManager.unregister(this);
    }

    public void sendPacket(Packet<?> packet) {
        getCraftPlayer().getHandle().playerConnection.sendPacket(packet);
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
            return sbPlayer;
        }
        SBPlayer sbPlayer = new SBPlayer();
        players.insert(sbPlayer);
        return sbPlayer;
    }


    protected ItemStack[] getRawInventoryItems() {
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

    @Nullable
    public SBItemStack getHeldItem() {
        return getInventory()[getPlayer().getInventory().getHeldItemSlot() + 36];
    }

    /**
     * Update the inventory and save custom NBT Data to every items
     */
    @SneakyThrows
    public void updateInventory() {
        updateInventory(player.getInventory().getHeldItemSlot());
    }

    @SneakyThrows
    public void updateInventory(int heldSlot) {
        activeItems.clear();
        for (SBStat stat : stats.getStats()) {
            stat.getBonusValue().clear();
        }
        for (SBSkill skill : skills.getSkills()) {
            skill.setMultiplier(1f);
        }
        ItemStack[] allInventoryItems = getRawInventoryItems();
        for (int i = 0; i < allInventoryItems.length; i++) {
            inventory[i] = null;
            if (allInventoryItems[i] == null || allInventoryItems[i].getType() == Material.AIR) continue;
            ItemStack item = allInventoryItems[i];
            NBTTagCompound tag = CraftItemStack.asNMSCopy(item).getTag();
            boolean wasCustomized = false;
            if (tag != null)
                wasCustomized = tag.hasKey("ExtraAttributes");
            SBItemStack sbItemStack = new SBItemStack(item);
            if (!wasCustomized) {
                for (SBCollection collection : getCollections().getCollections()) {
                    if (collection.getItem() == sbItemStack.getType().getItem()) {
                        collection.setCollected(collection.getCollected() + sbItemStack.getItemStack().getAmount(), this);
                    }
                }
            }
            inventory[i] = sbItemStack;
            if (sbItemStack.getType().getType() != SBMaterial.ItemType.CUSTOM) {
                sbItemStack.updatePlayerStats(this, i);
            } else {
                SBCustomItem item1 = sbItemStack.getType().getItem();
                if (item1 != null) {
                    if (item1.isInActive(heldSlot, i, this)) {
                        sbItemStack.updatePlayerStats(this, i);
                        activeItems.add(sbItemStack);
                    }
                }

            }
        }
    }

    public List<SBItemStack> getActiveItems() {
        return new ArrayList<>(activeItems);
    }

    public boolean isItemActive(SBCustomItem item) {
        for (SBItemStack activeItem : activeItems) {
            if (activeItem != null & activeItem.getType().getItem() == item) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnchantmentActive(SBEnchantment enchantment) {
        for (SBItemStack activeItem : activeItems) {
            if (activeItem.getEnchantmentLevel(enchantment) > 0) {
                return true;
            }
        }
        return false;
    }

    public CraftPlayer getCraftPlayer() {
        return (CraftPlayer) player;
    }

    @Subscribe()
    public void onTick(ServerTickEvent event) {


        if (player.getTicksLived() % 2 == 0) {
            scoreboard.setTitle(player.getPlayer(), getScoreboardTitle());
        }
        if (player.getTicksLived() % 5 == 0) {
            scoreboard.setLines(player.getPlayer(), getScoreboardContent());
            if (showActionBar) {
                displayActionBar();
            }
        }
        tickStats();

        PlayerTickEvent e = new PlayerTickEvent(this);
        EventManager.post(e);

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

    public boolean hasTalkedTo(AbstractNPC<?> npc) {
        AbstractNPC.NPCRegistry npcRegistry = AbstractNPC.getNPCRegistry(npc.getClass());
        if (npcRegistry != null) {
            return talkedNpcs.contains(new SBNamespace(npcRegistry.addonName(), npcRegistry.namespace()).toString());
        }
        return false;
    }

    public void setTalkedTo(AbstractNPC<?> npc, boolean talked) {
        AbstractNPC.NPCRegistry npcRegistry = AbstractNPC.getNPCRegistry(npc.getClass());
        if (npcRegistry != null) {
            String e = new SBNamespace(npcRegistry.addonName(), npcRegistry.namespace()).toString();
            if (talked) {
                if (talkedNpcs.contains(e)) return;
                talkedNpcs.add(e);
            } else {
                talkedNpcs.remove(e);
            }
        }
    }

    /**
     * Displays the bottom text of the screen
     */
    public void displayActionBar() {
        String text = stats.getHealth().getColor() + (int) Math.floor(getPlayer().getHealth()*5) + "/" + (int) (getPlayer().getMaxHealth()*5) + stats.getHealth().getIcon() + "   ";
        if (System.currentTimeMillis() < extraActionBarTime) {
            text += extraActionBar + "   ";
        } else if (stats.getDefence().getValue(this) > 0) {
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

    public void lockRecipe(SBCustomItem item) {
        unlockedRecipeData.remove(item.getNamespace());
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
        Bukkit.getScheduler().runTask(skyBlock, () -> {
            privateIsland.save();
            this.xp = player.getExp();
            this.inventoryData = BukkitSerialization.toBase64(player.getInventory());
            this.enderChestData = BukkitSerialization.toBase64(player.getEnderChest());
            MongoCollection players = skyBlock.getDatabaseManager().getCollection("players");
            players.update(String.format("{\"uuid\": \"%s\"}", uuid)).upsert().multi().with(this);
        });

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
        SBArea areaOf = skyBlock.getAreasManager().getAreaOf(player.getLocation());
        if (areaOf == null) return null;
        if (!unlockedAreas.contains(areaOf.getName())) {
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 0.5f);
            sendTitle(areaOf.getColor() + ChatColor.BOLD.toString() + areaOf.getName(), ChatColor.GREEN + "New Zone Discovered!");
            unlockedAreas.add(areaOf.getName());
            player.sendMessage(ChatColor.GREEN +                       "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            player.sendMessage(ChatColor.GREEN + "  " + ChatColor.BOLD + "NEW ZONE " + ChatColor.DARK_GRAY + ChatColor.BOLD + "- " + areaOf.getColor().toString() + ChatColor.BOLD + areaOf.getName());
            player.sendMessage("");
            player.sendMessage(ChatColor.DARK_GRAY + "  ➤ " + ChatColor.YELLOW + "Objective system coming soon : )");
            player.sendMessage("");
            player.sendMessage(ChatColor.GREEN +                       "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        }
        return areaOf;
    }

    public boolean hasBeenTo(SBArea area) {
        return unlockedAreas.contains(area.getName());
    }

    public void sendTitle(String title, String subtitle) {
        getCraftPlayer().getHandle().playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, null));
        getCraftPlayer().getHandle().playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, new ChatComponentText(subtitle)));
        getCraftPlayer().getHandle().playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, new ChatComponentText(title)));
    }

    public List<String> getScoreboardContent() {
        List<String> out = new ArrayList<>();
        if (getWorld() == null || getWorld().getWorldType() == WorldsManager.WorldType.NONE) return out;
        Date date = new Date(System.currentTimeMillis());
        out.add(ChatColor.GRAY + new SimpleDateFormat("dd/MM/yy").format(date) + " " + ChatColor.DARK_GRAY + skyBlock.getConfigsManager().config.serverId + getWorld().getWorldID());
        out.add("");
        IngameDate ingameDate = new IngameDate(System.currentTimeMillis() - skyBlock.getDatabaseManager().getServerData().dayZero);
        out.add(String.format(" %s %s", ingameDate.getMonthDisplay().getName(), ingameDate.getDayOfMonthDisplay()));
        out.add(ChatColor.GRAY + " " + ingameDate.getTimeDisplay());
        SBArea area = getArea();
        if (getWorld() == null || getWorld().getWorldType() != WorldsManager.WorldType.PRIVATE_ISLAND) {
            out.add(ChatColor.GRAY + " ⏣ " + (area ==null?"None": area.getColor() + area.getName()));
        } else {
            out.add(ChatColor.GRAY + " ⏣ " + ChatColor.GREEN + "Your Island");
        }

        out.add("");
        out.add(ChatColor.RESET + "Purse: " + ChatColor.GOLD + NumberUtils.formatNumber(getPurseCoins()));
        out.add("");
        out.add("Objective");
        out.add(ChatColor.YELLOW + "Objective System coming soon!");
        out.add("");
        out.add(ChatColor.YELLOW + skyBlock.getConfigsManager().config.scoreboardServerIp);
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
        return ChatColor.YELLOW + ChatColor.BOLD.toString() + "SKYBLOCK";
    }

    public boolean send(WorldsManager.WorldType worldType) {
        World w = null;
        SBWorld world1 = null;
        if (worldType == WorldsManager.WorldType.PRIVATE_ISLAND) {
            w = privateIsland.getWorld();
            world1 = skyBlock.getWorldsManager().getWorld(privateIsland.getWorldName());
        }
        for (SBWorld world : skyBlock.getWorldsManager().getWorlds()) {
            if (world.getWorldType() == worldType) {
                w = skyBlock.getServer().getWorld(world.getWorldName());
                WorldsManager worldsManager = skyBlock.getWorldsManager();
                if (worldsManager != null) {
                    world1 = worldsManager.getWorld(w.getName());
                }
            }
        }
        if (w != null && world1 != null) {
            PlayerPostPortalEvent event = new PlayerPostPortalEvent(skyBlock.getWorldsManager().getWorld(player.getWorld().getName()), world1, this);
            player.sendMessage(ChatColor.GRAY + "Sending to server " + skyBlock.getConfigsManager().config.serverId + world1.getWorldID() + "...");
            Location spawnLocation = w.getSpawnLocation();
            spawnLocation.setYaw(world1.getSpawnYaw());
            player.teleport(spawnLocation);
            EventManager.post(event);
            currentWorldType = worldType;
            player.sendMessage("");
            player.sendMessage(ChatColor.GREEN + "You are playing on profile: " + ChatColor.YELLOW + player.getName());
            return true;
        }

        player.sendMessage(ChatColor.RED + "Unable to send you to " + worldType.getName() + "! Please try again later.");
        return false;
    }



}
