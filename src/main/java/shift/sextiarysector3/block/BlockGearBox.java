package shift.sextiarysector3.block;

import javax.annotation.Nullable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.tileentity.TileEntityGearBox;
import shift.sextiarysector3.tileentity.gearbox.TileEntityWoodGearBox;

public class BlockGearBox extends BlockSSBase implements ITileEntityProvider {

    public static final PropertyBool POWER = PropertyBool.create("power");

    public static final PropertyEnum<ConnectionType> DOWN = PropertyEnum.create("down", ConnectionType.class);
    public static final PropertyEnum<ConnectionType> UP = PropertyEnum.create("up", ConnectionType.class);
    public static final PropertyEnum<ConnectionType> NORTH = PropertyEnum.create("north", ConnectionType.class);
    public static final PropertyEnum<ConnectionType> SOUTH = PropertyEnum.create("south", ConnectionType.class);
    public static final PropertyEnum<ConnectionType> WEST = PropertyEnum.create("west", ConnectionType.class);
    public static final PropertyEnum<ConnectionType> EAST = PropertyEnum.create("east", ConnectionType.class);

    public BlockGearBox(Material materialIn) {
        super(materialIn);
        this.blockState.getBaseState()
                .withProperty(POWER, Boolean.valueOf(false))
                .withProperty(DOWN, ConnectionType.NORMAL)
                .withProperty(UP, ConnectionType.NORMAL)
                .withProperty(NORTH, ConnectionType.NORMAL)
                .withProperty(SOUTH, ConnectionType.NORMAL)
                .withProperty(WEST, ConnectionType.NORMAL)
                .withProperty(EAST, ConnectionType.NORMAL);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY,
            float hitZ) {

        //if (worldIn.isRemote) return true;

        TileEntityGearBox te = (TileEntityGearBox) worldIn.getTileEntity(pos);
        te.changeConnect(side);

        return true;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        return state
                .withProperty(POWER, this.hsaPower(state, worldIn, pos))
                .withProperty(DOWN, this.getConnectType(state, worldIn, pos, EnumFacing.DOWN))
                .withProperty(UP, this.getConnectType(state, worldIn, pos, EnumFacing.UP))
                .withProperty(NORTH, this.getConnectType(state, worldIn, pos, EnumFacing.NORTH))
                .withProperty(SOUTH, this.getConnectType(state, worldIn, pos, EnumFacing.SOUTH))
                .withProperty(WEST, this.getConnectType(state, worldIn, pos, EnumFacing.WEST))
                .withProperty(EAST, this.getConnectType(state, worldIn, pos, EnumFacing.EAST));

    }

    public boolean hsaPower(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    public ConnectionType getConnectType(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing f) {

        //return ConnectionType.NORMAL;

        TileEntityGearBox te = (TileEntityGearBox) worldIn.getTileEntity(pos);

        if (te == null) return ConnectionType.NORMAL;

        return te.getConnectionType(f);

        /*
        if (te == null) return false;
        
        return te.hasCapability(CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY, f.getOpposite());*/

    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { POWER, DOWN, UP, NORTH, SOUTH, WEST, EAST });
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityWoodGearBox();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

}
