# Hypixel SkyBlock Remake / Recreate

Hypixel SkyBlock Full Remake Project, Including every single item and feature.<br>WIP

## Code time
fan87 - 9 hours

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
#### Obfuscation
No. You can't obfuscate the plugin without modifying any code. I mean it's open source, obfuscating it makes no sense at all.
#### Technical Issues
If you have any technical issues, either DM me on Discord (Or not if I turned my DM off) or create a new github issue and add tag "Question". If you are a developer and know what you are doing, it's 80% my fault since most stuff are un-hardtested (which means I don't test every single stuff, for example: I don't test every single recipe, I mean no one does but you get the idea, so there might be some bugs)
#### Using
Hypixel Inc. has the permission to take your server down,
or even take the repository down. 
We do not suggest you to make money with this plugin 
by hosting a server,
and we do not suggest you to make the server public, 
just have fun with your friends.
You can make money by coding addons, but don't be a d*ck,
the plugin is not yours, it's open source, and the idea is
made by Hypixel, not by either you or me.
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
