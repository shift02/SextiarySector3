package shift.sextiarysector3.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockConveyor extends BlockSSHorizontal {

    public static final PropertyBool LEFT = PropertyBool.create("left");
    public static final PropertyBool RIGHT = PropertyBool.create("right");

    protected static final AxisAlignedBB CONVEYOR_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);

    public BlockConveyor(Material materialIn) {
        super(materialIn);
        this.setDefaultState(this.getDefaultState().withProperty(LEFT, Boolean.valueOf(false)).withProperty(RIGHT, Boolean.valueOf(false)));
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { super.FACING, LEFT, RIGHT });
    }

    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        return state.withProperty(LEFT, isLeftConnect(state, worldIn, pos)).withProperty(RIGHT, isRightConnect(state, worldIn, pos));

    }

    public boolean isLeftConnect(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        EnumFacing f = state.getValue(super.FACING);

        f = f.rotateY();
        IBlockState bs = worldIn.getBlockState(pos.offset(f));
        if (!(bs.getBlock() instanceof BlockConveyor)) return false;
        if (bs.getValue(super.FACING) != f.getOpposite()) return false;

        return true;

    }

    public boolean isRightConnect(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        EnumFacing f = state.getValue(super.FACING);

        f = f.rotateY().getOpposite();
        IBlockState bs = worldIn.getBlockState(pos.offset(f));
        if (!(bs.getBlock() instanceof BlockConveyor)) return false;
        if (bs.getValue(super.FACING) != f.getOpposite()) return false;

        return true;

    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {

        return CONVEYOR_AABB;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {

        return CONVEYOR_AABB.offset(pos);
    }

}
