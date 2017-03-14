package shift.sextiarysector3.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector3.tileentity.TileEntityCreativeGFTank;

public class BlockCreativeGF extends BlockSSBase implements ITileEntityProvider {

    public BlockCreativeGF() {
        super(Material.WOOD);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCreativeGFTank();
    }

}
