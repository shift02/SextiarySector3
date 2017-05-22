package shift.sextiarysector3.recipe.template;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.VanillaBrewingRecipe;
import shift.sextiarysector3.SSItems;

public class SSBrewingRecipe extends VanillaBrewingRecipe {

    @Override
    public boolean isInput(ItemStack stack) {
        Item item = stack.getItem();
        return item == SSItems.potionCapsule;
    }

}
