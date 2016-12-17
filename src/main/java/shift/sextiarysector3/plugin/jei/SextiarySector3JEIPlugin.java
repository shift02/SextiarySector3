package shift.sextiarysector3.plugin.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector3.SSBlocks;

@JEIPlugin
public class SextiarySector3JEIPlugin implements IModPlugin {

    @Override
    public void register(IModRegistry registry) {

        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registry.addRecipeCategories(new CauldronRecipeCategory(guiHelper));
        registry.addRecipeCategories(new SpileRecipeCategory(guiHelper));

        registry.addRecipeHandlers(new CauldronRecipeRecipeHandler());
        registry.addRecipeHandlers(new SpileRecipeRecipeHandler());

        registry.addRecipeCategoryCraftingItem(new ItemStack(Items.CAULDRON), CauldronRecipeCategory.CAULDRON);
        registry.addRecipeCategoryCraftingItem(new ItemStack(SSBlocks.spile), SpileRecipeCategory.SPILE);

        registry.addRecipes(CauldronRecipeWrapper.getCauldronRecipes(jeiHelpers));
        registry.addRecipes(SpileRecipeWrapper.getSpileRecipes(jeiHelpers));

    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
