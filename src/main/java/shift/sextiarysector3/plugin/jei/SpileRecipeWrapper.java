package shift.sextiarysector3.plugin.jei;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector3.module.ModuleSap;
import shift.sextiarysector3.module.ModuleSap.SapEntry;

public class SpileRecipeWrapper extends BlankRecipeWrapper {

    private final List<ItemStack> input;
    private final List<FluidStack> fluid;

    public SpileRecipeWrapper(ItemStack input, FluidStack fluid) {

        this.input = Collections.singletonList(input);
        this.fluid = Collections.singletonList(fluid);
    }

    public List<ItemStack> getInputs() {
        return input;
    }

    @Override
    public List<FluidStack> getFluidOutputs() {
        return fluid;
    }

    /** JEIに変換 */
    public static List<SpileRecipeWrapper> getSpileRecipes(IJeiHelpers helpers) {

        IStackHelper stackHelper = helpers.getStackHelper();
        List<SapEntry> list = ModuleSap.getInstance().saps;

        List<SpileRecipeWrapper> recipes = Lists.newArrayList();

        for (SapEntry sap : list) {

            recipes.add(new SpileRecipeWrapper(new ItemStack(sap.wood), sap.fluid));

        }

        return recipes;
    }

}
