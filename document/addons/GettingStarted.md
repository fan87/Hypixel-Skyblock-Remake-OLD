 

> :warning: **Warning** If you don't even know how to add a dependency, what is `class` for in Java (including some keywords like extends, implement), this tutorial is not for you. I'm not going to teach you java in this document, and it is not a tutorial, please learn Java basics before reading this document without any tutorial. Yes you can watch/read tutorials, but I cannot make sure that you will understand everything and code your own addon by only reading this document 


## Getting Started
To start working on addons, you need a normal spigot plugin as base, since addon is just a spigot plugin that uses SkyBlock API.

### plugin.yml
You need to add the plugin name as dependency in plugins.yml, while I'm writing this document, the plugin name is `HypixelSkyBlock-Common`, so you add that into dependencies list, and add the jar to module/project dependencies list.

Now your plugin.yml should looks something like this:

```yaml
name: ExampleAddon
main: com.example.exampleaddon.ExampleAddon
version: 1.0
depend:
  - HypixelSkyBlock-Common
```



> :information_source: **Note** If the plugin name is outdated, please open an GitHub Issue to tell us! You can check it in main plugin's `plugin.yml`

### Main class

You don't need to change anything, but you need to somehow get the SkyBlock instance by registering your addon. If you really want to, you can force load the addon, but don't do that, it's pointless. By registering the addon, the server owner / admins will be able to see items/enchantments/commands etc. that you've registered. The addon name must not cointains `::` (Since namespace formatting is `addonName::name`, just like vanilla's  `minecraft:bedrock`, but in this system hypixel uses  `:`  in item name)

To get the instance, you need to do something like this:

```java
SkyBlock skyBlock = SkyBlock.registerAddon("Basic Commands Pack", "basiccmdpack", this);
```

