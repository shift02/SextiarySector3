package shift.sextiarysector3;

import net.minecraft.block.Block;
import shift.sextiarysector3.block.BlockSSOre;
import shift.sextiarysector3.util.UtilRegistry;

public class SSBlocks {

	public static Block copperOre;
	public static Block silverOre;
	public static Block orichalcumOre;

	public static void initBlock() {

		copperOre = new BlockSSOre().setUnlocalizedName("ss.copper_ore");
		UtilRegistry.registerNormalBlock(copperOre, "CopperOre", "ore/copper_ore");

		silverOre = new BlockSSOre().setUnlocalizedName("ss.silver_ore");
		UtilRegistry.registerNormalBlock(silverOre, "SilverOre", "ore/silver_ore");

		orichalcumOre = new BlockSSOre().setUnlocalizedName("ss.orichalcum_ore");
		UtilRegistry.registerNormalBlock(orichalcumOre, "Orichalcum", "ore/orichalcum_ore");

	}

}
