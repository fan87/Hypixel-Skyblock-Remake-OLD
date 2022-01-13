package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.utils.SBNamespace;
import me.fan87.commonplugin.world.SBWorld;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CmdTest extends SBCommand {

    public CmdTest() {
        super("a", "Just a test command", "skyblock.debug", "/testa");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(((Player) sender).getWorld().getName());
        if (world == null) {
            sender.sendMessage("null");
            return true;
        }
        NBTTagList npcList = world.getWorldData().getList("NpcList", 10);
        for (int i = 0; i < npcList.size(); i++) {
            NBTTagCompound nbtData = npcList.get(i);
            sender.sendMessage(new Vector(nbtData.getDouble("x"), nbtData.getDouble("y"), nbtData.getDouble("z")).toString() + " / " + SBNamespace.fromString(nbtData.getString("type")));
        }
        return true;
    }
}
