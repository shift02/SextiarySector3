package shift.sextiarysector3.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.api.energy.CapabilityGearForceHandler;
import shift.sextiarysector3.tileentity.TileEntityConveyor;

public class BlockConveyor extends BlockSSHorizontal implements ITileEntityProvider {

    public static final PropertyBool LEFT = PropertyBool.create("left");
    public static final PropertyBool RIGHT = PropertyBool.create("right");

    public static final PropertyBool POWER = PropertyBool.create("power");

    public static final PropertyBool GF_LEFT = PropertyBool.create("gf_left");
    public static final PropertyBool GF_RIGHT = PropertyBool.create("gf_right");

    protected static final AxisAlignedBB CONVEYOR_SELECTED_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25, 1.0D);
    protected static final AxisAlignedBB CONVEYOR_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);

    //角
    protected static final AxisAlignedBB CONVEYOR_EDGE_AABB0 = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 0.125D);
    protected static final AxisAlignedBB CONVEYOR_EDGE_AABB1 = new AxisAlignedBB(0.875, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
    protected static final AxisAlignedBB CONVEYOR_EDGE_AABB2 = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 0.125D, 1.0D, 1.0D);
    protected static final AxisAlignedBB CONVEYOR_EDGE_AABB3 = new AxisAlignedBB(0.875D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);

    public BlockConveyor(Material materialIn) {
        super(materialIn);
        this.setDefaultState(
                this.getDefaultState()
                        .withProperty(LEFT, Boolean.valueOf(false))
                        .withProperty(RIGHT, Boolean.valueOf(false))
                        .withProperty(POWER, Boolean.valueOf(false))
                        .withProperty(GF_LEFT, Boolean.valueOf(false))
                        .withProperty(GF_RIGHT, Boolean.valueOf(false)));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { super.FACING, LEFT, RIGHT, POWER, GF_LEFT, GF_RIGHT });
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        TileEntityConveyor te = (TileEntityConveyor) worldIn.getTileEntity(pos);

        return state
                .withProperty(LEFT, isLeftConnect(state, worldIn, pos))
                .withProperty(RIGHT, isRightConnect(state, worldIn, pos))
                .withProperty(POWER, te.hasPower())
                .withProperty(GF_LEFT, isLeftGFConnect(state, worldIn, pos))
                .withProperty(GF_RIGHT, isRightGFConnect(state, worldIn, pos));

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

    public boolean isLeftGFConnect(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        EnumFacing f = state.getValue(super.FACING);

        f = f.rotateY();
        TileEntity tE = worldIn.getTileEntity(pos.offset(f));
        if (tE == null) return false;
        if (!tE.hasCapability(CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY, f.getOpposite())) return false;

        return true;

    }

    public boolean isRightGFConnect(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        EnumFacing f = state.getValue(super.FACING);

        f = f.rotateY().getOpposite();
        TileEntity tE = worldIn.getTileEntity(pos.offset(f));
        if (tE == null) return false;
        if (!tE.hasCapability(CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY, f.getOpposite())) return false;

        return true;

    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn);
        state = state.getActualState(worldIn, pos);

        //addCollisionBoxToList(pos, entityBox, collidingBoxes, CONVEYOR_EDGE_AABB0);
        //addCollisionBoxToList(pos, entityBox, collidingBoxes, CONVEYOR_EDGE_AABB1);
        //addCollisionBoxToList(pos, entityBox, collidingBoxes, CONVEYOR_EDGE_AABB2);
        //addCollisionBoxToList(pos, entityBox, collidingBoxes, CONVEYOR_EDGE_AABB3);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CONVEYOR_SELECTED_AABB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {

        return CONVEYOR_AABB;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {

        return CONVEYOR_SELECTED_AABB.offset(pos);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityConveyor();
    }

}
