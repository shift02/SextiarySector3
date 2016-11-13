package shift.sextiarysector3.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSanctuary extends BlockSSBase {

    public BlockSanctuary() {
        super(Material.ROCK);
        this.setHardness(3.0f);
        this.setResistance(50.0f);

    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, net.minecraft.entity.EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {

        if (entityIn instanceof EntityPlayer) {
            super.onEntityWalk(worldIn, pos, entityIn);
            return;
        }

        entityIn.addVelocity(
                -MathHelper.sin((entityIn.rotationYaw + 180.0F) * (float) Math.PI / 180.0F) * 0.8F,
                -0.2D,
                MathHelper.cos((entityIn.rotationYaw + 180.0F) * (float) Math.PI / 180.0F) * 0.8F);

    }

    /*@Deprecated
    public boolean func_189872_a(IBlockState p_189872_1_, Entity p_189872_2_) {
    	return false;
    }
    
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    	return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);//NULL_AABB;
    }
    
    @Deprecated
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes,
    		@Nullable Entity entityIn) {
    
    	if (entityIn instanceof EntityPlayer) collidingBoxes.add(FULL_BLOCK_AABB);
    
    	return;
    
    	//addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(worldIn, pos));
    }
    
    protected static void addCollisionBoxToList(BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable AxisAlignedBB blockBox) {
    	if (blockBox != NULL_AABB) {
    		AxisAlignedBB axisalignedbb = blockBox.offset(pos);
    
    		if (entityBox.intersectsWith(axisalignedbb)) {
    			collidingBoxes.add(axisalignedbb);
    		}
    	}
    }
    
    public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
    
    	return false;//worldIn.getBlockState(pos).getMaterial().isSolid();
    }
    
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
    	return false;
    }
    
    //	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
    //		return false;
    //	}
     *
     * */

}
