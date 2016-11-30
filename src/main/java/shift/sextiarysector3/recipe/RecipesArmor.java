package shift.sextiarysector3.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector3.SSItems;

public class RecipesArmor {

    public static void addRecipes(CraftingManager p_77608_1_) {

        String[][] recipePatterns = new String[][] { { "XXX", "X X" }, { "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" }, { "X X", "X X" } };
        Object[] materialItems = new String[] {
                "ingotCopper",
                "ingotSilver",
                "gemOrichalcum"
        };

        ItemStack[][] recipeItems = new ItemStack[][] {
                { new ItemStack(SSItems.copperHelmet), new ItemStack(SSItems.copperChestplate), new ItemStack(SSItems.copperLeggings), new ItemStack(SSItems.copperBoots) },
                { new ItemStack(SSItems.silverHelmet), new ItemStack(SSItems.silverChestplate), new ItemStack(SSItems.silverLeggings), new ItemStack(SSItems.silverBoots) },
                { new ItemStack(SSItems.orichalcumHelmet), new ItemStack(SSItems.orichalcumChestplate), new ItemStack(SSItems.orichalcumLeggings), new ItemStack(SSItems.orichalcumBoots) }
        };

        for (int i = 0; i < materialItems.length; ++i) {
            Object object = materialItems[i];

            for (int j = 0; j < recipeItems[i].length; ++j) {
                ItemStack item = recipeItems[i][j];
                p_77608_1_.getRecipeList().add(new ShapedOreRecipe(item, new Object[] { recipePatterns[j], 'X', object }));
            }

        }

    }

}
