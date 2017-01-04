package shift.sextiarysector3.item;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRubberGroves extends ItemSSBase {

    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        System.out.println(hitX + " : " + hitY + " : " + hitZ);

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
                    worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty((PropertyDirection) prop, nextFacing));
                    return EnumActionResult.SUCCESS;
                } catch (IllegalArgumentException e) {

                }

                //return true;
            }
        }
        //worldIn.getBlockState(pos)

        System.out.println(nextFacing);

        return EnumActionResult.SUCCESS;

    }

}
