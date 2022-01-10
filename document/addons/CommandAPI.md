 

## Command API

In this document, I'm going to explain our commands system. If you want to code a SkyBlock Command, you can either use Bukkit API (Not recommended) or use our command API. Our command API is basically same as Bukkit's, but users will be able to see what you've registered with `/addons` commands, since it's a SkyBlock Addon.
If you don't even know how to start coding an addon, please check our [Getting Started](https://github.com/fan87/Hypixel-Skyblock-Remake/blob/master/document/addons/GettingStarted.md) document. Now let's get right into it!

### Command Main Class

Here's a example command class:

```java
import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdDmgPlayer extends SBCommand {
    public CmdDmgPlayer() {
        super("damage",  // Note 1
              "Damage the target player",  // Note 2
              "skyblock.admin",  // Note 3
              "/damage <player name> <amount>",  // Note 4
              "dmg", "d");  // Note 5
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length !=2) return false;  // Note 6

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])){

                loadedPlayer.getPlayer().damage(Double.valueOf(args[1])/5d);
                sender.sendMessage(ChatColor.GREEN + "Damaged " + ChatColor.YELLOW + loadedPlayer.getPlayer().getName());
                return true;

            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid player");
        return true;
    }
}

```

Note 1: The command name, which is how the command start, in this case: `damage` cuz `/damage` will be how you start the command<br>Note 2: The description of the command<br>Note 3: Permission required, you can type `""` as permission, then you won't need any permission to type the command<br>Note 4: Usage of the command, not that it won't affect anything other than `/addons` message and the message will be sent if you return false in onCommand method<br>Note 5: Aliases of the command<br>Note 6: Returns false if usage is incorrect, instead of vanilla bukkit wrong usage message, it will send a Hypixel like incorrect usage message, so don't return true and say something like "Incorrect usage", that's pointless 

### Registering the command

To register the command, you need to get the SkyBlock instance first (Check [Getting Started](https://github.com/fan87/Hypixel-Skyblock-Remake/blob/master/document/addons/GettingStarted.md)):

```java
skyBlock.getCommandsManager().registerCommand("examplepack", new CmdDmgPlayer());
```

Then you are ready to go! Note that `examplepack` must be same as your addon namespace (Check [Getting Started](https://github.com/fan87/Hypixel-Skyblock-Remake/blob/master/document/addons/GettingStarted.md))

>  :warning: **Warning** you don't need to register it in `plugin.yml`, since command name and stuff are already there
