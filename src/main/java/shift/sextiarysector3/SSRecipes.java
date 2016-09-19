package shift.sextiarysector3;

import net.minecraft.item.crafting.CraftingManager;
import shift.sextiarysector3.recipe.RecipesCore;
import shift.sextiarysector3.recipe.RecipesForestry;
import shift.sextiarysector3.recipe.RecipesFurnace;
import shift.sextiarysector3.recipe.RecipesMining;

public class SSRecipes {

	public static void initRecipes() {

		RecipesFurnace.addRecipes();

		CraftingManager m = CraftingManager.getInstance();

		RecipesCore.addRecipes(m);
		RecipesForestry.addRecipes(m);
		RecipesMining.addRecipes(m);

	}

}
