package shift.sextiarysector3.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;

public class RecipesIndustry {

    public static void addRecipes(CraftingManager p_77608_1_) {

        //plasticブロック
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.plasticBlock, 1),
                new Object[] {
                        "xxx", "xxx", "xxx",
                        'x', "plastic"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.rubberBlock, 1),
                new Object[] {
                        "xxx", "xxx", "xxx",
                        'x', "rubber"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.rubberGloves, 1),
                new Object[] {
                        " x", "x ",
                        'x', "rubber"
                }));

        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSItems.blueGel, 1),
                new Object[] {
                        "dustBluestone",
                        "slimeball"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.woodShaft, 4),
                new Object[] {
                        "xzx", "xyx", "xzx",
                        'x', "plankWood",
                        'y', "dustBluestone",
                        'z', SSItems.blueGel
                }));

    }

}
