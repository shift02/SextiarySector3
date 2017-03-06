package shift.sextiarysector3.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import shift.sextiarysector3.tileentity.TileEntityShaft;

public class BlockShaft extends BlockSSDirectional implements ITileEntityProvider {

    public BlockShaft(Material materialIn) {
        super(materialIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    /*
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY,
            float hitZ) {
    
        if (worldIn.isRemote) return true;
    
        ((TileEntityShaft) worldIn.getTileEntity(pos)).speed += 5;
        if (((TileEntityShaft) worldIn.getTileEntity(pos)).speed > 50) ((TileEntityShaft) worldIn.getTileEntity(pos)).speed = 0;
    
        return true;
    }*/

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityShaft();
    }

}
