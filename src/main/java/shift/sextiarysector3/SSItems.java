package shift.sextiarysector3;

import net.minecraft.item.Item;
import shift.sextiarysector3.item.ItemIngot;
import shift.sextiarysector3.util.UtilRegistry;

public class SSItems {

	public static Item copperIngot;
	public static Item silverIngot;
	public static Item orichalcumGem;

	public static void initItem() {

		copperIngot = new ItemIngot().setUnlocalizedName("ss.copper_ingot");
		UtilRegistry.registerNormalItem(copperIngot, "CopperIngot", "ingot/copper_ingot");

		silverIngot = new ItemIngot().setUnlocalizedName("ss.silver_ingot");
		UtilRegistry.registerNormalItem(silverIngot, "SilverIngot", "ingot/silver_ingot");

		orichalcumGem = new ItemIngot().setUnlocalizedName("ss.orichalcum_gem");
		UtilRegistry.registerNormalItem(orichalcumGem, "OrichalcumGem", "gem/orichalcum_gem");

	}
}
