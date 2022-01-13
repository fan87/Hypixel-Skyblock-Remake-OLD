package me.fan87.commonplugin.world;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.features.impl.api.ClientSideEntityController;
import me.fan87.commonplugin.npc.AbstractNPC;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.SBNamespace;
import me.fan87.commonplugin.utils.Vec3d;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bson.ByteBuf;
import org.bson.RawBsonDocument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.nio.charset.StandardCharsets;

@EqualsAndHashCode
public class SBWorld {

    @Getter
    private final String worldName;
    private final WorldsManager worldsManager;
    private final SkyBlock skyBlock;

    protected SBWorld(SkyBlock skyBlock, String worldName, WorldsManager worldsManager) {
        this.skyBlock = skyBlock;
        this.worldName = worldName;
        this.worldsManager = worldsManager;
    }

    protected void init() {
        NBTTagList npcList = getWorldData().getList("NpcList", 10);
        for (int i = 0; i < npcList.size(); i++) {
            NBTTagCompound nbtData = npcList.get(i);
            spawnNpc(new Vector(nbtData.getDouble("x"), nbtData.getDouble("y"), nbtData.getDouble("z")), SBNamespace.fromString(nbtData.getString("type")));
        }
    }

    public WorldsManager.WorldType getWorldType() {
        try {
            return WorldsManager.WorldType.valueOf(worldsManager.getConfig().getString(worldName + ".type"));
        } catch (Exception ignored) {
            return WorldsManager.WorldType.NONE;
        }
    }

    public void setWorldType(WorldsManager.WorldType worldType) {
        worldsManager.getConfig().set(worldName + ".type", worldType.getName());
    }

    public Vec3d getSpawn() {
        if (!worldsManager.getConfig().contains(worldName + ".spawn")) {
            return null;
        }
        Vec3d vec3d = Vec3d.fromString(worldsManager.getConfig().getString(worldName + ".spawn"));
        return vec3d;
    }

    public float getSpawnYaw() {
        boolean contains = worldsManager.getConfig().contains(worldName + ".spawnYaw");
        if (contains) {
            return ((float) worldsManager.getConfig().getDouble(worldName + ".spawnYaw"));
        } else {
            setSpawnYaw(180);
            return 180;
        }
    }

    public void setSpawnYaw(float spawnYaw) {
        worldsManager.getConfig().set(worldName + ".spawnYaw", spawnYaw);
        worldsManager.saveConfig();
    }

    public int getPreScanArea() {
        return worldsManager.getConfig().contains(worldName + ".scanSize")?worldsManager.getConfig().getInt(worldName + ".scanSize"):20;
    }

    public void setSpawn(Vec3d spawn) {
        worldsManager.getConfig().set(worldName + ".spawn", spawn.toString());
        worldsManager.saveConfig();
    }

    public String getWorldID() {
        StringBuilder out = new StringBuilder();
        int i = worldsManager.getWorlds().indexOf(this);
        String chars = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789qwertyuiopasdfghjklzxcvbnm";
        while(i > 0) {
            out.insert(0, chars.charAt(i % chars.length()));
            i=i/chars.length();
        }
        return out.toString();
    }

    public World getWorld() {
        World world = worldsManager.getSkyBlock().getServer().getWorld(getWorldName());
        return world;
    }

    public boolean canPlayerBuild(SBPlayer player) {
        if (getWorldName().equals(player.getPrivateIsland().getWorldName())) {
            return true;
        }
        return false;
    }

    public NBTTagCompound getWorldData() {
        for (LivingEntity livingEntity : getWorld().getLivingEntities()) {
            if (livingEntity instanceof ArmorStand) {
                ItemStack itemInHand = ((ArmorStand) livingEntity).getItemInHand();
                if (itemInHand == null || itemInHand.getType() == Material.AIR) continue;
                net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(itemInHand);
                if (itemStack == null) continue;
                if (itemStack.getTag() == null) itemStack.setTag(new NBTTagCompound());
                if (itemStack.getTag().hasKey("WorldDataStorage")) {
                    skyBlock.getFeaturesManager().getFeature(ClientSideEntityController.class).removeEntity(livingEntity.getEntityId());
                    return itemStack.getTag().getCompound("WorldDataStorage");
                }
            }
        }
        ArmorStand armorStand = getWorld().spawn(new Location(getWorld(), 0, 1, 0), ArmorStand.class);
        armorStand.setMarker(true);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        skyBlock.getFeaturesManager().getFeature(ClientSideEntityController.class).removeEntity(armorStand.getEntityId());
        ItemStack itemInHand = new ItemStack(Material.STONE);
        armorStand.setItemInHand(itemInHand);
        net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(itemInHand);
        if (itemStack.getTag() == null) itemStack.setTag(new NBTTagCompound());
        return itemStack.getTag().getCompound("WorldDataStorage");
    }

