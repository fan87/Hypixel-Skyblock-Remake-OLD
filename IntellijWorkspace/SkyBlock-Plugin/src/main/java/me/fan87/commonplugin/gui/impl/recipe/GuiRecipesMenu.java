package me.fan87.commonplugin.gui.impl.recipe;

import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.gui.impl.types.GuiList;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.recipes.SBRecipe;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuiRecipesMenu extends GuiList {

    private final SBPlayer player;
    private final SBCustomItem.RecipeCategory recipeCategory;

    public GuiRecipesMenu(SBPlayer player, SBCustomItem.RecipeCategory recipeCategory, int page) {
        super(recipeCategory.getName(), page, new ArrayList<>());
        this.player = player;
        this.recipeCategory = recipeCategory;
    }



    @Override
    public void init() {
        List<SBCustomItem> allUnlockableCraftingRecipes = player.getSkyBlock().getRecipesManager().getAllUnlockableCraftingRecipes();
        int unlocked = (int) allUnlockableCraftingRecipes.stream().filter(recipe -> recipe.getRecipeCategory() == recipeCategory && player.isRecipeUnlocked(recipe)).count();
        int all = allUnlockableCraftingRecipes.size();
        fillBorder(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        set(5, 1, new GuiItem(new ItemStackBuilder(recipeCategory.getIcon(), recipeCategory.getDurability())
                .addAllItemFlags()
                .setDisplayName(ChatColor.GREEN + recipeCategory.getName() + " Recipes")
                .addLore(ChatColor.GRAY + String.format("View all of the %s Recipes that you have unlocked!", recipeCategory.getName()), true)
                .addLore("")
                .addLore(NumberUtils.getPercentageText(ChatColor.GRAY + "Recipes Unlocked", unlocked, all))
                .addLore(NumberUtils.generateProgressBar(unlocked, all))
                .build()));
        for (SBRecipe recipe : player.getSkyBlock().getRecipesManager().getCraftingRecipes().stream().sorted((o1, o2) -> {
            boolean o2Unlocked = player.isRecipeUnlocked(o2);
            boolean o1Unlocked = player.isRecipeUnlocked(o1);
            if (o1Unlocked && !o2Unlocked) {
                return 1;
            }
            if (o2Unlocked && !o1Unlocked) {
                return -1;
            }
            return o1.getOutputType().getDisplayName().compareTo(o2.getOutputType().getDisplayName());
        }).filter(r -> r.getOutputType().getRecipeCategory() == recipeCategory).collect(Collectors.toList())) {
            ItemStackBuilder itemStackBuilder = new ItemStackBuilder(Material.INK_SACK, 8)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.RED + "???")
                    .addLore(ChatColor.GRAY + "Progress through your item collections and explore the world to unlock new trades!", true);
            if (!player.isRecipeUnlocked(recipe)) {
                SBCollection sbCollection = recipe.relatedCollection(player);
                if (sbCollection != null) {
                    itemStackBuilder.addLore(ChatColor.GRAY + String.format("Unlocked in the %s Collection", sbCollection.getDisplayName()), true);
                }
                getContents().add(new GuiItem(itemStackBuilder.build()));
            } else {
                getContents().add(new GuiItem(new ItemStackBuilder(new SBItemStack(recipe.getOutputType().newItemStack()).getDisplayItemStack())
                        .addLore("")
                        .addLore(ChatColor.YELLOW + "Click to view recipe!")
                        .build(), event -> {
                    new GuiRecipeMenu(recipe, player, new GuiRecipesMenu(player, recipeCategory, getCurrentPage())).open(player.getPlayer());
                }));
            }
        }
        putItems();
        renderGoBackItems(new GuiRecipeTypesMenu(player), player.getPlayer());
    }

    @Override
    public void goPage(int page) {
        new GuiRecipesMenu(player, recipeCategory, page).open(player.getPlayer());
    }
}
