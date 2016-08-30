package shift.sextiarysector3.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector3.module.ModuleSap;

public class BlockSpile extends BlockSSBase {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	protected static final AxisAlignedBB TORCH_NORTH_AABB = new AxisAlignedBB(0.375D, 0.25, 0.6875, 0.625, 0.5, 1.0D);
	protected static final AxisAlignedBB TORCH_SOUTH_AABB = new AxisAlignedBB(0.375D, 0.25, 0.0D, 0.625, 0.5, 0.3125D);
	protected static final AxisAlignedBB TORCH_WEST_AABB = new AxisAlignedBB(0.6875, 0.25, 0.375D, 1.0D, 0.5, 0.625D);
	protected static final AxisAlignedBB TORCH_EAST_AABB = new AxisAlignedBB(0.000D, 0.25, 0.375, 0.3125, 0.5, 0.625D);

	public BlockSpile() {
		super(Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setLightLevel(0.4F);
		this.setTickRandomly(true);
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);

			if (this.isSap(worldIn, pos, state, rand) && rand.nextInt(7) == 0) {
				this.addSap(worldIn, pos, state, rand);
			}
		}
	}

	public boolean isSap(World worldIn, BlockPos pos, IBlockState state, Random rand) {

		if (!(ModuleSap.getInstance().isSapBlock(worldIn.getBlockState(pos.offset((EnumFacing) state.getValue(FACING).getOpposite())).getBlock())))
			return false;

		return true;
	}

	public void addSap(World worldIn, BlockPos pos, IBlockState state, Random rand) {

		BlockPos cP = pos.down();
		Block block = worldIn.getBlockState(cP).getBlock();

		BlockSapCauldron cPB = ModuleSap.getInstance()
				.getSapCauldron(worldIn.getBlockState(pos.offset((EnumFacing) state.getValue(FACING).getOpposite())).getBlock());

		if (cPB == null) return;

		if (!(block == Blocks.CAULDRON || block instanceof BlockSapCauldron)) return;

		if (block == Blocks.CAULDRON) {

			worldIn.setBlockState(cP, cPB.getDefaultState());
			cPB.addWaterLevel(worldIn, cP, worldIn.getBlockState(cP), 1);

		}

		if (cPB.equals(block)) {
			cPB.addWaterLevel(worldIn, cP, worldIn.getBlockState(cP), 1);
		}

	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		switch ((EnumFacing) state.getValue(FACING)) {
		case EAST:
			return TORCH_EAST_AABB;
		case WEST:
			return TORCH_WEST_AABB;
		case SOUTH:
			return TORCH_SOUTH_AABB;
		case NORTH:
			return TORCH_NORTH_AABB;
		default:
			break;
		}
		return null;

	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		for (EnumFacing enumfacing : FACING.getAllowedValues()) {
			if (this.canPlaceAt(worldIn, pos, enumfacing)) {
				return true;
			}
		}

		return false;
	}

	private boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing) {
		BlockPos blockpos = pos.offset(facing.getOpposite());
		boolean flag = facing.getAxis().isHorizontal();
		return flag && worldIn.isSideSolid(blockpos, facing, true);
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if (this.canPlaceAt(worldIn, pos, facing)) {
			return this.getDefaultState().withProperty(FACING, facing);
		} else {
			for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
				if (worldIn.isSideSolid(pos.offset(enumfacing.getOpposite()), enumfacing, true)) {
					return this.getDefaultState().withProperty(FACING, enumfacing);
				}
			}

			return this.getDefaultState();
		}
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.checkForDrop(worldIn, pos, state);
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		this.onNeighborChangeInternal(worldIn, pos, state);
	}

	protected boolean onNeighborChangeInternal(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.checkForDrop(worldIn, pos, state)) {
			return true;
		} else {
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
			EnumFacing.Axis enumfacing$axis = enumfacing.getAxis();
			EnumFacing enumfacing1 = enumfacing.getOpposite();
			boolean flag = false;

			if (enumfacing$axis.isHorizontal() && !worldIn.isSideSolid(pos.offset(enumfacing1), enumfacing, true)) {
				flag = true;
			}

			if (flag) {
				this.dropBlockAsItem(worldIn, pos, state, 0);
				worldIn.setBlockToAir(pos);
				return true;
			} else {
				return false;
			}
		}
	}

	protected boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getBlock() == this && this.canPlaceAt(worldIn, pos, (EnumFacing) state.getValue(FACING))) {
			return true;
		} else {
			if (worldIn.getBlockState(pos).getBlock() == this) {
				this.dropBlockAsItem(worldIn, pos, state, 0);
				worldIn.setBlockToAir(pos);
			}

			return false;
		}
	}

	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

}
