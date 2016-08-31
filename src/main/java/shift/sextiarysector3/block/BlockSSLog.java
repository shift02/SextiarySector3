package shift.sextiarysector3.block;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class BlockSSLog extends BlockLog {

	public BlockSSLog() {
		super();

		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));

		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS });
	}

	public IBlockState getStateFromMeta(int meta) {
		BlockLog.EnumAxis enumfacing$axis = BlockLog.EnumAxis.Y;
		int i = meta & 12;

		if (i == 4) {
			enumfacing$axis = BlockLog.EnumAxis.X;
		} else if (i == 8) {
			enumfacing$axis = BlockLog.EnumAxis.Z;
		}

		return this.getDefaultState().withProperty(LOG_AXIS, enumfacing$axis);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		BlockLog.EnumAxis enumfacing$axis = (BlockLog.EnumAxis) state.getValue(LOG_AXIS);

		if (enumfacing$axis == BlockLog.EnumAxis.X) {
			i |= 4;
		} else if (enumfacing$axis == BlockLog.EnumAxis.Z) {
			i |= 8;
		}

		return i;
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getStateFromMeta(meta).withProperty(LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis()));
	}

}
