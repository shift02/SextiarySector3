package shift.sextiarysector3.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISpanner {

    public boolean canUse(ItemStack item, EntityPlayer player, int damage);

    public boolean use(ItemStack item, EntityPlayer player, int damage);

}
