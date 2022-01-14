package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.gui.impl.npc.GuiShop;
import me.fan87.commonplugin.item.SBItemVector;
import me.fan87.commonplugin.item.init.ItemsVANILLA;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.tradings.tradable.impl.CoinTradable;
import me.fan87.commonplugin.world.SBWorld;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTest extends SBCommand {

    public CmdTest() {
        super("a", "Just a test command", "skyblock.debug", "/testa");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        SBPlayer player = skyBlock.getPlayersManager().getPlayer((Player) sender);
        SBWorld world = player.getWorld();


        GuiShop shop = new GuiShop(player, "fan87's Test Shop",
                new GuiShop.ShopItem(new SBItemVector(ItemsVANILLA.DIAMOND_AXE, 1), new CoinTradable(600))
                );
        shop.open(player.getPlayer());

        return true;
    }
}
