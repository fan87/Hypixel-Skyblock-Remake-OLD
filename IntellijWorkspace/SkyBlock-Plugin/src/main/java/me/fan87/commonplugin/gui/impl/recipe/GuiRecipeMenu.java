package me.fan87.commonplugin.gui.impl.recipe;

import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.recipes.SBRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GuiRecipeMenu extends Gui {

    private final SBRecipe recipe;
    private final SBPlayer player;
    private final Gui previousPage;

    public GuiRecipeMenu(SBRecipe recipe, SBPlayer player, Gui previousPage) {
        super(recipe.getOutputType().getDisplayName() + " Recipe", 6);
        this.recipe = recipe;
        this.player = player;
        this.previousPage = previousPage;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        if (recipe.getType() == SBRecipe.RecipeType.CRAFTING_TABLE) {
            fill(2, 2, 4, 4, new GuiItem(new ItemStack(Material.AIR)));
            renderGoBackItems(previousPage==null?new GuiSkyBlockMenu(player):previousPage, player.getPlayer());
        }
    }
}
