package shift.sextiarysector3.plugin.jei;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector3.SSRecipes;
import shift.sextiarysector3.recipe.template.RecipeCauldron;

public class CauldronRecipeWrapper extends BlankRecipeWrapper {

    private final List<ItemStack> input;
    private final List<FluidStack> fluid;
    private final List<ItemStack> outputs;

    public CauldronRecipeWrapper(ItemStack input, FluidStack fluid, ItemStack output) {

        this.input = Collections.singletonList(input);
        this.fluid = Collections.singletonList(fluid);
        this.outputs = Collections.singletonList(output);
    }

    public List<ItemStack> getInputs() {
        return input;
    }

    @Override
    public List<FluidStack> getFluidInputs() {
        return fluid;
    }

    public List<ItemStack> getOutputs() {
        return outputs;
    }

    /** JEIに変換 */
    public static List<CauldronRecipeWrapper> getCauldronRecipes(IJeiHelpers helpers) {

        IStackHelper stackHelper = helpers.getStackHelper();
        List<RecipeCauldron> list = SSRecipes.cauldronRecipes;

        List<CauldronRecipeWrapper> recipes = Lists.newArrayList();

        for (RecipeCauldron rc : list) {

            recipes.add(new CauldronRecipeWrapper(rc.input, rc.fluid, rc.output));

        }

        return recipes;
    }

}
