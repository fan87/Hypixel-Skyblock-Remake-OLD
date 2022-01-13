package me.fan87.commonplugin.world.privateisland;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.features.impl.api.ClientSideEntityController;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.ZipUtils;
import me.fan87.commonplugin.utils.world.VoidGenerator;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.apache.commons.io.FileUtils;
import org.bson.types.ObjectId;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.UUID;

public class PrivateIsland {

    private SBPlayer player;
    private SkyBlock skyBlock;
    public PrivateIsland() {

    }

    public void init(SBPlayer player) {

        this.player = player;
        this.skyBlock = player.getSkyBlock();
    }

    @Getter
    @JsonProperty("worldId")
    private String worldId;

    @Getter
    private String worldName;

    @Getter
    @Setter
    @JsonProperty("spawnYaw")
    private float spawnYaw;

    @Getter
    @Setter
    @JsonProperty("spawnX")
    private int spawnX;

    @Getter
    @Setter
    @JsonProperty("spawnY")
    private int spawnY;

    @Getter
    @Setter
    @JsonProperty("spawnZ")
    private int spawnZ;

    @SneakyThrows
    public World load() {
        if (worldName != null) {
            World world = skyBlock.getServer().getWorld(worldName);
            if (world != null) return world;
        }
        worldName = "PI-" + UUID.randomUUID();
        if (worldId != null) {
            File file = new File(worldName);
            try {
                if (file.exists()) file.delete();
                GridFSBucket bucket = GridFSBuckets.create(skyBlock.getDatabaseManager().getDatabase(), "worlds");
                ZipUtils.unzipFile(bucket.openDownloadStream(new ObjectId(worldId)), file);
                File uid = new File(file, "uid.dat");
                if (uid.exists()) uid.delete();
            } catch (Exception e) {
                e.printStackTrace();
                if (file.exists()) file.delete();
                file = new File(skyBlock.getDataFolder(), "privateisland");
                if (!file.exists()) {
                    System.err.println("Private Island World Not Found!");
                    System.exit(-1);
                    return null;
                }
                FileUtils.copyDirectory(file, new File(worldName));
            }
        } else {
            File file = new File(skyBlock.getDataFolder(), "privateisland");
            if (!file.exists()) {
                System.err.println("Private Island World Not Found!");
                System.exit(-1);
                return null;
            }
            FileUtils.copyDirectory(file, new File(worldName));
        }
        File file = new File(worldName);
        World world = new WorldCreator(worldName)
                .generateStructures(false)
                .generator(new VoidGenerator())
                .createWorld();
        getWorldData();
        return world;
    }

    @SneakyThrows
    public void save() {
        if (worldName != null) {
            skyBlock.getServer().getWorld(worldName).save();
            File file = new File(worldName);
            GridFSBucket bucket = GridFSBuckets.create(skyBlock.getDatabaseManager().getDatabase(), "worlds");
            GridFSUploadStream outputStream = bucket.openUploadStream("PI-" + UUID.randomUUID());
            if (worldId != null) {
                bucket.delete(new ObjectId(worldId));
            }
            ZipUtils.zipFile(file, outputStream);
            worldId = outputStream.getObjectId().toString();
        } else {
            load().save();
        }
    }

    @SneakyThrows
    public void unload() {
        if (worldName != null) {
            skyBlock.getServer().unloadWorld(worldName, false);
            File file = new File(worldName);
            FileUtils.deleteDirectory(file);
            System.out.println("Successfully unloaded " + worldName);
        }
    }

    public World getWorld() {
        return skyBlock.getServer().getWorld(worldName);
    }

    public NBTTagCompound getWorldData() {
        for (LivingEntity livingEntity : getWorld().getLivingEntities()) {
            if (livingEntity instanceof ArmorStand) {
                ItemStack itemInHand = ((ArmorStand) livingEntity).getItemInHand();
                net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(itemInHand);
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

    public boolean isBlockManuallyPlaced(Location location) {
        NBTTagList manuallyPlaced1 = getWorldData().getList("manuallyPlaced", 10);
        for (int i = 0; i < manuallyPlaced1.size(); i++) {
            NBTTagCompound manuallyPlaced = manuallyPlaced1.get(i);
            if (manuallyPlaced.getInt("x") == location.getBlockX() && manuallyPlaced.getInt("y") == location.getBlockY() && manuallyPlaced.getInt("z") == location.getBlockZ()) {
                return true;
            }
        }
        return false;
    }

    public void setBlockManuallyPlaced(Location location, boolean value) {
        if (!value) {
            NBTTagCompound worldData = getWorldData();
            NBTTagList manuallyPlaced1 = worldData.getList("manuallyPlaced", 10);
            for (int i = 0; i < manuallyPlaced1.size(); i++) {
                NBTTagCompound manuallyPlaced = manuallyPlaced1.get(i);
                if (manuallyPlaced.getInt("x") == location.getBlockX() && manuallyPlaced.getInt("y") == location.getBlockY() && manuallyPlaced.getInt("z") == location.getBlockZ()) {
                    manuallyPlaced1.a(i);
                }
            }
        } else {
            NBTTagCompound worldData = getWorldData();
            NBTTagList manuallyPlaced1 = worldData.getList("manuallyPlaced", 10);
            for (int i = 0; i < manuallyPlaced1.size(); i++) {
                NBTTagCompound manuallyPlaced = manuallyPlaced1.get(i);
                if (manuallyPlaced.getInt("x") == location.getBlockX() && manuallyPlaced.getInt("y") == location.getBlockY() && manuallyPlaced.getInt("z") == location.getBlockZ()) {
                    return;
                }
            }
            NBTTagCompound comp = new NBTTagCompound();
            comp.setInt("x", location.getBlockX());
            comp.setInt("y", location.getBlockY());
            comp.setInt("z", location.getBlockZ());
            manuallyPlaced1.add(comp);
            worldData.set("manuallyPlaced", manuallyPlaced1);
            saveWorldData(worldData);
        }
    }

}
