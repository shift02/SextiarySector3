package shift.sextiarysector3.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector3.SSBlocks;

public class RecipesForestry {

	public static void addRecipes(CraftingManager p_77608_1_) {

		//スパイル
		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.spile, 1),
				new Object[] {
						"xxx",
						'x', "ingotIron"
				}));

		//木材
		p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(Blocks.PLANKS, 4, 2),
				new Object[] {
						SSBlocks.rubberLog
				}));

	}

}
