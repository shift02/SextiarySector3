package shift.sextiarysector3.plugin.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class SpileRecipeRecipeHandler implements IRecipeHandler<SpileRecipeWrapper> {

    @Override
    public Class<SpileRecipeWrapper> getRecipeClass() {
        return SpileRecipeWrapper.class;
    }

    @Override
    public String getRecipeCategoryUid() {
        return SpileRecipeCategory.SPILE;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(SpileRecipeWrapper recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(SpileRecipeWrapper recipe) {
        return true;
    }

    @Override
    public String getRecipeCategoryUid(SpileRecipeWrapper recipe) {
        return getRecipeCategoryUid();
    }

}