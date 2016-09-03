package shift.sextiarysector3.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shift.sextiarysector3.SSItems;

public class RecipesFurnace {

	public static void addRecipes() {

		GameRegistry.addSmelting(new ItemStack(SSItems.sapBottle), new ItemStack(SSItems.plastic), 0.1f);

	}
}
