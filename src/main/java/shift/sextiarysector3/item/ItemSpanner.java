package shift.sextiarysector3.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import shift.sextiarysector3.api.ISpanner;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class ItemSpanner extends ItemSSBase implements ISpanner {

    public ItemSpanner() {

        super();
        this.setHarvestLevel("spanner", 1);
        this.setMaxStackSize(1);
        this.setMaxDamage(140);

        this.setCreativeTab(SextiarySectorAPI.TabSSIndustry);

    }

    @Override
    public boolean canUse(ItemStack item, EntityPlayer player, int damage) {
        return true;
    }

    @Override
    public boolean use(ItemStack item, EntityPlayer player, int damage) {
        item.damageItem(damage, player);

        return true;
    }
}
