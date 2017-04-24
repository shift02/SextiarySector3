package shift.sextiarysector3.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import shift.sextiarysector3.api.energy.CapabilityGearForceHandler;

public class BlockGearBox extends BlockSSBase {

    public static final PropertyBool POWER = PropertyBool.create("power");

    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool EAST = PropertyBool.create("east");

    public BlockGearBox(Material materialIn) {
        super(materialIn);
        this.blockState.getBaseState()
                .withProperty(POWER, Boolean.valueOf(false))
                .withProperty(DOWN, Boolean.valueOf(false))
                .withProperty(UP, Boolean.valueOf(false))
                .withProperty(NORTH, Boolean.valueOf(false))
                .withProperty(SOUTH, Boolean.valueOf(false))
                .withProperty(WEST, Boolean.valueOf(false))
                .withProperty(EAST, Boolean.valueOf(false));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        return state
                .withProperty(POWER, this.hsaPower(state, worldIn, pos))
                .withProperty(DOWN, this.isConnect(state, worldIn, pos, EnumFacing.DOWN))
                .withProperty(UP, this.isConnect(state, worldIn, pos, EnumFacing.UP))
                .withProperty(NORTH, this.isConnect(state, worldIn, pos, EnumFacing.NORTH))
                .withProperty(SOUTH, this.isConnect(state, worldIn, pos, EnumFacing.SOUTH))
                .withProperty(WEST, this.isConnect(state, worldIn, pos, EnumFacing.WEST))
                .withProperty(EAST, this.isConnect(state, worldIn, pos, EnumFacing.EAST));

    }

    public boolean hsaPower(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    public boolean isConnect(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing f) {

        TileEntity te = worldIn.getTileEntity(pos.offset(f));

        if (te == null) return false;

        return te.hasCapability(CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY, f.getOpposite());

    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { POWER, DOWN, UP, NORTH, SOUTH, WEST, EAST });
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

}
