package shift.sextiarysector3;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import shift.sextiarysector3.block.BlockSSLeaves;
import shift.sextiarysector3.block.BlockSSLog;
import shift.sextiarysector3.block.BlockSSOre;
import shift.sextiarysector3.block.BlockSSPressurePlate;
import shift.sextiarysector3.block.BlockSSPressurePlate.Sensitivity;
import shift.sextiarysector3.block.BlockSSSapling;
import shift.sextiarysector3.block.BlockSanctuary;
import shift.sextiarysector3.block.BlockSapCauldron;
import shift.sextiarysector3.block.BlockSapCauldron.Sap;
import shift.sextiarysector3.block.BlockSpile;
import shift.sextiarysector3.item.ItemSSLeaves;
import shift.sextiarysector3.util.UtilRegistry;

public class SSBlocks {

	//Core
	public static Block lapisPressurePlate;

	/** 銅の感圧板 */
	public static Block copperPressurePlate;
	/** 銀の感圧板 */
	public static Block silverPressurePlate;
	/** オリハルコンの感圧板 */
	public static Block orichalcumPressurePlate;

	public static Block sanctuary;

	public static Block rubberSapling;
	public static Block rubberLog;
	public static Block rubberLeaves;

	public static Block spile;

	//樹液
	public static Block sapCauldron;
	public static Block rubberCauldron;

	//鉱石
	public static Block copperOre;
	public static Block silverOre;
	public static Block orichalcumOre;

	public static void initBlock() {

		//Core
		lapisPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.XPORB).setUnlocalizedName("ss.lapis_pressure_plate");
		UtilRegistry.registerNormalBlock(lapisPressurePlate, "LapisPressurePlate", "lapis_pressure_plate");

		copperPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.AGEABLE).setUnlocalizedName("ss.copper_pressure_plate");
		UtilRegistry.registerNormalBlock(copperPressurePlate, "CopperPressurePlate", "copper_pressure_plate");

		silverPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.ENEMY).setUnlocalizedName("ss.silver_pressure_plate");
		UtilRegistry.registerNormalBlock(silverPressurePlate, "SilverPressurePlate", "silver_pressure_plate");

		orichalcumPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.PLAYER).setUnlocalizedName("ss.orichalcum_pressure_plate");
		UtilRegistry.registerNormalBlock(orichalcumPressurePlate, "OrichalcumPressurePlate", "orichalcum_pressure_plate");

		sanctuary = new BlockSanctuary().setUnlocalizedName("ss.sanctuary");
		UtilRegistry.registerNormalBlock(sanctuary, "Sanctuary", "sanctuary");

		//林業
		rubberSapling = new BlockSSSapling().setUnlocalizedName("ss.rubber_sapling");
		UtilRegistry.registerNormalBlock(rubberSapling, "RubberSapling", "forestry/rubber_sapling");

		rubberLog = new BlockSSLog().setUnlocalizedName("ss.rubber_log");
		UtilRegistry.registerNormalBlock(rubberLog, "RubberLog", "forestry/rubber_log");

		rubberLeaves = new BlockSSLeaves(rubberSapling).setUnlocalizedName("ss.rubber_leaves");
		UtilRegistry.registerNormalBlock(rubberLeaves, new ItemSSLeaves((BlockLeaves) rubberLeaves), "RubberLeaves", "forestry/rubber_leaves");

		spile = new BlockSpile().setUnlocalizedName("ss.spile").setUnlocalizedName("ss.spile");
		UtilRegistry.registerNormalBlock(spile, "Spile", "spile");

		sapCauldron = new BlockSapCauldron(Sap.SAP).setUnlocalizedName("ss.sap_cauldron");
		UtilRegistry.registerNormalBlock(sapCauldron, "SapCauldron", "cauldron/sap_cauldron");

		rubberCauldron = new BlockSapCauldron(Sap.RUBBER).setUnlocalizedName("ss.rubber_cauldron");
		UtilRegistry.registerNormalBlock(rubberCauldron, "RubberCauldron", "cauldron/rubber_cauldron");

		//鉱石
		copperOre = new BlockSSOre().setUnlocalizedName("ss.copper_ore");
		UtilRegistry.registerNormalBlock(copperOre, "CopperOre", "ore/copper_ore");

		silverOre = new BlockSSOre().setUnlocalizedName("ss.silver_ore");
		UtilRegistry.registerNormalBlock(silverOre, "SilverOre", "ore/silver_ore");

		orichalcumOre = new BlockSSOre().setUnlocalizedName("ss.orichalcum_ore");
		UtilRegistry.registerNormalBlock(orichalcumOre, "Orichalcum", "ore/orichalcum_ore");

	}

}
