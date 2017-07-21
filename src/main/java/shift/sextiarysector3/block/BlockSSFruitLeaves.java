package shift.sextiarysector3.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import shift.sextiarysector3.api.season.Season;
import shift.sextiarysector3.util.SeasonManager;

public class BlockSSFruitLeaves extends BlockSSLeaves implements IGrowable {

    public Season season;

    public ItemStack fruit;

    public BlockSSFruitLeaves(Block sapling, ItemStack fruit, Season season) {
        super(sapling);
        this.fruit = fruit;
        this.season = season;

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY,
            float hitZ) {

        if (this.getAge(state) < this.getMaxAge()) return false;
        if (!this.canGrow(worldIn, pos, state, worldIn.isRemote)) return false;

        if (!worldIn.isRemote) {

            //worldIn.playSoundEffect(x, y, z, "random.break", 1.0F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            //worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.8F, 0.4F);

            EntityItem eItem = new EntityItem(worldIn, pos.getX() + 0.5d, pos.getY() + 0.6d, pos.getZ() + 0.5d, fruit.copy());

            worldIn.spawnEntityInWorld(eItem);

        }

        worldIn.setBlockState(pos, state.withProperty(AGE, 0), 2);

        return true;

    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);

        if (SeasonManager.getInstance().getSeason(worldIn) != this.season) {

            if (this.getAge(state) > 0) {
                worldIn.setBlockState(pos, state.withProperty(AGE, 0), 2);

            }

            return;

        }

        if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {

            int i = this.getAge(state);

            if (i < this.getMaxAge() && this.canGrow(worldIn, pos, state, worldIn.isRemote)) {
                float f = 25.0f;//getGrowthChance(this, worldIn, pos);

                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0)) {
                    worldIn.setBlockState(pos, state.withProperty(AGE, i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }

        }

    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();

        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, state.withProperty(AGE, i), 2);
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {

        if (!state.getValue(DECAYABLE)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {

        if (!state.getValue(DECAYABLE)) {
            return false;
        }

        return true;
    }

    public int getMaxAge() {
        return 3;
    }

}
