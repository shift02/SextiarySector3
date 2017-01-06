package shift.sextiarysector3.item;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemRubberGroves extends ItemSSBase {

    public ItemRubberGroves() {

        super();
        this.setMaxDamage(16);

    }

    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        //System.out.println(hitX + " : " + hitY + " : " + hitZ);

        EnumFacing nextFacing = EnumFacing.NORTH;

        float lim = 0.25f;

        if (facing.equals(EnumFacing.UP) || facing.equals(EnumFacing.DOWN)) {

            if ((hitX <= lim && hitZ <= lim) || (hitX >= lim + 0.5 && hitZ <= lim) || (hitX <= lim && hitZ >= lim + 0.5) || (hitX >= lim + 0.5 && hitZ >= lim + 0.5))
                nextFacing = facing.getOpposite();
            else if (hitX <= lim)
                nextFacing = EnumFacing.WEST;
            else if (hitX >= lim + 0.5)
                nextFacing = EnumFacing.EAST;
            else if (hitZ <= lim)
                nextFacing = EnumFacing.NORTH;
            else if (hitZ >= lim + 0.5)
                nextFacing = EnumFacing.SOUTH;
            else
                nextFacing = facing;

        }

        if (facing.equals(EnumFacing.NORTH) || facing.equals(EnumFacing.SOUTH)) {

            if ((hitX <= lim && hitY <= lim) || (hitX >= lim + 0.5 && hitY <= lim) || (hitX <= lim && hitY >= lim + 0.5) || (hitX >= lim + 0.5 && hitY >= lim + 0.5))
                nextFacing = facing.getOpposite();
            else if (hitX <= lim)
                nextFacing = EnumFacing.WEST;
            else if (hitX >= lim + 0.5)
                nextFacing = EnumFacing.EAST;
            else if (hitY <= lim)
                nextFacing = EnumFacing.DOWN;
            else if (hitY >= lim + 0.5)
                nextFacing = EnumFacing.UP;
            else
                nextFacing = facing;

        }

        if (facing.equals(EnumFacing.WEST) || facing.equals(EnumFacing.EAST)) {

            if ((hitZ <= lim && hitY <= lim) || (hitZ >= lim + 0.5 && hitY <= lim) || (hitZ <= lim && hitY >= lim + 0.5) || (hitZ >= lim + 0.5 && hitY >= lim + 0.5))
                nextFacing = facing.getOpposite();
            else if (hitY <= lim)
                nextFacing = EnumFacing.DOWN;
            else if (hitY >= lim + 0.5)
                nextFacing = EnumFacing.UP;
            else if (hitZ <= lim)
                nextFacing = EnumFacing.NORTH;
            else if (hitZ >= lim + 0.5)
                nextFacing = EnumFacing.SOUTH;
            else
                nextFacing = facing;

        }

        //worldIn.getBlockState(pos).getBlock().rotateBlock(worldIn, pos, nextFacing);

        for (IProperty<?> prop : worldIn.getBlockState(pos).getProperties().keySet()) {

            if (prop.getName().equals("facing") && prop instanceof PropertyDirection) {
                try {
                    IBlockState state = worldIn.getBlockState(pos);
                    worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty((PropertyDirection) prop, nextFacing));
                    SoundType sound = state.getBlock().getSoundType(state, worldIn, pos, playerIn);
                    worldIn.playSound((EntityPlayer) null, pos, sound.getStepSound(), SoundCategory.BLOCKS, 1.0F,
                            worldIn.rand.nextFloat() * 0.1F + 0.6F);
                    stack.damageItem(1, playerIn);
                    return EnumActionResult.SUCCESS;
                } catch (IllegalArgumentException e) {

                }

                //return true;
            }
        }
        //worldIn.getBlockState(pos)

        //System.out.println(nextFacing);

        return EnumActionResult.SUCCESS;

    }

    /*
    protected RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids) {
        float f = playerIn.rotationPitch;
        float f1 = playerIn.rotationYaw;
        double d0 = playerIn.posX;
        double d1 = playerIn.posY + (double) playerIn.getEyeHeight();
        double d2 = playerIn.posZ;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d3 = 5.0D;
        if (playerIn instanceof net.minecraft.entity.player.EntityPlayerMP) {
            d3 = ((net.minecraft.entity.player.EntityPlayerMP) playerIn).interactionManager.getBlockReachDistance();
        }
        Vec3d vec3d1 = vec3d.addVector((double) f6 * d3, (double) f5 * d3, (double) f7 * d3);
        return this.rayTraceBlocks(worldIn, vec3d, vec3d1, useLiquids, !useLiquids, false);
    }*/

    //ワールドから 946
    @Nullable
    public RayTraceResult rayTraceBlocks(World world, Vec3d vec31, Vec3d vec32, boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock) {

        AxisAlignedBB FULL_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

        if (!Double.isNaN(vec31.xCoord) && !Double.isNaN(vec31.yCoord) && !Double.isNaN(vec31.zCoord)) {
            if (!Double.isNaN(vec32.xCoord) && !Double.isNaN(vec32.yCoord) && !Double.isNaN(vec32.zCoord)) {
                int i = MathHelper.floor_double(vec32.xCoord);
                int j = MathHelper.floor_double(vec32.yCoord);
                int k = MathHelper.floor_double(vec32.zCoord);
                int l = MathHelper.floor_double(vec31.xCoord);
                int i1 = MathHelper.floor_double(vec31.yCoord);
                int j1 = MathHelper.floor_double(vec31.zCoord);
                BlockPos blockpos = new BlockPos(l, i1, j1);
                IBlockState iblockstate = world.getBlockState(blockpos);
                Block block = iblockstate.getBlock();

                if ((!ignoreBlockWithoutBoundingBox || iblockstate.getCollisionBoundingBox(world, blockpos) != Block.NULL_AABB) && block.canCollideCheck(iblockstate, stopOnLiquid)) {
                    RayTraceResult raytraceresult = this.collisionRayTrace(iblockstate, world, blockpos, vec31, vec32);

                    if (raytraceresult != null) {
                        return raytraceresult;
                    }
                }

                RayTraceResult raytraceresult2 = null;
                int k1 = 200;

                while (k1-- >= 0) {
                    if (Double.isNaN(vec31.xCoord) || Double.isNaN(vec31.yCoord) || Double.isNaN(vec31.zCoord)) {
                        return null;
                    }

                    if (l == i && i1 == j && j1 == k) {
                        return returnLastUncollidableBlock ? raytraceresult2 : null;
                    }

                    boolean flag2 = true;
                    boolean flag = true;
                    boolean flag1 = true;
                    double d0 = 999.0D;
                    double d1 = 999.0D;
                    double d2 = 999.0D;

                    if (i > l) {
                        d0 = (double) l + 1.0D;
                    } else if (i < l) {
                        d0 = (double) l + 0.0D;
                    } else {
                        flag2 = false;
                    }

                    if (j > i1) {
                        d1 = (double) i1 + 1.0D;
                    } else if (j < i1) {
                        d1 = (double) i1 + 0.0D;
                    } else {
                        flag = false;
                    }

                    if (k > j1) {
                        d2 = (double) j1 + 1.0D;
                    } else if (k < j1) {
                        d2 = (double) j1 + 0.0D;
                    } else {
                        flag1 = false;
                    }

                    double d3 = 999.0D;
                    double d4 = 999.0D;
                    double d5 = 999.0D;
                    double d6 = vec32.xCoord - vec31.xCoord;
                    double d7 = vec32.yCoord - vec31.yCoord;
                    double d8 = vec32.zCoord - vec31.zCoord;

                    if (flag2) {
                        d3 = (d0 - vec31.xCoord) / d6;
                    }

                    if (flag) {
                        d4 = (d1 - vec31.yCoord) / d7;
                    }

                    if (flag1) {
                        d5 = (d2 - vec31.zCoord) / d8;
                    }

                    if (d3 == -0.0D) {
                        d3 = -1.0E-4D;
                    }

                    if (d4 == -0.0D) {
                        d4 = -1.0E-4D;
                    }

                    if (d5 == -0.0D) {
                        d5 = -1.0E-4D;
                    }

                    EnumFacing enumfacing;

                    if (d3 < d4 && d3 < d5) {
                        enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
                        vec31 = new Vec3d(d0, vec31.yCoord + d7 * d3, vec31.zCoord + d8 * d3);
                    } else if (d4 < d5) {
                        enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
                        vec31 = new Vec3d(vec31.xCoord + d6 * d4, d1, vec31.zCoord + d8 * d4);
                    } else {
                        enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
                        vec31 = new Vec3d(vec31.xCoord + d6 * d5, vec31.yCoord + d7 * d5, d2);
                    }

                    l = MathHelper.floor_double(vec31.xCoord) - (enumfacing == EnumFacing.EAST ? 1 : 0);
                    i1 = MathHelper.floor_double(vec31.yCoord) - (enumfacing == EnumFacing.UP ? 1 : 0);
                    j1 = MathHelper.floor_double(vec31.zCoord) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
                    blockpos = new BlockPos(l, i1, j1);
                    IBlockState iblockstate1 = world.getBlockState(blockpos);
                    Block block1 = iblockstate1.getBlock();

                    if (!ignoreBlockWithoutBoundingBox || iblockstate1.getMaterial() == Material.PORTAL || iblockstate1.getCollisionBoundingBox(world, blockpos) != Block.NULL_AABB) {
                        if (block1.canCollideCheck(iblockstate1, stopOnLiquid)) {
                            RayTraceResult raytraceresult1 = this.collisionRayTrace(iblockstate1, world, blockpos, vec31, vec32);//iblockstate1.collisionRayTrace(world, blockpos, vec31, vec32);

                            if (raytraceresult1 != null) {
                                return raytraceresult1;
                            }
                        } else {
                            raytraceresult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec31, enumfacing, blockpos);
                        }
                    }
                }

                return returnLastUncollidableBlock ? raytraceresult2 : null;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //Blockから 744
    @Deprecated
    @Nullable
    public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {

        AxisAlignedBB FULL_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

        return this.rayTrace(pos, start, end, FULL_BLOCK_AABB);
    }

    @Nullable
    protected RayTraceResult rayTrace(BlockPos pos, Vec3d start, Vec3d end, AxisAlignedBB boundingBox) {
        Vec3d vec3d = start.subtract((double) pos.getX(), (double) pos.getY(), (double) pos.getZ());
        Vec3d vec3d1 = end.subtract((double) pos.getX(), (double) pos.getY(), (double) pos.getZ());
        RayTraceResult raytraceresult = boundingBox.calculateIntercept(vec3d, vec3d1);
        return raytraceresult == null ? null : new RayTraceResult(raytraceresult.hitVec.addVector((double) pos.getX(), (double) pos.getY(), (double) pos.getZ()), raytraceresult.sideHit, pos);
    }

}
