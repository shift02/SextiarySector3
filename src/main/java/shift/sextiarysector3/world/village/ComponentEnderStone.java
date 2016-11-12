package shift.sextiarysector3.world.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.block.BlockEnderStoneMonument;

public class ComponentEnderStone extends StructureVillagePieces.Village {

    public ComponentEnderStone() {

    }

    public ComponentEnderStone(StructureVillagePieces.Start p_i2107_1_, int p_i2107_2_, Random p_i2106_3_, StructureBoundingBox p_i2106_4_, EnumFacing facing) {
        super(p_i2107_1_, p_i2107_2_);
        this.setCoordBaseMode(facing);
        this.boundingBox = p_i2106_4_;
    }

    public static Object buildComponent(StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, EnumFacing facing,
            int p5) {

        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 10, 10, 10, facing);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null
                ? new ComponentEnderStone(startPiece, p5, random, structureboundingbox, facing) : null;

    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if (this.averageGroundLvl < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 9 - 1, 0);
        }

        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 9, 10, 9, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

        IBlockState iblockstate0 = this
                .getBiomeSpecificBlockState(
                        Blocks.DOUBLE_STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.STONE)
                                .withProperty(BlockStoneSlab.SEAMLESS, Boolean.valueOf(false)));

        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 9, 0, 9, iblockstate0, iblockstate0, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 2, 8, 0, 8, Blocks.COBBLESTONE.getDefaultState(), Blocks.STONE.getDefaultState(), false);

        IBlockState iblockstate1 = this
                .getBiomeSpecificBlockState(SSBlocks.enderStone.getDefaultState().withProperty(BlockEnderStoneMonument.FACING, EnumFacing.SOUTH));

        this.setBlockState(worldIn, SSBlocks.enderStoneFoundation.getDefaultState(), 3, 1, 5, structureBoundingBoxIn);

        this.setBlockState(worldIn, SSBlocks.enderStoneFoundation.getDefaultState(), 5, 1, 5, structureBoundingBoxIn);

        this.setBlockState(worldIn, SSBlocks.enderStoneFoundation.getDefaultState(), 7, 1, 5, structureBoundingBoxIn);

        this.setBlockState(worldIn, iblockstate1, 3, 2, 5, structureBoundingBoxIn);
        BlockPos blockpos = new BlockPos(this.getXWithOffset(3, 5), this.getYWithOffset(2), this.getZWithOffset(3, 5));
        worldIn.scheduleBlockUpdate(blockpos, iblockstate1.getBlock(), SSBlocks.enderStone.tickRate(worldIn), 0);

        this.setBlockState(worldIn, iblockstate1, 5, 2, 5, structureBoundingBoxIn);
        BlockPos blockpos1 = new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(2), this.getZWithOffset(5, 5));
        worldIn.scheduleBlockUpdate(blockpos1, iblockstate1.getBlock(), SSBlocks.enderStone.tickRate(worldIn), 0);

        this.setBlockState(worldIn, iblockstate1, 7, 2, 5, structureBoundingBoxIn);
        BlockPos blockpos2 = new BlockPos(this.getXWithOffset(7, 5), this.getYWithOffset(2), this.getZWithOffset(7, 5));
        worldIn.scheduleBlockUpdate(blockpos2, iblockstate1.getBlock(), SSBlocks.enderStone.tickRate(worldIn), 0);

        return true;
    }

}
