package shift.sextiarysector3.world;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.block.BlockEnderStoneMonument;

public class WorldGenEnderStone extends WorldGenerator {

    public boolean generate(World worldIn, Random rand, BlockPos position) {

        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (SSBlocks.enderStoneFoundation.canPlaceBlockAt(worldIn, blockpos) && this.isFlat(worldIn, blockpos)) {
                worldIn.setBlockState(blockpos, SSBlocks.enderStoneFoundation.getDefaultState(), 2);
                IBlockState s = SSBlocks.enderStone.getDefaultState();

                worldIn.setBlockState(blockpos.up(), s, 3);
                worldIn.setBlockState(blockpos.up(),
                        worldIn.getBlockState(blockpos.up()).withProperty(BlockEnderStoneMonument.FACING, EnumFacing.Plane.HORIZONTAL.random(rand)), 2);

                worldIn.scheduleBlockUpdate(blockpos.up(), s.getBlock(), SSBlocks.enderStone.tickRate(worldIn), 0);

                break;

            }
        }

        return true;
    }

    public boolean isFlat(World worldIn, BlockPos blockpos) {

        if (worldIn.getBlockState(blockpos.down()).getBlock() != Blocks.GRASS) return false;

        if (worldIn.getBlockState(blockpos.down().south()).getBlock() != Blocks.GRASS) return false;
        if (worldIn.getBlockState(blockpos.down().north()).getBlock() != Blocks.GRASS) return false;
        if (worldIn.getBlockState(blockpos.down().west()).getBlock() != Blocks.GRASS) return false;
        if (worldIn.getBlockState(blockpos.down().east()).getBlock() != Blocks.GRASS) return false;

        return true;

    }

}
