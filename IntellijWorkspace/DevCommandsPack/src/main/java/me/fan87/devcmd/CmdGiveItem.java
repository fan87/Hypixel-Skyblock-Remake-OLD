package me.fan87.devcmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBItems;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdGiveItem extends SBCommand {


    public CmdGiveItem() {
        super("giveitem", "Gives a skyblock item to you", "skyblock.admin", "/giveitem <Item Name>");
    }

    @Override
    protected boolean onCommand(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length != 1) return false;
            SBCustomItem item = SBItems.getItem(strings[0]);
            if (item != null) {
                SBItemStack itemStack = new SBItemStack(item);
                player.getInventory().addItem(itemStack.getItemStack());
            }
            return true;
        }
        return false;
    }


}
