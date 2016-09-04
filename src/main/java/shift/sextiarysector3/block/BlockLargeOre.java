package shift.sextiarysector3.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLargeOre extends BlockSSOre {

	private final Item oreItem;
	private final Block oreBlock;

	public BlockLargeOre(Item item, Block block, int level) {
		super(level);
		this.oreItem = item;
		this.oreBlock = block;
		this.setResistance(5.0F);
		this.setHardness(3.0F);

	}

	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {

		return oreItem;

	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {

		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		Random rand = world instanceof World ? ((World) world).rand : RANDOM;

		int count = quantityDropped(state, fortune, rand);
		for (int i = 0; i < count; i++) {
			Item item = getItemDropped(state, rand, fortune);
			if (item != null) {
				ret.add(new ItemStack(item, 1, damageDropped(state)));
				if (oreBlock == Blocks.COAL_ORE) {
					ret.add(new ItemStack(Items.COAL, 2));
				} else {
					ret.add(new ItemStack(oreBlock, 1, damageDropped(state)));
				}

			}
		}
		return ret;
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

}
