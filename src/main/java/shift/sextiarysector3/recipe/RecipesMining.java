package shift.sextiarysector3.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;

public class RecipesMining {

	public static void addRecipes(CraftingManager p_77608_1_) {

		//鉱石 インゴット--ブロック
		Object[] oIngot = new Object[] { "ingotCopper", "ingotSilver", "gemOrichalcum" };
		Block[] block = new Block[] { SSBlocks.copperBlock, SSBlocks.silverBlock, SSBlocks.orichalcumBlock };
		for (int i = 0; i < oIngot.length; i++) {
			p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(block[i], 1),
					new Object[] { "xxx", "xxx", "xxx",
							Character.valueOf('x'), oIngot[i],
					}));
		}

		Object[] oBlock = new Object[] { "blockCopper", "blockSilver", "blockOrichalcum" };
		Item[] ingot = new Item[] { SSItems.copperIngot, SSItems.silverIngot, SSItems.orichalcumGem };
		for (int i = 0; i < oBlock.length; i++) {
			p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(ingot[i], 9),
					new Object[] {
							oBlock[i]
					}));
		}

	}

}