    public void saveWorldData(NBTTagCompound tagCompound) {
        for (LivingEntity livingEntity : getWorld().getLivingEntities()) {
            if (livingEntity instanceof ArmorStand) {
                ItemStack itemInHand = ((ArmorStand) livingEntity).getItemInHand();
                net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(itemInHand);
                if (itemStack.getTag() == null) itemStack.setTag(new NBTTagCompound());
                if (itemStack.getTag().hasKey("WorldDataStorage")) {
                    skyBlock.getFeaturesManager().getFeature(ClientSideEntityController.class).removeEntity(livingEntity.getEntityId());
                    itemStack.getTag().set("WorldDataStorage", tagCompound);
                    return;
                }
            }
        }
        ArmorStand armorStand = getWorld().spawn(new Location(getWorld(), 0, 1, 0), ArmorStand.class);
        armorStand.setMarker(true);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        skyBlock.getFeaturesManager().getFeature(ClientSideEntityController.class).removeEntity(armorStand.getEntityId());
        ItemStack itemInHand = new ItemStack(Material.STONE);
        armorStand.setItemInHand(itemInHand);
        net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(itemInHand);
        if (itemStack.getTag() == null) itemStack.setTag(new NBTTagCompound());
        itemStack.getTag().set("WorldDataStorage", tagCompound);
        itemInHand = CraftItemStack.asCraftMirror(itemStack);
        armorStand.setItemInHand(itemInHand);
    }

    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public <T> T getBlockData(Location location, Class<T> clazz) {
        NBTTagCompound worldData = getWorldData();
        NBTTagCompound blockDataStorage = worldData.getCompound("BlockDataStorage");
        String string = blockDataStorage.getString(location.toVector().toString());
        RawBsonDocument rawBsonDocument = new RawBsonDocument(string.getBytes(StandardCharsets.UTF_8));
        return gson.fromJson(rawBsonDocument.toJson(), clazz);
    }

    public void setBlockData(Location location, Object data) {
        NBTTagCompound worldData = getWorldData();
        NBTTagCompound blockDataStorage = worldData.getCompound("BlockDataStorage");
        RawBsonDocument rawBsonDocument = RawBsonDocument.parse(gson.toJson(data));
        ByteBuf byteBuffer = rawBsonDocument.getByteBuffer();
        blockDataStorage.setString(location.toVector().toString(), new String(byteBuffer.array()));
        worldData.set("BlockDataStorage", blockDataStorage);
        saveWorldData(worldData);
    }

    public void addNPC(Vector vector, SBNamespace npcNamespace) {
        NBTTagCompound worldData = getWorldData();
        spawnNpc(vector, npcNamespace);
        NBTTagCompound npcData = new NBTTagCompound();
        npcData.setDouble("x", vector.getX());
        npcData.setDouble("y", vector.getY());
        npcData.setDouble("z", vector.getZ());
        npcData.setString("type", npcNamespace.toString());
        NBTTagList npcList = worldData.getList("NpcList", 10);
        npcList.add(npcData);
        worldData.set("NpcList", npcList);
        saveWorldData(worldData);
    }

    public boolean removeNpc(AbstractNPC<?> npc) {
        NBTTagCompound worldData = getWorldData();
        npc.destroy();
        NBTTagList npcList = worldData.getList("NpcList", 10);
        for (int i = 0; i < npcList.size(); i++) {
            NBTTagCompound npcData = npcList.get(i);
            AbstractNPC.NPCRegistry npcRegistry = AbstractNPC.getNPCRegistry(npc.getClass());
            if (npcData.getString("type").equals(new SBNamespace(npcRegistry.addonName(), npcRegistry.namespace()).toString())) {
                if (
                        Math.abs(npcData.getDouble("x") - npc.asCraftCopy().getLocation().getX()) < 1E-3 &&
                        Math.abs(npcData.getDouble("y") - npc.asCraftCopy().getLocation().getY()) < 1E-3 &&
                        Math.abs(npcData.getDouble("z") - npc.asCraftCopy().getLocation().getZ()) < 1E-3
                ) {
                    npcList.a(i);
                    worldData.set("NpcList", npcList);
                    saveWorldData(worldData);
                    return true;
                }
            }
        }
        return false;
    }

    @SneakyThrows
    protected void spawnNpc(Vector vector, SBNamespace namespace) {
        AbstractNPC<?> npc = skyBlock.getNpcManager().getNpcList().get(namespace).getConstructor(SkyBlock.class).newInstance(skyBlock);
        npc.create(getWorld());
        npc.getNpcEntity().setPosition(vector.getX(), vector.getY(), vector.getZ());
        npc.updatePosition();
    }

}
