package shift.sextiarysector3.recipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;

public class RecipesCore {

	public static void addRecipes(CraftingManager p_77608_1_) {

		//Shield
		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.plasticShield, 1),
				new Object[] {
						"x x", "xxx", " x ",
						'x', SSItems.plastic
				}));

		//鉱石 インゴット--プレート
		Object[] oIngot = new Object[] { "gemLapis", "ingotCopper", "ingotSilver", "gemOrichalcum" };
		Block[] pressurePlate = new Block[] { SSBlocks.lapisPressurePlate, SSBlocks.copperPressurePlate, SSBlocks.silverPressurePlate,
				SSBlocks.orichalcumPressurePlate };
		for (int i = 0; i < oIngot.length; i++) {
			p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(pressurePlate[i], 1),
					new Object[] { "xx",
							Character.valueOf('x'), oIngot[i],
					}));
		}

		//スパイル
		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.sanctuary, 2),
				new Object[] {
						"xyx", "xzx", "xxx",
						'x', "stone",
						'y', "ingotSilver",
						'z', Blocks.REDSTONE_BLOCK
				}));

	}

}
