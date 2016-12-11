package shift.sextiarysector3.recipe;

import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector3.recipe.template.RecipeCauldron;

public class RecipesCauldron {

    public static void addRecipes(List<RecipeCauldron> list) {

        list.add(new RecipeCauldron(new ItemStack(Items.GLASS_BOTTLE), new FluidStack(FluidRegistry.WATER, 300), new ItemStack(Items.POTIONITEM)));

    }

}
