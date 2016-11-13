package shift.sextiarysector3.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class BlockSSOre extends BlockSSBase {

    public BlockSSOre(int level) {
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", level);
        this.setCreativeTab(SextiarySectorAPI.TabSSMining);
        this.setHardness(3.0f);
        this.setResistance(5.0f);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

}
