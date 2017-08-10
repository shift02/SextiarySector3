package shift.sextiarysector3.item;

import net.minecraft.item.ItemFood;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class ItemSSFood extends ItemFood {

    public ItemSSFood(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setCreativeTab(SextiarySectorAPI.TabSSCooking);
    }

}
