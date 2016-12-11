package shift.sextiarysector3;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.crafting.CraftingManager;
import shift.sextiarysector3.recipe.RecipesArmor;
import shift.sextiarysector3.recipe.RecipesCauldron;
import shift.sextiarysector3.recipe.RecipesCore;
import shift.sextiarysector3.recipe.RecipesForestry;
import shift.sextiarysector3.recipe.RecipesFurnace;
import shift.sextiarysector3.recipe.RecipesMining;
import shift.sextiarysector3.recipe.RecipesTool;
import shift.sextiarysector3.recipe.template.RecipeCauldron;

public class SSRecipes {

    public static List<RecipeCauldron> cauldronRecipes = Lists.newArrayList();

    public static void initRecipes() {

        RecipesFurnace.addRecipes();

        CraftingManager m = CraftingManager.getInstance();

        RecipesCore.addRecipes(m);
        RecipesArmor.addRecipes(m);
        RecipesForestry.addRecipes(m);
        RecipesMining.addRecipes(m);

        RecipesTool.addRecipes(m);

        RecipesCauldron.addRecipes(cauldronRecipes);

    }

}
