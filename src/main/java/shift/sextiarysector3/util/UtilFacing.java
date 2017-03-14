package shift.sextiarysector3.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

public class UtilFacing {

    public static AxisAlignedBB rotationAxisAlignedBB(AxisAlignedBB aabb, EnumFacing.Axis axis) {

        if (axis == EnumFacing.Axis.X) {

            return new AxisAlignedBB(aabb.minY, aabb.minX, aabb.minZ, aabb.maxY, aabb.maxX, aabb.maxZ);

        }

        if (axis == EnumFacing.Axis.Z) {
            return new AxisAlignedBB(aabb.minX, aabb.minZ, aabb.minY, aabb.maxX, aabb.maxZ, aabb.maxY);
        }

        return aabb;

    }

}
