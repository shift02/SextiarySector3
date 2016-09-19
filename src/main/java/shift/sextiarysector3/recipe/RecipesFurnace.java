package shift.sextiarysector3.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;

public class RecipesFurnace {

	public static void addRecipes() {

		GameRegistry.addSmelting(new ItemStack(Items.REDSTONE), new ItemStack(SSItems.bluestone), 0.1f);

		GameRegistry.addSmelting(new ItemStack(SSItems.sapBottle), new ItemStack(SSItems.plastic), 0.1f);

		GameRegistry.addSmelting(new ItemStack(SSBlocks.copperOre), new ItemStack(SSItems.copperIngot), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSBlocks.silverOre), new ItemStack(SSItems.silverIngot), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSBlocks.orichalcumOre), new ItemStack(SSItems.orichalcumGem), 0.1f);

		GameRegistry.addSmelting(new ItemStack(SSItems.ironDust), new ItemStack(Items.IRON_INGOT), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSItems.goldDust), new ItemStack(Items.GOLD_INGOT), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSItems.copperDust), new ItemStack(SSItems.copperIngot), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSItems.silverDust), new ItemStack(SSItems.silverIngot), 0.1f);

	}
}
