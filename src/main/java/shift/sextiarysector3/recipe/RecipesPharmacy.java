package shift.sextiarysector3.recipe;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector3.SSItems;
import shift.sextiarysector3.recipe.template.SSBrewingRecipe;

public class RecipesPharmacy {

    public static void addRecipes(CraftingManager p_77608_1_) {

        BrewingRecipeRegistry.addRecipe(new SSBrewingRecipe());

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.shiningFlower, 1),
                new Object[] {
                        "xxx", "xyx", "xxx",
                        'y', new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.SUNFLOWER.getMeta()),
                        'x', "nuggetSilver"
                }));

    }

}
