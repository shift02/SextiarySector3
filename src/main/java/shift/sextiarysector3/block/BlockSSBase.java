package shift.sextiarysector3.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class BlockSSBase extends Block {

	public BlockSSBase(Material materialIn) {
		super(materialIn);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	public BlockSSBase(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

}
