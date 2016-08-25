package shift.sextiarysector3.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class BlockSSOre extends BlockSSBase {

	public BlockSSOre() {
		super(Material.ROCK);
		this.setCreativeTab(SextiarySectorAPI.TabSSMining);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

}
