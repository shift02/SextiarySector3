package shift.sextiarysector3;

import net.minecraft.item.Item;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.item.ItemIngot;
import shift.sextiarysector3.item.ItemSSBase;
import shift.sextiarysector3.item.ItemSSShield;
import shift.sextiarysector3.renderer.RendererShield;
import shift.sextiarysector3.tileentity.TileEntityShield;
import shift.sextiarysector3.util.UtilRegistry;

public class SSItems {

	public static Item plasticShield;

	public static Item copperIngot;
	public static Item silverIngot;
	public static Item orichalcumGem;

	public static Item plastic;

	public static void initItem() {

		plasticShield = new ItemSSShield().setUnlocalizedName("ss.plastic_shield");
		UtilRegistry.registerCustomItem(plasticShield, "PlasticShield", "plastic_shield", TileEntityShield.class, new RendererShield());

		copperIngot = new ItemIngot().setUnlocalizedName("ss.copper_ingot");
		UtilRegistry.registerNormalItem(copperIngot, "CopperIngot", "ingot/copper_ingot");

		silverIngot = new ItemIngot().setUnlocalizedName("ss.silver_ingot");
		UtilRegistry.registerNormalItem(silverIngot, "SilverIngot", "ingot/silver_ingot");

		orichalcumGem = new ItemIngot().setUnlocalizedName("ss.orichalcum_gem");
		UtilRegistry.registerNormalItem(orichalcumGem, "OrichalcumGem", "gem/orichalcum_gem");

		plastic = new ItemSSBase().setUnlocalizedName("ss.plastic").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		UtilRegistry.registerNormalItem(plastic, "Plastic", "plastic");

	}
}
