package shift.sextiarysector3;

import net.minecraft.item.Item;
import shift.sextiarysector3.item.ItemIngot;
import shift.sextiarysector3.util.UtilRegistry;

public class SSItems {

	public static Item copperIngot;

	public static void initItem() {

		copperIngot = new ItemIngot().setUnlocalizedName("ss.copper_ingot");
		UtilRegistry.registerNormalItem(copperIngot, "CopperIngot", "ingot/copper_ingot");

	}
}
