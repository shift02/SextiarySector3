package shift.sextiarysector3;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import shift.sextiarysector3.block.BlockEnderStoneMonument;
import shift.sextiarysector3.block.BlockLargeOre;
import shift.sextiarysector3.block.BlockSSBase;
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

    public static Block enderStone;
    public static Block enderStoneFoundation;

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

    public static Block coalLargeOre;
    public static Block ironLargeOre;
    public static Block goldLargeOre;
    //public static Block diamondLargeOre;

    public static Block copperLargeOre;
    public static Block silverLargeOre;
    //public static Block orichalcumLargeOre;

    public static Block copperBlock;
    public static Block silverBlock;
    public static Block orichalcumBlock;

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

        enderStone = new BlockEnderStoneMonument().setUnlocalizedName("ss.ender_stone");
        UtilRegistry.registerNormalBlock(enderStone, "EnderStone", "ender_stone");

        enderStoneFoundation = new BlockSSBase(Material.ROCK).setDropSize(0).setUnlocalizedName("ss.ender_stone_foundation")
                .setHardness(100f).setResistance(100.0F);
        UtilRegistry.registerNormalBlock(enderStoneFoundation, "EnderStoneFoundation", "ender_stone_foundation");

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
        copperOre = new BlockSSOre(1).setUnlocalizedName("ss.copper_ore");
        UtilRegistry.registerNormalBlock(copperOre, "CopperOre", "ore/copper_ore");

        silverOre = new BlockSSOre(2).setUnlocalizedName("ss.silver_ore");
        UtilRegistry.registerNormalBlock(silverOre, "SilverOre", "ore/silver_ore");

        orichalcumOre = new BlockSSOre(3).setUnlocalizedName("ss.orichalcum_ore");
        UtilRegistry.registerNormalBlock(orichalcumOre, "Orichalcum", "ore/orichalcum_ore");

        //Large鉱石
        coalLargeOre = new BlockLargeOre(SSItems.coalDust, Blocks.COAL_ORE, 1).setUnlocalizedName("ss.coal_large_ore");
        UtilRegistry.registerNormalBlock(coalLargeOre, "CoalLargeOre", "ore/coal_largeore");

        ironLargeOre = new BlockLargeOre(SSItems.ironDust, Blocks.IRON_ORE, 2).setUnlocalizedName("ss.iron_large_ore");
        UtilRegistry.registerNormalBlock(ironLargeOre, "IronLargeOre", "ore/iron_large_ore");

        goldLargeOre = new BlockLargeOre(SSItems.goldDust, Blocks.GOLD_ORE, 3).setUnlocalizedName("ss.gold_large_ore");
        UtilRegistry.registerNormalBlock(goldLargeOre, "GoldLargeOre", "ore/gold_large_ore");

        ///diamondLargeOre = new BlockLargeOre(Items.COAL, Blocks.DIAMOND_ORE, 1).setUnlocalizedName("ss.diamond_large_ore");
        //UtilRegistry.registerNormalBlock(diamondLargeOre, "DiamondLargeOre", "ore/diamond_large_ore");

        copperLargeOre = new BlockLargeOre(SSItems.coalDust, SSBlocks.copperOre, 2).setUnlocalizedName("ss.copper_large_ore");
        UtilRegistry.registerNormalBlock(copperLargeOre, "CopperLargeOre", "ore/copper_large_ore");

        silverLargeOre = new BlockLargeOre(SSItems.silverDust, SSBlocks.silverOre, 3).setUnlocalizedName("ss.silver_large_ore");
        UtilRegistry.registerNormalBlock(silverLargeOre, "SilverLargeOre", "ore/silver_large_ore");

        //orichalcumLargeOre = new BlockLargeOre(SSItems.orichalcumGem, SSBlocks.orichalcumOre, 1).setUnlocalizedName("ss.orichalcum_large_ore");
        //UtilRegistry.registerNormalBlock(orichalcumLargeOre, "orichalcum_large_ore", "ore/orichalcum_large_ore");

        copperBlock = new BlockSSBase(Material.IRON).setUnlocalizedName("ss.copper_block").setHardness(4.0F).setResistance(10.0F);
        UtilRegistry.registerNormalBlock(copperBlock, "CopperBlock", "ore/copper_block");

        silverBlock = new BlockSSBase(Material.IRON).setUnlocalizedName("ss.silver_block").setHardness(3.0F).setResistance(10.0F);
        UtilRegistry.registerNormalBlock(silverBlock, "SilverBlock", "ore/silver_block");

        orichalcumBlock = new BlockSSBase(Material.IRON).setUnlocalizedName("ss.orichalcum_block").setHardness(8.0F).setResistance(20.0F);
        UtilRegistry.registerNormalBlock(orichalcumBlock, "OrichalcumBlock", "ore/orichalcum_block");

    }

}
