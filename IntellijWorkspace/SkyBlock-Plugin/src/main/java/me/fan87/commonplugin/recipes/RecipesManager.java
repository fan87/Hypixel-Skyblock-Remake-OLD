package me.fan87.commonplugin.recipes;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.recipes.impl.SBShapedRecipe;
import me.fan87.commonplugin.recipes.impl.SBShapelessRecipe;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipesManager {

    private final SkyBlock skyBlock;
    @Getter
    private final List<SBRecipe> craftingRecipes = new ArrayList<>();

    public RecipesManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        Iterator<Recipe> recipeIterator = skyBlock.getServer().recipeIterator();
        List<Recipe> rs = new ArrayList<>();
        while (recipeIterator.hasNext()) {
            Recipe next = recipeIterator.next();
            rs.add(next);
        }
        for (Recipe r : rs) {
            if (r instanceof ShapedRecipe) {
                craftingRecipes.add(new SBShapedRecipe(((ShapedRecipe) r), false));
            }
        }
        for (Recipe r : rs) {
            if (r instanceof ShapelessRecipe) {
                try {craftingRecipes.add(new SBShapelessRecipe(((ShapelessRecipe) r)));} catch (Exception ignored) {}
            }
        }
    }

    public List<SBCustomItem> getAllUnlockableCraftingRecipes() {
        List<SBCustomItem> out = new ArrayList<>();
        for (SBRecipe craftingRecipe : craftingRecipes) {
            if (craftingRecipe.isUnlockable()) {
                if (craftingRecipe.getOutputType() != null) {
                    if (!out.contains(craftingRecipe.getOutputType())) {
                        out.add(craftingRecipe.getOutputType());
                    }
                }

            }
        }
        return out;
    }

}
