package shift.sextiarysector3.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;

public class RecipesCooking {

    public static void addRecipes(CraftingManager p_77608_1_) {

        //メイプルケーキ
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.mapleCake, 1),
                new Object[] {
                        "xxx", "yzy", "aaa",
                        'x', SSItems.mapleBottle,
                        'y', Items.SUGAR,
                        'z', "egg",
                        'a', "cropWheat"
                }));

    }

}
