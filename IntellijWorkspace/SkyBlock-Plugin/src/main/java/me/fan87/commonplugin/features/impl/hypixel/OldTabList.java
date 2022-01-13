package me.fan87.commonplugin.features.impl.hypixel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.SneakyThrows;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.SBPlayerJoinEvent;
import me.fan87.commonplugin.features.SBFeature;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OldTabList extends SBFeature {
    public OldTabList() {
        super("Old Tab List", "The return of old tab list (Lazy to make new one lol)", false);
    }

    @SerializedName("tabListServerIP")
    @Expose
    private String tabListServerIP = "MC.HYPIXEL.NET";

    @SerializedName("footerText")
    @Expose
    private String footerText = ChatColor.GREEN + "Ranks, Boosters & MORE! " + ChatColor.RED + ChatColor.BOLD + "STORE.HYPIXEL.NET";

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    @SneakyThrows
    public void onJoin(SBPlayerJoinEvent event) {
        ChatComponentText header = new ChatComponentText(ChatColor.AQUA + "You are playing on " + ChatColor.YELLOW + ChatColor.BOLD + tabListServerIP + "\n");
        Date date = new Date(System.currentTimeMillis());
        header.addSibling(new ChatComponentText(ChatColor.GRAY + new SimpleDateFormat("dd/MM/yy").format(date) + "  " + ChatColor.DARK_GRAY + skyBlock.getConfigsManager().config.serverId + event.getPlayer().getWorld().getWorldID() + "\n"));
        ChatComponentText footer = new ChatComponentText("\n" + ChatColor.GREEN + ChatColor.BOLD + "Active Effects");
        footer.addSibling(new ChatComponentText("\n" + ChatColor.GRAY + "No effects active. Drink Potions or splash\n" + ChatColor.GRAY + "them on the ground to buff yourself!"))
                .addSibling(new ChatComponentText("\n\n" + footerText));
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(header);
        Field b = PacketPlayOutPlayerListHeaderFooter.class.getDeclaredField("b");
        b.setAccessible(true);
        b.set(packet, footer);
        event.getPlayer().sendPacket(packet);
    }

}
