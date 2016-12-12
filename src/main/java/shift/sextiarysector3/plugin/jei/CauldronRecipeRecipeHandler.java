package shift.sextiarysector3.plugin.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class CauldronRecipeRecipeHandler implements IRecipeHandler<CauldronRecipeWrapper> {

    @Override
    public Class<CauldronRecipeWrapper> getRecipeClass() {
        return CauldronRecipeWrapper.class;
    }

    @Override
    public String getRecipeCategoryUid() {
        return CauldronRecipeCategory.CAULDRON;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(CauldronRecipeWrapper recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(CauldronRecipeWrapper recipe) {
        return true;
    }

    @Override
    public String getRecipeCategoryUid(CauldronRecipeWrapper recipe) {
        return getRecipeCategoryUid();
    }

}