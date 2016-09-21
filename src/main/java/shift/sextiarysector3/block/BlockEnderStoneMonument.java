package shift.sextiarysector3.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector3.SSItems;

public class BlockEnderStoneMonument extends BlockSSBase {

	protected static final AxisAlignedBB FRAME_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.00D, 1.0D);

	public BlockEnderStoneMonument() {
		super(Material.ROCK);
		//this.setTickRandomly(true);
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		worldIn.scheduleBlockUpdate(pos, state.getBlock(), this.tickRate(worldIn), 0);

	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

		worldIn.scheduleBlockUpdate(pos, state.getBlock(), this.tickRate(worldIn), 0);
		super.updateTick(worldIn, pos, state, rand);

		//if (!worldIn.isRemote) {

		for (EnumFacing f : EnumFacing.HORIZONTALS) {
			setEnder(worldIn, pos.offset(f), state, rand, f);
		}

		//}
	}

	public void setEnder(World worldIn, BlockPos pos, IBlockState state, Random rand, EnumFacing f) {

		AxisAlignedBB axisalignedbb = FRAME_AABB.offset(pos);

		List<? extends EntityHanging> list = worldIn.getEntitiesWithinAABB(EntityHanging.class, axisalignedbb);

		for (EntityHanging eH : list) {

			if (!eH.getHorizontalFacing().equals(f)) continue;

			if (!(eH instanceof EntityItemFrame)) continue;

			EntityItemFrame eF = (EntityItemFrame) eH;
			if (eF.getDisplayedItem() == null) continue;
			if (!worldIn.isRemote) changeSilver(eF, eF.getDisplayedItem(), worldIn, pos, state, rand, f);
			if (worldIn.isRemote) changeSilverC(eF, eF.getDisplayedItem(), worldIn, pos, state, rand);
		}

	}

	public void changeSilver(EntityItemFrame eF, ItemStack item, World worldIn, BlockPos pos, IBlockState state, Random rand, EnumFacing f) {

		for (ItemStack silver : OreDictionary.getOres("ingotSilver")) {
			if (!OreDictionary.itemMatches(silver, item, false)) continue;

			System.out.println("Hit");

			ItemStack card = new ItemStack(SSItems.enderCard);

			if (!card.hasTagCompound()) {
				card.setTagCompound(new NBTTagCompound());
			}

			if (!card.getTagCompound().hasKey("effect")) {
				card.getTagCompound().setInteger("effect", 10);

			}

			card.getTagCompound().setBoolean("power", true);

			card.getTagCompound().setInteger("x", pos.getX());
			card.getTagCompound().setInteger("y", pos.getY());
			card.getTagCompound().setInteger("z", pos.getZ());
			card.getTagCompound().setInteger("facing", f.getIndex());

			eF.setDisplayedItem(card);

			return;

		}

	}

	public void changeSilverC(EntityItemFrame eF, ItemStack item, World worldIn, BlockPos pos, IBlockState state, Random rand) {

		if (!item.hasTagCompound()) return;
		if (!item.getTagCompound().hasKey("effect")) return;
		int time = item.getTagCompound().getInteger("effect");
		if (time == 0) {
			item.getTagCompound().removeTag("effect");
			return;
		}

		item.getTagCompound().setInteger("effect", time - 1);

		for (int i = 0; i < 32; ++i) {
			worldIn.spawnParticle(EnumParticleTypes.PORTAL, pos.getX() + 0.5D, pos.getY() + rand.nextDouble() * 2.0D, pos.getZ() + 0.5D, rand.nextGaussian(),
					0.0D, rand.nextGaussian(), new int[0]);
		}

	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		for (EnumFacing f : EnumFacing.HORIZONTALS) {
			setEnder(worldIn, pos.offset(f), worldIn.getBlockState(pos), rand, f);
		}

	}

}
