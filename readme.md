# Hypixel SkyBlock Remake / Recreate

Hypixel SkyBlock Full Remake Project, Including every single item and feature.<br>WIP

## Coding Style

Nothing is hard coded (except some basic systems like world management, players management etc.), means you can create your own item or block etc. with a plugin, and we have our own event system, which means you can cancel stuff like critical, we are also using features system, you can read the document(WIP) to understand it. We also like to use [Lombok](https://projectlombok.org/), that's all.

## Contribute

Contact me on Discord: `fan87#5283`. I'm not a Hypixel SkyBlock pro, so I might missed something.

## Document
### Warning
This is not as easy as Spigot plugins, 
there are some advanced syntax in the addon api, 
you need to understand java (JVM not included) in order to code addons, 
for example: the plugin is object-oriented, 
means you need to understand it to code it, 
or it will be a mess and everything will go wrong.
### Addons API
To create an addon, you need to first obtain the instance of `SkyBlock`. To do it, you can type something like this in your main class:
```java
SkyBlock skyBlock = SkyBlock.registerPlugin("Example Addon", "exampleaddon", this);
```
With this instance, you can start getting stuff like `PlayersManager`, `WorldsManager` etc., and you can start doing stuff.<br>
Now I want to talk about the addon api, we got a command system, if you want to register a command, you need to use type something like this:
```java
skyBlock.getCommandsManager().registerCommand("exampleaddon", new CmdExample());
```
Note that `exampleaddon` in this line must match the addon namespace specified in `registerPlugin` or it will throw a `UnknownAddonError`<br>
In `CmdExample`, you want something like this:
```java
public class CmdExample extends SBCommand {
    public CmdExample() {
        super("example", "Just an example command", "" /*Permission*/, "/example <Text>", /* Aliases */ "ex", "e");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (args.length != 1) {
            return false;
        }
        sender.send("Hello World!");
        return true;
    }
}
```
First, don't always return `true` like what you do in every bukkit / spigot plugin tutorial, it's very stupid. Just return false if the usage is invalid, the skyblock plugin will send a hypixel-like invalid usage message.
<br>
Second, **YOU DON'T NEED TO REGISTER THE COMMAND IN PLUGIN.YML** .
