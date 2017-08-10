package shift.sextiarysector3;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.api.season.Season;
import shift.sextiarysector3.block.BlockConveyor;
import shift.sextiarysector3.block.BlockCreativeGF;
import shift.sextiarysector3.block.BlockCreeperChest;
import shift.sextiarysector3.block.BlockEnderStoneMonument;
import shift.sextiarysector3.block.BlockGearBox;
import shift.sextiarysector3.block.BlockLargeOre;
import shift.sextiarysector3.block.BlockMapleSapling;
import shift.sextiarysector3.block.BlockPlasticChest;
import shift.sextiarysector3.block.BlockPlasticColorChest;
import shift.sextiarysector3.block.BlockPlumSapling;
import shift.sextiarysector3.block.BlockPotionCake;
import shift.sextiarysector3.block.BlockSSBase;
import shift.sextiarysector3.block.BlockSSCake;
import shift.sextiarysector3.block.BlockSSFruitLeaves;
import shift.sextiarysector3.block.BlockSSLeaves;
import shift.sextiarysector3.block.BlockSSLog;
import shift.sextiarysector3.block.BlockSSOre;
import shift.sextiarysector3.block.BlockSSPressurePlate;
import shift.sextiarysector3.block.BlockSSPressurePlate.Sensitivity;
import shift.sextiarysector3.block.BlockSSSapling;
import shift.sextiarysector3.block.BlockSanctuary;
import shift.sextiarysector3.block.BlockSapCauldron;
import shift.sextiarysector3.block.BlockSapCauldron.Sap;
import shift.sextiarysector3.block.BlockShaft;
import shift.sextiarysector3.block.BlockShopMonitor;
import shift.sextiarysector3.block.BlockSmallWindmill;
import shift.sextiarysector3.block.BlockSpile;
import shift.sextiarysector3.block.BlockWoodScaffold;
import shift.sextiarysector3.item.ItemBlockMeta;
import shift.sextiarysector3.item.ItemSSCake;
import shift.sextiarysector3.item.ItemSSLeaves;
import shift.sextiarysector3.module.ModuleColorChest;
import shift.sextiarysector3.tileentity.TileEntityConveyor;
import shift.sextiarysector3.tileentity.TileEntityCreativeGFTank;
import shift.sextiarysector3.tileentity.TileEntityCreeperChest;
import shift.sextiarysector3.tileentity.TileEntityPlasticChest;
import shift.sextiarysector3.tileentity.TileEntityShopMonitor;
import shift.sextiarysector3.tileentity.TileEntitySmallWindmill;
import shift.sextiarysector3.tileentity.gearbox.TileEntityWoodGearBox;
import shift.sextiarysector3.tileentity.shaft.TileEntityWoodShaft;
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

    //林業
    public static Block rubberSapling;
    public static Block rubberLog;
    public static Block rubberLeaves;

    public static Block mapleSapling;
    public static Block mapleLog;
    public static Block mapleLeaves;

    public static Block plumSapling;
    public static Block plumLog;
    public static Block plumLeaves;

    public static Block spile;

    //樹液
    public static Block sapCauldron;
    public static Block rubberCauldron;
    public static Block mapleCauldron;

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

    //工業
    public static Block plasticBlock;
    public static Block rubberBlock;

    public static Block plasticChest;

    public static Block[] plasticColorChest = new Block[16];

    public static Block woodScaffold;

    public static Block creativeGF;

    public static Block woodShaft;

    public static Block woodGearBox;

    public static Block conveyor;

    public static Block smallWindmill;

    //マジック
    public static Block strengthCake;
    public static Block swiftnessCake;
    public static Block fireresistanceCake;
    public static Block leapingCake;

    //料理
    public static Block mapleCake;

    //経済
    public static Block creeperChest;

    public static Block shopMonitor;

    public static void initBlock() {

        //Core
        lapisPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.XPORB).setUnlocalizedName("ss.lapis_pressure_plate");
        UtilRegistry.registerNormalBlock(lapisPressurePlate, "lapis_pressure_plate", "lapis_pressure_plate");

        copperPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.AGEABLE).setUnlocalizedName("ss.copper_pressure_plate");
        UtilRegistry.registerNormalBlock(copperPressurePlate, "copper_pressure_plate", "copper_pressure_plate");

        silverPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.ENEMY).setUnlocalizedName("ss.silver_pressure_plate");
        UtilRegistry.registerNormalBlock(silverPressurePlate, "silver_pressure_plate", "silver_pressure_plate");

        orichalcumPressurePlate = new BlockSSPressurePlate(Material.IRON, Sensitivity.PLAYER).setUnlocalizedName("ss.orichalcum_pressure_plate");
        UtilRegistry.registerNormalBlock(orichalcumPressurePlate, "orichalcum_pressure_plate", "orichalcum_pressure_plate");

        sanctuary = new BlockSanctuary().setUnlocalizedName("ss.sanctuary");
        UtilRegistry.registerNormalBlock(sanctuary, "sanctuary", "sanctuary");

        enderStone = new BlockEnderStoneMonument().setUnlocalizedName("ss.ender_stone");
        UtilRegistry.registerNormalBlock(enderStone, "ender_stone", "ender_stone");

        enderStoneFoundation = new BlockSSBase(Material.ROCK).setDropSize(0).setUnlocalizedName("ss.ender_stone_foundation")
                .setHardness(100f).setResistance(100.0F);
        UtilRegistry.registerNormalBlock(enderStoneFoundation, "ender_stone_foundation", "ender_stone_foundation");

        //林業
        rubberSapling = new BlockSSSapling().setUnlocalizedName("ss.rubber_sapling");
        UtilRegistry.registerNormalBlock(rubberSapling, "rubber_sapling", "forestry/rubber_sapling");

        rubberLog = new BlockSSLog().setUnlocalizedName("ss.rubber_log");
        UtilRegistry.registerNormalBlock(rubberLog, "rubber_log", "forestry/rubber_log");

        rubberLeaves = new BlockSSLeaves(rubberSapling).setUnlocalizedName("ss.rubber_leaves");
        UtilRegistry.registerNormalBlock(rubberLeaves, new ItemSSLeaves((BlockLeaves) rubberLeaves), "rubber_leaves", "forestry/rubber_leaves");

        mapleSapling = new BlockMapleSapling().setUnlocalizedName("ss.maple_sapling");
        UtilRegistry.registerNormalBlock(mapleSapling, "maple_sapling", "forestry/maple_sapling");

        mapleLog = new BlockSSLog().setUnlocalizedName("ss.maple_log");
        UtilRegistry.registerNormalBlock(mapleLog, "maple_log", "forestry/maple_log");

        mapleLeaves = new BlockSSLeaves(mapleSapling).setUnlocalizedName("ss.maple_leaves");
        UtilRegistry.registerNormalBlock(mapleLeaves, new ItemSSLeaves((BlockLeaves) mapleLeaves), "maple_leaves", "forestry/maple_leaves");

        plumSapling = new BlockPlumSapling().setUnlocalizedName("ss.plum_sapling");
        UtilRegistry.registerNormalBlock(plumSapling, "plum_sapling", "forestry/plum_sapling");

        plumLog = new BlockSSLog().setUnlocalizedName("ss.plum_log");
        UtilRegistry.registerNormalBlock(plumLog, "plum_log", "forestry/plum_log");

        plumLeaves = new BlockSSFruitLeaves(plumSapling, new ItemStack(SSItems.plum), Season.SUMMER).setUnlocalizedName("ss.plum_leaves");
        UtilRegistry.registerNormalBlock(plumLeaves, new ItemSSLeaves((BlockLeaves) plumLeaves), "plum_leaves", "forestry/plum_leaves");

        spile = new BlockSpile().setUnlocalizedName("ss.spile").setUnlocalizedName("ss.spile");
        UtilRegistry.registerNormalBlock(spile, "spile", "spile");

        sapCauldron = new BlockSapCauldron(Sap.SAP).setUnlocalizedName("ss.sap_cauldron");
        UtilRegistry.registerNormalBlock(sapCauldron, "sap_cauldron", "cauldron/sap_cauldron");

        rubberCauldron = new BlockSapCauldron(Sap.RUBBER).setUnlocalizedName("ss.rubber_cauldron");
        UtilRegistry.registerNormalBlock(rubberCauldron, "rubber_cauldron", "cauldron/rubber_cauldron");

        mapleCauldron = new BlockSapCauldron(Sap.MAPLE).setUnlocalizedName("ss.maple_cauldron");
        UtilRegistry.registerNormalBlock(mapleCauldron, "maple_cauldron", "cauldron/maple_cauldron");

        //鉱業
        //鉱石
        copperOre = new BlockSSOre(1).setUnlocalizedName("ss.copper_ore");
        UtilRegistry.registerNormalBlock(copperOre, "copper_ore", "ore/copper_ore");

        silverOre = new BlockSSOre(2).setUnlocalizedName("ss.silver_ore");
        UtilRegistry.registerNormalBlock(silverOre, "silver_ore", "ore/silver_ore");

        orichalcumOre = new BlockSSOre(3).setUnlocalizedName("ss.orichalcum_ore");
        UtilRegistry.registerNormalBlock(orichalcumOre, "orichalcum_ore", "ore/orichalcum_ore");

        //Large鉱石
        coalLargeOre = new BlockLargeOre(SSItems.coalDust, Blocks.COAL_ORE, 1).setUnlocalizedName("ss.coal_large_ore");
        UtilRegistry.registerNormalBlock(coalLargeOre, "coal_largeore_ore", "ore/coal_largeore");

        ironLargeOre = new BlockLargeOre(SSItems.ironDust, Blocks.IRON_ORE, 2).setUnlocalizedName("ss.iron_large_ore");
        UtilRegistry.registerNormalBlock(ironLargeOre, "iron_large_ore", "ore/iron_large_ore");

        goldLargeOre = new BlockLargeOre(SSItems.goldDust, Blocks.GOLD_ORE, 3).setUnlocalizedName("ss.gold_large_ore");
        UtilRegistry.registerNormalBlock(goldLargeOre, "gold_large_ore", "ore/gold_large_ore");

        ///diamondLargeOre = new BlockLargeOre(Items.COAL, Blocks.DIAMOND_ORE, 1).setUnlocalizedName("ss.diamond_large_ore");
        //UtilRegistry.registerNormalBlock(diamondLargeOre, "DiamondLargeOre", "ore/diamond_large_ore");

        copperLargeOre = new BlockLargeOre(SSItems.copperDust, SSBlocks.copperOre, 2).setUnlocalizedName("ss.copper_large_ore");
        UtilRegistry.registerNormalBlock(copperLargeOre, "copper_large_ore", "ore/copper_large_ore");

        silverLargeOre = new BlockLargeOre(SSItems.silverDust, SSBlocks.silverOre, 3).setUnlocalizedName("ss.silver_large_ore");
        UtilRegistry.registerNormalBlock(silverLargeOre, "silver_large_ore", "ore/silver_large_ore");

        //orichalcumLargeOre = new BlockLargeOre(SSItems.orichalcumGem, SSBlocks.orichalcumOre, 1).setUnlocalizedName("ss.orichalcum_large_ore");
        //UtilRegistry.registerNormalBlock(orichalcumLargeOre, "orichalcum_large_ore", "ore/orichalcum_large_ore");

        copperBlock = new BlockSSBase(Material.IRON).setUnlocalizedName("ss.copper_block").setHardness(4.0F).setResistance(10.0F);
        UtilRegistry.registerNormalBlock(copperBlock, "copper_block", "ore/copper_block");

        silverBlock = new BlockSSBase(Material.IRON).setUnlocalizedName("ss.silver_block").setHardness(3.0F).setResistance(10.0F);
        UtilRegistry.registerNormalBlock(silverBlock, "silver_block", "ore/silver_block");

        orichalcumBlock = new BlockSSBase(Material.IRON).setUnlocalizedName("ss.orichalcum_block").setHardness(8.0F).setResistance(20.0F);
        UtilRegistry.registerNormalBlock(orichalcumBlock, "orichalcum_block", "ore/orichalcum_block");

        //工業
        plasticBlock = new BlockSSBase(Material.IRON).setUnlocalizedName("ss.plastic_block").setHardness(3.0F).setResistance(20.0F).setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        UtilRegistry.registerNormalBlock(plasticBlock, "plastic_block", "plastic_block");

        rubberBlock = new BlockSSBase(Material.IRON).setUnlocalizedName("ss.rubber_block").setHardness(3.0F).setResistance(20.0F).setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        UtilRegistry.registerNormalBlock(rubberBlock, "rubber_block", "rubber_block");

        plasticChest = new BlockPlasticChest().setUnlocalizedName("ss.plastic_chest").setHardness(3.0F).setResistance(20.0F);
        GameRegistry.registerTileEntity(TileEntityPlasticChest.class, SextiarySector3.MODID + ":" + "plastic_chest");
        UtilRegistry.registerTESRBlock(plasticChest, TileEntityPlasticChest.class, "plastic_chest", "chest/plastic_chest");

        for (int i = 0; i < EnumDyeColor.values().length; i++) {

            String colorChestName = "plastic_" + EnumDyeColor.values()[i].getName() + "_chest";

            plasticColorChest[i] = new BlockPlasticColorChest(ModuleColorChest.plasticColorChestTile[i], ModuleColorChest.plasticColorChestType[i])
                    .setUnlocalizedName("ss." + colorChestName).setHardness(3.0F).setResistance(20.0F);
            GameRegistry.registerTileEntity(ModuleColorChest.plasticColorChestTile[i], SextiarySector3.MODID + ":" + colorChestName);
            UtilRegistry.registerTESRBlock(plasticColorChest[i], ModuleColorChest.plasticColorChestTile[i], colorChestName, "chest/" + colorChestName);

        }

        //足場
        woodScaffold = new BlockWoodScaffold().setUnlocalizedName("ss.wood_scaffold").setHardness(3.0F).setResistance(20.0F).setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        UtilRegistry.registerNormalBlock(woodScaffold, new ItemBlockMeta(woodScaffold), "wood_scaffold", "industry/wood_scaffold");

        //GF
        creativeGF = new BlockCreativeGF().setUnlocalizedName("ss.creative_gf_tank").setHardness(3.0F).setResistance(20.0F).setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntityCreativeGFTank.class, SextiarySector3.MODID + ":" + "creative_gf_tank");
        UtilRegistry.registerNormalBlock(creativeGF, "creative_gf_tank", "creative_gf_tank");

        woodShaft = new BlockShaft(Material.WOOD).setUnlocalizedName("ss.wood_shaft").setHardness(3.0F).setResistance(20.0F).setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntityWoodShaft.class, SextiarySector3.MODID + ":" + "wood_shaft");
        UtilRegistry.registerTESRBlock(woodShaft, TileEntityWoodShaft.class, "wood_shaft", "wood_shaft");

        woodGearBox = new BlockGearBox(Material.IRON).setUnlocalizedName("ss.wood_gearbox").setHardness(3.0F).setResistance(20.0F).setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntityWoodGearBox.class, SextiarySector3.MODID + ":" + "wood_gearbox");
        UtilRegistry.registerNormalBlock(woodGearBox, "wood_gearbox", "industry/wood_gearbox");

        conveyor = new BlockConveyor(Material.IRON).setUnlocalizedName("ss.conveyor").setHardness(3.0F).setResistance(20.0F).setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntityConveyor.class, SextiarySector3.MODID + ":" + "conveyor");
        UtilRegistry.registerNormalBlock(conveyor, "conveyor", "industry/conveyor");

        smallWindmill = new BlockSmallWindmill().setUnlocalizedName("ss.wood_shaft").setHardness(4.0F).setResistance(20.0F).setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntitySmallWindmill.class, SextiarySector3.MODID + ":" + "small_windmill");
        UtilRegistry.registerTESRBlock(smallWindmill, TileEntitySmallWindmill.class, "small_windmill", "industry/small_windmill");

        //魔法
        strengthCake = new BlockPotionCake(MobEffects.STRENGTH, 0xff0000).setUnlocalizedName("ss.strength_cake").setHardness(0.5F);
        UtilRegistry.registerNormalBlock(strengthCake, new ItemSSCake(strengthCake), "strength_cake", "cake/strength_cake");

        swiftnessCake = new BlockPotionCake(MobEffects.SPEED, 0x0000FF).setUnlocalizedName("ss.swiftness_cake").setHardness(0.5F);
        UtilRegistry.registerNormalBlock(swiftnessCake, new ItemSSCake(swiftnessCake), "swiftness_cake", "cake/swiftness_cake");

        fireresistanceCake = new BlockPotionCake(MobEffects.FIRE_RESISTANCE, 0xff4500).setUnlocalizedName("ss.fireresistance_cake").setHardness(0.5F);
        UtilRegistry.registerNormalBlock(fireresistanceCake, new ItemSSCake(fireresistanceCake), "fireresistance_cake", "cake/fireresistance_cake");

        leapingCake = new BlockPotionCake(MobEffects.JUMP_BOOST, 0x00FFFF).setUnlocalizedName("ss.leaping_cake").setHardness(0.5F);
        UtilRegistry.registerNormalBlock(leapingCake, new ItemSSCake(leapingCake), "leaping_cake", "cake/leaping_cake");

        //料理
        mapleCake = new BlockSSCake().setUnlocalizedName("ss.maple_cake").setHardness(0.5F).setCreativeTab(SextiarySectorAPI.TabSSCooking);
        UtilRegistry.registerNormalBlock(mapleCake, new ItemSSCake(mapleCake), "maple_cake", "cake/maple_cake");

        //経済
        creeperChest = new BlockCreeperChest().setUnlocalizedName("ss.creeper_chest").setHardness(3.0F).setResistance(20.0F);
        GameRegistry.registerTileEntity(TileEntityCreeperChest.class, SextiarySector3.MODID + ":" + "creeper_chest");
        UtilRegistry.registerTESRBlock(creeperChest, TileEntityCreeperChest.class, "creeper_chest", "creeper_chest");

        shopMonitor = new BlockShopMonitor().setUnlocalizedName("ss.shop_monitor").setHardness(3.0F).setResistance(20.0F);
        GameRegistry.registerTileEntity(TileEntityShopMonitor.class, SextiarySector3.MODID + ":" + "shop_monitor");
        UtilRegistry.registerTESRBlock(shopMonitor, TileEntityShopMonitor.class, "shop_monitor", "shop_monitor");

    }

}
