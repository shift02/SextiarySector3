package shift.sextiarysector3.plugin.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

public abstract class RecipeCategoryBase<T extends IRecipeWrapper> extends BlankRecipeCategory<T> {

    public RecipeCategoryBase(IGuiHelper guiHelper) {

    }

}
