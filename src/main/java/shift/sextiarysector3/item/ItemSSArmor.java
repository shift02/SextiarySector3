package shift.sextiarysector3.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class ItemSSArmor extends ItemArmor {

    private static String modelRoot = SextiarySector3.MODID + ":" + "textures/models/armor/";

    public ItemSSArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(SextiarySectorAPI.TabSSCore);

    }

    /*
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
    
        String s1 = String.format(modelRoot + "%s_layer_%d%s.png",
                this.getArmorMaterial().name(),
                (EntityEquipmentSlot.LEGS.equals(slot) ? 2 : 1),
                type == null ? "" : String.format("_%s", type));
    
        return s1;
    
    }*/

}
