package me.fan87.commonplugin.gui.impl.recipe;

import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;

public class GuiRecipeTypesMenu extends Gui {

    private final SBPlayer player;

    public GuiRecipeTypesMenu(SBPlayer player) {
        super("Recipe Book", 6);
        this.player = player;
    }

    @Override
    public void init() {
        int index = -1;
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        List<SBCustomItem> allUnlockableCraftingRecipes = player.getSkyBlock().getRecipesManager().getAllUnlockableCraftingRecipes();
        {
            int unlocked = (int) allUnlockableCraftingRecipes.stream().filter(player::isRecipeUnlocked).count();
            int all = allUnlockableCraftingRecipes.size();
            set(5, 1, new GuiItem(new ItemStackBuilder(Material.BOOK)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Recipe Book")
                    .addLore(ChatColor.GRAY + "Through your adventure, you will unlock recipes for all kinds of special items! You can view how to craft items here.", true)
                    .addLore("")
                    .addLore(NumberUtils.getPercentageText(ChatColor.GRAY + "Recipe Book Unlocked", unlocked, all))
                    .addLore(NumberUtils.generateProgressBar(unlocked, all))
                    .build()));
        }
        for (SBCustomItem.RecipeCategory value : SBCustomItem.RecipeCategory.values()) {
            index++;
            int x = index % 5 + 3;
            int y = index / 5 + 3;
            int unlocked = (int) allUnlockableCraftingRecipes.stream().filter(recipe -> recipe.getRecipeCategory() == value && player.isRecipeUnlocked(recipe)).count();
            int all = allUnlockableCraftingRecipes.size();
            set(x, y, new GuiItem(new ItemStackBuilder(value.getIcon(), value.getDurability())
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + value.getName() + " Recipes")
                    .addLore(ChatColor.GRAY + String.format("View all of the %s Recipes that you have unlocked!", value.getName()), true)
                    .addLore("")
                    .addLore(NumberUtils.getPercentageText(ChatColor.GRAY + "Recipes Unlocked", unlocked, all))
                    .addLore(NumberUtils.generateProgressBar(unlocked, all))
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to view!")
                    .build(), event -> {
                new GuiRecipesMenu(player, value, 1).open(player.getPlayer());
            }));
        }
        renderGoBackItems(new GuiSkyBlockMenu(player), player.getPlayer());
    }
}
