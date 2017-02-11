package shift.sextiarysector3.module;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.SextiarySector3;

public class ModuleToolMaterial implements IModule {

    private static ModuleToolMaterial instance;

    private ModuleToolMaterial() {
    }

    public static ModuleToolMaterial getInstance() {
        if (instance == null) {
            instance = new ModuleToolMaterial();
        }
        return instance;
    }

    public static ToolMaterial copperTool;
    public static ToolMaterial silverTool;
    public static ToolMaterial orichalcumTool;
    public static ToolMaterial brassTool;
    public static ToolMaterial ninjaTool;

    public static ArmorMaterial copperArmor;
    public static ArmorMaterial silverArmor;
    public static ArmorMaterial orichalcumArmor;
    public static ArmorMaterial ninjaArmor;

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        //レベル 耐久 スピード Power Enchant
        copperTool = EnumHelper.addToolMaterial("copper", 2, 200, 4.0F, 1.0F, 10);
        silverTool = EnumHelper.addToolMaterial("silver", 0, 32, 12.0F, 0.0F, 22);
        orichalcumTool = EnumHelper.addToolMaterial("orichalcum", 4, 2161, 12.5F, 5.2F, 22);

        brassTool = EnumHelper.addToolMaterial("brass", 2, 660, 7.0F, 3.0F, 8);
        ninjaTool = EnumHelper.addToolMaterial("ninja", 4, 1172, 12.5F, 5.0F, 19);

        //耐久 それぞれの防御力 Enchant
        copperArmor = EnumHelper.addArmorMaterial("copper", SextiarySector3.MODID + ":" + "copper", 13, new int[] { 2, 4, 4, 2 }, 7,
                SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
        silverArmor = EnumHelper.addArmorMaterial("silver", SextiarySector3.MODID + ":" + "silver", 7, new int[] { 1, 3, 5, 2 }, 7,
                SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0);

        orichalcumArmor = EnumHelper.addArmorMaterial("silver", SextiarySector3.MODID + ":" + "orichalcum", 50, new int[] { 4, 7, 9, 4 }, 14,
                SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0f);

        ninjaArmor = EnumHelper.addArmorMaterial("ninja", null, 29, new int[] { 4, 9, 6, 4 }, 22, null, 0);

    }

    @Override
    public void load(FMLInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void loadClient(FMLInitializationEvent event) {

    }

}
