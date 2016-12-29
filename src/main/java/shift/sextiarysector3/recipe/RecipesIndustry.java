package shift.sextiarysector3.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector3.SSBlocks;

public class RecipesIndustry {

    public static void addRecipes(CraftingManager p_77608_1_) {

        //plasticブロック
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.plasticBlock, 1),
                new Object[] {
                        "xxx", "xxx", "xxx",
                        'x', "plastic"
                }));

    }

}