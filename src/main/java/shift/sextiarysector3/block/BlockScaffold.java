package shift.sextiarysector3.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

public class BlockScaffold extends BlockSSBase {

    protected static final AxisAlignedBB SCAFFOLD_AABB = new AxisAlignedBB(0.001D, 0.0D, 0.001D, 0.999D, 1D, 0.999D);

    public BlockScaffold(Material materialIn) {
        super(materialIn);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {

        ForgeModContainer.fullBoundingBoxLadders = true;
        return SCAFFOLD_AABB;

    }

    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {

        return true;
    }

}
