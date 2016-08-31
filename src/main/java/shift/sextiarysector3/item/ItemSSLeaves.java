package shift.sextiarysector3.item;

import net.minecraft.block.BlockLeaves;
import net.minecraft.item.ItemBlock;

public class ItemSSLeaves extends ItemBlock {
	private final BlockLeaves leaves;

	public ItemSSLeaves(BlockLeaves block) {
		super(block);
		this.leaves = block;
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	/**
	 * Converts the given ItemStack damage value into a metadata value to be placed in the world when this Item is
	 * placed as a Block (mostly used with ItemBlocks).
	 */
	public int getMetadata(int damage) {
		return damage | 4;
	}

}