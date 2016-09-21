package shift.sextiarysector3;

import net.minecraft.item.Item;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.item.ItemDust;
import shift.sextiarysector3.item.ItemEnderCard;
import shift.sextiarysector3.item.ItemIngot;
import shift.sextiarysector3.item.ItemSSAxe;
import shift.sextiarysector3.item.ItemSSBase;
import shift.sextiarysector3.item.ItemSSHoe;
import shift.sextiarysector3.item.ItemSSPickaxe;
import shift.sextiarysector3.item.ItemSSShield;
import shift.sextiarysector3.item.ItemSSSpade;
import shift.sextiarysector3.item.ItemSSSword;
import shift.sextiarysector3.item.ItemSapBottle;
import shift.sextiarysector3.module.ModuleToolMaterial;
import shift.sextiarysector3.renderer.RendererShield;
import shift.sextiarysector3.tileentity.TileEntityShield;
import shift.sextiarysector3.util.UtilRegistry;

public class SSItems {

	//Core
	public static Item bluestone;

	public static Item plasticShield;

	public static Item enderCard;

	//道具
	public static Item copperShovel;
	public static Item copperPickaxe;
	public static Item copperAxe;
	public static Item copperSword;
	public static Item copperHoe;

	public static Item silverShovel;
	public static Item silverPickaxe;
	public static Item silverAxe;
	public static Item silverSword;
	public static Item silverHoe;

	public static Item orichalcumShovel;
	public static Item orichalcumPickaxe;
	public static Item orichalcumAxe;
	public static Item orichalcumSword;
	public static Item orichalcumHoe;

	//林業
	public static Item sapBottle;
	public static Item rubberBottle;

	//鉱業
	public static Item copperIngot;
	public static Item silverIngot;
	public static Item orichalcumGem;

	//鉱業
	public static Item coalDust;
	public static Item ironDust;
	public static Item goldDust;

	public static Item copperDust;
	public static Item silverDust;

	public static Item plastic;

