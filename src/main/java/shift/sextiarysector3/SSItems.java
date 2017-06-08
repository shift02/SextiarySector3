package shift.sextiarysector3;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionHelper;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.item.ItemCalendar;
import shift.sextiarysector3.item.ItemCapsule;
import shift.sextiarysector3.item.ItemDepthMeter;
import shift.sextiarysector3.item.ItemDust;
import shift.sextiarysector3.item.ItemEnderCard;
import shift.sextiarysector3.item.ItemIngot;
import shift.sextiarysector3.item.ItemPotionCapsule;
import shift.sextiarysector3.item.ItemRubberGroves;
import shift.sextiarysector3.item.ItemSSArmor;
import shift.sextiarysector3.item.ItemSSAxe;
import shift.sextiarysector3.item.ItemSSBase;
import shift.sextiarysector3.item.ItemSSHoe;
import shift.sextiarysector3.item.ItemSSPickaxe;
import shift.sextiarysector3.item.ItemSSShield;
import shift.sextiarysector3.item.ItemSSSpade;
import shift.sextiarysector3.item.ItemSSSword;
import shift.sextiarysector3.item.ItemSapBottle;
import shift.sextiarysector3.item.ItemSeasonStone;
import shift.sextiarysector3.item.ItemShopMemory;
import shift.sextiarysector3.module.ModuleToolMaterial;
import shift.sextiarysector3.tileentity.TileEntityShield;
import shift.sextiarysector3.util.UtilRegistry;

public class SSItems {

    //Core
    public static Item bluestone;

    public static Item plasticShield;

    public static Item enderCard;

    public static Item depthMeter;

    public static Item calendar;
    public static Item seasonstone;

    public static Item emptyCapsule;

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

    //防具
    //  銅
    public static Item copperHelmet;
    public static Item copperChestplate;
    public static Item copperLeggings;
    public static Item copperBoots;

    // シルバー
    public static Item silverHelmet;
    public static Item silverChestplate;
    public static Item silverLeggings;
    public static Item silverBoots;

    // オリハルコン
    public static Item orichalcumHelmet;
    public static Item orichalcumChestplate;
    public static Item orichalcumLeggings;
    public static Item orichalcumBoots;

    //林業
    public static Item treeBranch;

    public static Item sapBottle;
    public static Item rubberBottle;
    public static Item mapleBottle;

    //鉱業
    public static Item copperIngot;
    public static Item silverIngot;
    public static Item orichalcumGem;

    //工業
    public static Item coalDust;
    public static Item ironDust;
    public static Item goldDust;

    public static Item copperDust;
    public static Item silverDust;

    public static Item plastic;
    public static Item rubber;

    public static Item blueGel;

    public static Item woodGear;

    public static Item rubberGloves;

    public static Item ductTape;

    //魔術
    public static Item potionCapsule;

    //経済
    public static ItemShopMemory creeperMemory;

