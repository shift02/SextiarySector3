package shift.sextiarysector3.recipe;

import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector3.SSFluids;
import shift.sextiarysector3.SSItems;
import shift.sextiarysector3.recipe.template.RecipeCauldron;

public class RecipesCauldron {

    public static void addRecipes(List<RecipeCauldron> list) {

        list.add(new RecipeCauldron(new ItemStack(Items.GLASS_BOTTLE), new FluidStack(FluidRegistry.WATER, 300), new ItemStack(Items.POTIONITEM)));

        list.add(new RecipeCauldron(new ItemStack(Items.GLASS_BOTTLE), new FluidStack(SSFluids.rubber, 300), new ItemStack(SSItems.rubberBottle)));

        list.add(new RecipeCauldron(new ItemStack(Items.GLASS_BOTTLE), new FluidStack(SSFluids.sap, 300), new ItemStack(SSItems.sapBottle)));

        list.add(new RecipeCauldron(new ItemStack(Items.GLASS_BOTTLE), new FluidStack(SSFluids.maple, 300), new ItemStack(SSItems.mapleBottle)));

    }

}