	public static void initItem() {

		bluestone = new ItemSSBase().setUnlocalizedName("ss.bluestone");
		UtilRegistry.registerNormalItem(bluestone, "Bluestone", "dust/bluestone");

		plasticShield = new ItemSSShield().setUnlocalizedName("ss.plastic_shield");
		UtilRegistry.registerCustomItem(plasticShield, "PlasticShield", "plastic_shield", TileEntityShield.class, new RendererShield());

		enderCard = new ItemEnderCard().setUnlocalizedName("ss.ender_card");
		UtilRegistry.registerNormalItem(enderCard, "EnderCard", "ender_card");

		//銅
		copperShovel = new ItemSSSpade(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_shovel");
		UtilRegistry.registerToolItem(copperShovel, "CopperShovel", "tool/copper_shovel");

		copperPickaxe = new ItemSSPickaxe(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_pickaxe");
		UtilRegistry.registerToolItem(copperPickaxe, "CopperPickaxe", "tool/copper_pickaxe");

		copperAxe = new ItemSSAxe(ModuleToolMaterial.copperTool, 8.0F, -3.2F).setUnlocalizedName("ss.copper_axe");
		UtilRegistry.registerToolItem(copperAxe, "CopperAxe", "tool/copper_axe");

		copperSword = new ItemSSSword(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_sword");
		UtilRegistry.registerToolItem(copperSword, "CopperSword", "tool/copper_sword");

		copperHoe = new ItemSSHoe(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_hoe");
		UtilRegistry.registerToolItem(copperHoe, "CopperHoe", "tool/copper_hoe");

		//銀
		silverShovel = new ItemSSSpade(ModuleToolMaterial.silverTool).setUnlocalizedName("ss.silver_shovel");
		UtilRegistry.registerToolItem(silverShovel, "SilverShovel", "tool/silver_shovel");

		silverPickaxe = new ItemSSPickaxe(ModuleToolMaterial.silverTool).setUnlocalizedName("ss.silver_pickaxe");
		UtilRegistry.registerToolItem(silverPickaxe, "SilverPickaxe", "tool/silver_pickaxe");

		silverAxe = new ItemSSAxe(ModuleToolMaterial.silverTool, 8.0F, -3.2F).setUnlocalizedName("ss.silver_axe");
		UtilRegistry.registerToolItem(silverAxe, "SilverAxe", "tool/silver_axe");

		silverSword = new ItemSSSword(ModuleToolMaterial.silverTool).setUnlocalizedName("ss.silver_sword");
		UtilRegistry.registerToolItem(silverSword, "SilverSword", "tool/silver_sword");

		silverHoe = new ItemSSHoe(ModuleToolMaterial.silverTool).setUnlocalizedName("ss.silver_hoe");
		UtilRegistry.registerToolItem(silverHoe, "SilverHoe", "tool/silver_hoe");

		//オリハルコン
		orichalcumShovel = new ItemSSSpade(ModuleToolMaterial.orichalcumTool).setUnlocalizedName("ss.orichalcum_shovel");
		UtilRegistry.registerToolItem(orichalcumShovel, "OrichalcumShovel", "tool/orichalcum_shovel");

		orichalcumPickaxe = new ItemSSPickaxe(ModuleToolMaterial.orichalcumTool).setUnlocalizedName("ss.orichalcum_pickaxe");
		UtilRegistry.registerToolItem(orichalcumPickaxe, "OrichalcumPickaxe", "tool/orichalcum_pickaxe");

		orichalcumAxe = new ItemSSAxe(ModuleToolMaterial.orichalcumTool, 8.0F, -3.2F).setUnlocalizedName("ss.orichalcum_axe");
		UtilRegistry.registerToolItem(orichalcumAxe, "OrichalcumAxe", "tool/orichalcum_axe");

		orichalcumSword = new ItemSSSword(ModuleToolMaterial.orichalcumTool).setUnlocalizedName("ss.orichalcum_sword");
		UtilRegistry.registerToolItem(orichalcumSword, "OrichalcumSword", "tool/orichalcum_sword");

		orichalcumHoe = new ItemSSHoe(ModuleToolMaterial.orichalcumTool).setUnlocalizedName("ss.orichalcum_hoe");
		UtilRegistry.registerToolItem(orichalcumHoe, "OrichalcumHoe", "tool/orichalcum_hoe");

		//林業
		sapBottle = new ItemSapBottle().setUnlocalizedName("ss.sap_bottle");
		UtilRegistry.registerNormalItem(sapBottle, "SapBottle", "fluid/sap_bottle");

		rubberBottle = new ItemSapBottle().setUnlocalizedName("ss.rubber_bottle");
		UtilRegistry.registerNormalItem(rubberBottle, "RubberBottle", "fluid/rubber_bottle");

		//鉱業
		copperIngot = new ItemIngot().setUnlocalizedName("ss.copper_ingot");
		UtilRegistry.registerNormalItem(copperIngot, "CopperIngot", "ingot/copper_ingot");

		silverIngot = new ItemIngot().setUnlocalizedName("ss.silver_ingot");
		UtilRegistry.registerNormalItem(silverIngot, "SilverIngot", "ingot/silver_ingot");

		orichalcumGem = new ItemIngot().setUnlocalizedName("ss.orichalcum_gem");
		UtilRegistry.registerNormalItem(orichalcumGem, "OrichalcumGem", "gem/orichalcum_gem");

		//工業
		coalDust = new ItemDust().setUnlocalizedName("ss.coal_dust");
		UtilRegistry.registerNormalItem(coalDust, "CoalDust", "dust/coal_dust");

		ironDust = new ItemDust().setUnlocalizedName("ss.iron_dust");
		UtilRegistry.registerNormalItem(ironDust, "IronDust", "dust/iron_dust");

		goldDust = new ItemDust().setUnlocalizedName("ss.gold_dust");
		UtilRegistry.registerNormalItem(goldDust, "GoldDust", "dust/gold_dust");

		copperDust = new ItemDust().setUnlocalizedName("ss.copper_dust");
		UtilRegistry.registerNormalItem(copperDust, "CopperDust", "dust/copper_dust");

		silverDust = new ItemDust().setUnlocalizedName("ss.silver_dust");
		UtilRegistry.registerNormalItem(silverDust, "SilverDust", "dust/silver_dust");

		plastic = new ItemSSBase().setUnlocalizedName("ss.plastic").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		UtilRegistry.registerNormalItem(plastic, "Plastic", "plastic");

	}
}
