package shift.sextiarysector3.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockConveyor extends BlockSSHorizontal {

    public BlockConveyor(Material materialIn) {
        super(materialIn);
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

}
