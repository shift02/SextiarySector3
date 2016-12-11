package shift.sextiarysector3.recipe.template;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeCauldron {

    public ItemStack input;

    public FluidStack fluid;

    public ItemStack output;

    public RecipeCauldron(ItemStack input, FluidStack fluid, ItemStack output) {
        this.input = input;
        this.fluid = fluid;
        this.output = output;
    }

}
