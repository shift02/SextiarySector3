package shift.sextiarysector3.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnderCard extends ItemSSBase {

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {

		if (!stack.hasTagCompound()) return;

		NBTTagCompound nbt = stack.getTagCompound();

		tooltip.add("X:" + nbt.getInteger("x") + ", Y:" + nbt.getInteger("y") + ", Z:" + nbt.getInteger("z"));

	}

}
