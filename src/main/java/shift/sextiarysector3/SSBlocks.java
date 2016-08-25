package shift.sextiarysector3;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import shift.sextiarysector3.block.BlockSSOre;
import shift.sextiarysector3.block.BlockSSPressurePlate;
import shift.sextiarysector3.block.BlockSSPressurePlate.Sensitivity;
import shift.sextiarysector3.util.UtilRegistry;

public class SSBlocks {

	//Core
	/** 銅の感圧板 */
	public static Block copperPressurePlate;
	/** 銀の感圧板 */
	public static Block silverPressurePlate;
	/** オリハルコンの感圧板 */
	public static Block orichalcumPressurePlate;

	//鉱石
	public static Block copperOre;
	public static Block silverOre;
	public static Block orichalcumOre;

	public static void initBlock() {

		//Core
		copperPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.AGEABLE).setUnlocalizedName("ss.copper_pressure_plate");
		UtilRegistry.registerNormalBlock(copperPressurePlate, "CopperPressurePlate", "copper_pressure_plate");

		silverPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.ENEMY).setUnlocalizedName("ss.silver_pressure_plate");
		UtilRegistry.registerNormalBlock(silverPressurePlate, "SilverPressurePlate", "silver_pressure_plate");

		orichalcumPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.XPORB).setUnlocalizedName("ss.orichalcum_pressure_plate");
		UtilRegistry.registerNormalBlock(orichalcumPressurePlate, "OrichalcumPressurePlate", "orichalcum_pressure_plate");

		//鉱石
		copperOre = new BlockSSOre().setUnlocalizedName("ss.copper_ore");
		UtilRegistry.registerNormalBlock(copperOre, "CopperOre", "ore/copper_ore");

		silverOre = new BlockSSOre().setUnlocalizedName("ss.silver_ore");
		UtilRegistry.registerNormalBlock(silverOre, "SilverOre", "ore/silver_ore");

		orichalcumOre = new BlockSSOre().setUnlocalizedName("ss.orichalcum_ore");
		UtilRegistry.registerNormalBlock(orichalcumOre, "Orichalcum", "ore/orichalcum_ore");

	}

}
