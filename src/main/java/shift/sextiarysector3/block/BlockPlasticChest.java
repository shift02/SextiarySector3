package shift.sextiarysector3.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.tileentity.TileEntityPlasticChest;

public class BlockPlasticChest extends BlockSSChest {

    public BlockPlasticChest() {
        super(Type.PLASTIC);

        this.setCreativeTab(SextiarySectorAPI.TabSSIndustry);

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPlasticChest();
    }
}