    public static void initItem() {

        bluestone = new ItemSSBase().setUnlocalizedName("ss.bluestone");
        UtilRegistry.registerNormalItem(bluestone, "bluestone", "dust/bluestone");

        plasticShield = new ItemSSShield().setUnlocalizedName("ss.plastic_shield");
        UtilRegistry.registerCustomItem(plasticShield, "plastic_shield", "plastic_shield", TileEntityShield.class);

        enderCard = new ItemEnderCard().setUnlocalizedName("ss.ender_card");
        UtilRegistry.registerNormalItem(enderCard, "ender_card", "ender_card");

        depthMeter = new ItemDepthMeter().setUnlocalizedName("ss.depth_meter");
        UtilRegistry.registerAnimationItem(depthMeter, "depth_meter", "depth_meter/depth_meter", 8);

        calendar = new ItemCalendar().setUnlocalizedName("ss.calendar");
        UtilRegistry.registerAnimationItem(calendar, "calendar", "calendar/calendar", 30);

        seasonstone = new ItemSeasonStone().setUnlocalizedName("ss.season_stone");
        UtilRegistry.registerAnimationItem(seasonstone, "season_stone", "season_stone/season_stone", 4);

        emptyCapsule = new ItemCapsule().setUnlocalizedName("ss.empty_capsule");
        UtilRegistry.registerNormalItem(emptyCapsule, "empty_capsule", "capsule/empty_capsule");

        //銅
        copperShovel = new ItemSSSpade(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_shovel");
        UtilRegistry.registerToolItem(copperShovel, "copper_shovel", "tool/copper_shovel");

        copperPickaxe = new ItemSSPickaxe(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_pickaxe");
        UtilRegistry.registerToolItem(copperPickaxe, "copper_pickaxe", "tool/copper_pickaxe");

        copperAxe = new ItemSSAxe(ModuleToolMaterial.copperTool, 8.0F, -3.2F).setUnlocalizedName("ss.copper_axe");
        UtilRegistry.registerToolItem(copperAxe, "copper_axe", "tool/copper_axe");

        copperSword = new ItemSSSword(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_sword");
        UtilRegistry.registerToolItem(copperSword, "copper_sword", "tool/copper_sword");

        copperHoe = new ItemSSHoe(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_hoe");
        UtilRegistry.registerToolItem(copperHoe, "copper_hoe", "tool/copper_hoe");

        //銀
        silverShovel = new ItemSSSpade(ModuleToolMaterial.silverTool).setUnlocalizedName("ss.silver_shovel");
        UtilRegistry.registerToolItem(silverShovel, "silver_shovel", "tool/silver_shovel");

        silverPickaxe = new ItemSSPickaxe(ModuleToolMaterial.silverTool).setUnlocalizedName("ss.silver_pickaxe");
        UtilRegistry.registerToolItem(silverPickaxe, "silver_pickaxe", "tool/silver_pickaxe");

        silverAxe = new ItemSSAxe(ModuleToolMaterial.silverTool, 8.0F, -3.2F).setUnlocalizedName("ss.silver_axe");
        UtilRegistry.registerToolItem(silverAxe, "silver_axe", "tool/silver_axe");

        silverSword = new ItemSSSword(ModuleToolMaterial.silverTool).setUnlocalizedName("ss.silver_sword");
        UtilRegistry.registerToolItem(silverSword, "silver_sword", "tool/silver_sword");

        silverHoe = new ItemSSHoe(ModuleToolMaterial.silverTool).setUnlocalizedName("ss.silver_hoe");
        UtilRegistry.registerToolItem(silverHoe, "silver_hoe", "tool/silver_hoe");

        //オリハルコン
        orichalcumShovel = new ItemSSSpade(ModuleToolMaterial.orichalcumTool).setUnlocalizedName("ss.orichalcum_shovel");
        UtilRegistry.registerToolItem(orichalcumShovel, "orichalcum_shovel", "tool/orichalcum_shovel");

        orichalcumPickaxe = new ItemSSPickaxe(ModuleToolMaterial.orichalcumTool).setUnlocalizedName("ss.orichalcum_pickaxe");
        UtilRegistry.registerToolItem(orichalcumPickaxe, "orichalcum_pickaxe", "tool/orichalcum_pickaxe");

        orichalcumAxe = new ItemSSAxe(ModuleToolMaterial.orichalcumTool, 8.0F, -3.2F).setUnlocalizedName("ss.orichalcum_axe");
        UtilRegistry.registerToolItem(orichalcumAxe, "orichalcum_axe", "tool/orichalcum_axe");

        orichalcumSword = new ItemSSSword(ModuleToolMaterial.orichalcumTool).setUnlocalizedName("ss.orichalcum_sword");
        UtilRegistry.registerToolItem(orichalcumSword, "orichalcum_sword", "tool/orichalcum_sword");

        orichalcumHoe = new ItemSSHoe(ModuleToolMaterial.orichalcumTool).setUnlocalizedName("ss.orichalcum_hoe");
        UtilRegistry.registerToolItem(orichalcumHoe, "orichalcum_hoe", "tool/orichalcum_hoe");

        //防具
        //  銅
        copperHelmet = new ItemSSArmor(ModuleToolMaterial.copperArmor, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("ss.copper_helmet");
        UtilRegistry.registerNormalItem(copperHelmet, "copper_helmet", "armor/copper_helmet");

        copperChestplate = new ItemSSArmor(ModuleToolMaterial.copperArmor, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("ss.copper_chestplate");
        UtilRegistry.registerNormalItem(copperChestplate, "copper_chestplate", "armor/copper_chestplate");

        copperLeggings = new ItemSSArmor(ModuleToolMaterial.copperArmor, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("ss.copper_leggings");
        UtilRegistry.registerNormalItem(copperLeggings, "copper_leggings", "armor/copper_leggings");

        copperBoots = new ItemSSArmor(ModuleToolMaterial.copperArmor, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("ss.copper_boots");
        UtilRegistry.registerNormalItem(copperBoots, "copper_boots", "armor/copper_boots");

        //銀
        silverHelmet = new ItemSSArmor(ModuleToolMaterial.silverArmor, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("ss.silver_helmet");
        UtilRegistry.registerNormalItem(silverHelmet, "silver_helmet", "armor/silver_helmet");

        silverChestplate = new ItemSSArmor(ModuleToolMaterial.silverArmor, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("ss.silver_chestplate");
        UtilRegistry.registerNormalItem(silverChestplate, "silver_chestplate", "armor/silver_chestplate");

        silverLeggings = new ItemSSArmor(ModuleToolMaterial.silverArmor, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("ss.silver_leggings");
        UtilRegistry.registerNormalItem(silverLeggings, "silver_leggings", "armor/silver_leggings");

        silverBoots = new ItemSSArmor(ModuleToolMaterial.silverArmor, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("ss.silver_boots");
        UtilRegistry.registerNormalItem(silverBoots, "silver_boots", "armor/silver_boots");

        //オリハルコン
        orichalcumHelmet = new ItemSSArmor(ModuleToolMaterial.orichalcumArmor, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("ss.orichalcum_helmet");
        UtilRegistry.registerNormalItem(orichalcumHelmet, "orichalcum_helmet", "armor/orichalcum_helmet");

        orichalcumChestplate = new ItemSSArmor(ModuleToolMaterial.orichalcumArmor, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("ss.orichalcum_chestplate");
        UtilRegistry.registerNormalItem(orichalcumChestplate, "orichalcum_chestplate", "armor/orichalcum_chestplate");

        orichalcumLeggings = new ItemSSArmor(ModuleToolMaterial.orichalcumArmor, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("ss.orichalcum_leggings");
        UtilRegistry.registerNormalItem(orichalcumLeggings, "orichalcum_leggings", "armor/orichalcum_leggings");

        orichalcumBoots = new ItemSSArmor(ModuleToolMaterial.orichalcumArmor, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("ss.orichalcum_boots");
        UtilRegistry.registerNormalItem(orichalcumBoots, "orichalcum_boots", "armor/orichalcum_boots");

        //林業
        treeBranch = new ItemSSBase().setUnlocalizedName("ss.tree_branch").setCreativeTab(SextiarySectorAPI.TabSSForestry);
        UtilRegistry.registerNormalItem(treeBranch, "tree_branch", "tree_branch");

        sapBottle = new ItemSapBottle().setUnlocalizedName("ss.sap_bottle");
        UtilRegistry.registerNormalItem(sapBottle, "sap_bottle", "fluid/sap_bottle");

        rubberBottle = new ItemSapBottle().setUnlocalizedName("ss.rubber_bottle");
        UtilRegistry.registerNormalItem(rubberBottle, "rubber_bottle", "fluid/rubber_bottle");

        mapleBottle = new ItemSapBottle().setUnlocalizedName("ss.maple_bottle");
        UtilRegistry.registerNormalItem(mapleBottle, "maple_bottle", "fluid/maple_bottle");

        //鉱業
        copperIngot = new ItemIngot().setUnlocalizedName("ss.copper_ingot");
        UtilRegistry.registerNormalItem(copperIngot, "copper_ingot", "ingot/copper_ingot");

        silverIngot = new ItemIngot().setUnlocalizedName("ss.silver_ingot");
        UtilRegistry.registerNormalItem(silverIngot, "silver_ingot", "ingot/silver_ingot");

        orichalcumGem = new ItemIngot().setUnlocalizedName("ss.orichalcum_gem");
        UtilRegistry.registerNormalItem(orichalcumGem, "orichalcum_gem", "gem/orichalcum_gem");

        //工業
        coalDust = new ItemDust().setUnlocalizedName("ss.coal_dust");
        UtilRegistry.registerNormalItem(coalDust, "coal_dust", "dust/coal_dust");

        ironDust = new ItemDust().setUnlocalizedName("ss.iron_dust");
        UtilRegistry.registerNormalItem(ironDust, "iron_dust", "dust/iron_dust");

        goldDust = new ItemDust().setUnlocalizedName("ss.gold_dust");
        UtilRegistry.registerNormalItem(goldDust, "gold_dust", "dust/gold_dust");

        copperDust = new ItemDust().setUnlocalizedName("ss.copper_dust");
        UtilRegistry.registerNormalItem(copperDust, "copper_dust", "dust/copper_dust");

        silverDust = new ItemDust().setUnlocalizedName("ss.silver_dust");
        UtilRegistry.registerNormalItem(silverDust, "silver_dust", "dust/silver_dust");

        plastic = new ItemSSBase().setUnlocalizedName("ss.plastic").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        UtilRegistry.registerNormalItem(plastic, "plastic", "plastic");

        rubber = new ItemSSBase().setUnlocalizedName("ss.rubber").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        UtilRegistry.registerNormalItem(rubber, "rubber", "rubber");

        blueGel = new ItemSSBase().setUnlocalizedName("ss.blue_gel").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        UtilRegistry.registerNormalItem(blueGel, "blue_gel", "gel/blue_gel");

        woodGear = new ItemSSBase().setUnlocalizedName("ss.wood_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        UtilRegistry.registerNormalItem(woodGear, "wood_gear", "gear/wood_gear");

        rubberGloves = new ItemRubberGroves().setUnlocalizedName("ss.rubber_gloves").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        UtilRegistry.registerToolItem(rubberGloves, "rubber_gloves", "tool/rubber_gloves");

        ductTape = new ItemSSBase().setUnlocalizedName("ss.duct_tape").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        UtilRegistry.registerNormalItem(ductTape, "duct_tape", "duct_tape");

        //薬学
        potionCapsule = new ItemPotionCapsule().setUnlocalizedName("ss.potion_capsule");
        UtilRegistry.registerNormalItem(potionCapsule, "potion_capsule", "capsule/potion_capsule");
        PotionHelper.registerPotionItem(new PotionHelper.ItemPredicateInstance(potionCapsule));

        creeperMemory = (ItemShopMemory) new ItemShopMemory("creeper").setUnlocalizedName("ss.creeper_memory");
        UtilRegistry.registerNormalItem(creeperMemory, "creeper_memory", "memory/creeper_memory");

    }
}
