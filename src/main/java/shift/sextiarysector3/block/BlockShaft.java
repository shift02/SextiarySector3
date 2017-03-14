package shift.sextiarysector3.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.tileentity.TileEntityShaft;
import shift.sextiarysector3.util.UtilFacing;

public class BlockShaft extends BlockSSDirectional implements ITileEntityProvider {

    protected static final AxisAlignedBB SHAFT_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1D, 0.75D);
    protected static final AxisAlignedBB SHAFT_AABB1 = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1D);
    protected static final AxisAlignedBB SHAFT_AABB2 = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 1D, 0.75D, 0.75D);

    public BlockShaft(Material materialIn) {
        super(materialIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityShaft();
    }

    //当たり判定。サボテンやソウルサンドを参考にすると良い。ココの設定をすると、onEntityCollidedWithBlockが呼ばれるようになる
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {

        return this.getBoxFromPool(blockState, worldIn, pos);

    }

    //ブロックに視点を合わせた時に出てくる黒い線のアレ
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {

        return this.getBoxFromPool(state, worldIn, pos).offset(pos);
    }

    public AxisAlignedBB getBoxFromPool(IBlockState state, World par1World, BlockPos pos) {

        EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
        switch (enumfacing) {
        case UP:
        case DOWN:
            return SHAFT_AABB;
        case WEST:
        case EAST:
            return UtilFacing.rotationAxisAlignedBB(SHAFT_AABB, EnumFacing.Axis.X);
        case SOUTH:
        default:
            return UtilFacing.rotationAxisAlignedBB(SHAFT_AABB, EnumFacing.Axis.Z);
        }

    }

}
