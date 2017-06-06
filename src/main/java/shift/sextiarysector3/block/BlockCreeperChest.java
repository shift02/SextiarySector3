package shift.sextiarysector3.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector3.tileentity.TileEntityCreeperChest;

public class BlockCreeperChest extends BlockSSChest {

    public BlockCreeperChest() {
        super(Type.BASIC);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCreeperChest();
    }

}
