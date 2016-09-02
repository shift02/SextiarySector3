package shift.sextiarysector3.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class ItemSSShield extends ItemShield {

	public ItemSSShield() {
		super();
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	@SideOnly(Side.CLIENT)
	public CreativeTabs getCreativeTab() {
		return SextiarySectorAPI.TabSSCore;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		//ItemBanner.appendHoverTextFromTileEntityTag(stack, tooltip);
	}

	public String getItemStackDisplayName(ItemStack stack) {
		return ("" + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
	}

}
