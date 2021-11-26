package me.fan87.commonplugin.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.LoreUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SBCustomItem {

    protected final SkyBlock skyBlock;

    @Getter
    private final String namespace;

    @Getter
    private final String displayName;

    @Getter
    private final Material material;

    @Getter
    private final String description;


    public SBCustomItem(String namespace, String displayName, String description, Material material, SkyBlock skyBlock) {
        this.namespace = namespace;
        this.material = material;
        this.displayName = displayName;
        this.description = description;
        this.skyBlock = skyBlock;
    }

    public SBCustomItem(String namespace, String displayName, Material material, SkyBlock skyBlock) {
        this(namespace, displayName, "", material, skyBlock);
    }

    public void updatePlayerStats(SBPlayer player, int inventoryIndex) {

    }

    public List<String> getLores(SBItemStack itemStack) {
        if (itemStack.getType().getItem() != this) {
            return new ArrayList<>();
        }
        return getLores();
    }

    public List<String> getLores() {

        List<String> out = new ArrayList<>();
        out.addAll(LoreUtils.splitLoreForLine("§7" + getDescription()));
        return out;
    }



    public boolean activateForSlot(int slot, SBPlayer player) {
        return true;
    }

    public ItemStack newItemStack() {
        ItemStack itemStack = new ItemStack(getMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(getDisplayName());
        itemMeta.setLore(getLores());
        itemStack.setItemMeta(itemMeta);
        NBTItem nbt = new NBTItem(itemStack, true);
        NBTCompound extraAttributes = nbt.addCompound("ExtraAttributes");
        extraAttributes.setString("id", getNamespace());
        applyExtraAttributes(extraAttributes);
        return itemStack;
    }

    public void applyExtraAttributes(NBTCompound compound) {

    }

}
