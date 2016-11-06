package shift.sextiarysector3.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector3.SSItems;

public class BlockEnderStoneMonument extends BlockSSBase {

    protected static final AxisAlignedBB FRAME_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.00D, 1.0D);

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockEnderStoneMonument() {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        //this.setTickRandomly(true);
    }

    //動作系
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        worldIn.scheduleBlockUpdate(pos, state.getBlock(), this.tickRate(worldIn), 0);

    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

        worldIn.scheduleBlockUpdate(pos, state.getBlock(), this.tickRate(worldIn), 0);
        super.updateTick(worldIn, pos, state, rand);

        //if (!worldIn.isRemote) {

        for (EnumFacing f : EnumFacing.HORIZONTALS) {
            setEnder(worldIn, pos.offset(f), state, rand, f);
        }

        //}
    }

    public void setEnder(World worldIn, BlockPos pos, IBlockState state, Random rand, EnumFacing f) {

        AxisAlignedBB axisalignedbb = FRAME_AABB.offset(pos);

        List<? extends EntityHanging> list = worldIn.getEntitiesWithinAABB(EntityHanging.class, axisalignedbb);

        for (EntityHanging eH : list) {

            if (!eH.getHorizontalFacing().equals(f)) continue;

            if (!(eH instanceof EntityItemFrame)) continue;

            EntityItemFrame eF = (EntityItemFrame) eH;
            if (eF.getDisplayedItem() == null) continue;
            if (!worldIn.isRemote) changeSilver(eF, eF.getDisplayedItem(), worldIn, pos, state, rand, f);
            if (worldIn.isRemote) changeSilverC(eF, eF.getDisplayedItem(), worldIn, pos, state, rand);
        }

    }

    public void changeSilver(EntityItemFrame eF, ItemStack item, World worldIn, BlockPos pos, IBlockState state, Random rand, EnumFacing f) {

        for (ItemStack silver : OreDictionary.getOres("ingotSilver")) {
            if (!OreDictionary.itemMatches(silver, item, false)) continue;

            System.out.println("Hit");

            ItemStack card = new ItemStack(SSItems.enderCard);

            if (!card.hasTagCompound()) {
                card.setTagCompound(new NBTTagCompound());
            }

            if (!card.getTagCompound().hasKey("effect")) {
                card.getTagCompound().setInteger("effect", 10);

            }

            card.getTagCompound().setBoolean("power", true);

            card.getTagCompound().setInteger("x", pos.getX());
            card.getTagCompound().setInteger("y", pos.getY());
            card.getTagCompound().setInteger("z", pos.getZ());
            card.getTagCompound().setInteger("facing", f.getIndex());

            eF.setDisplayedItem(card);

            return;

        }

    }

    public void changeSilverC(EntityItemFrame eF, ItemStack item, World worldIn, BlockPos pos, IBlockState state, Random rand) {

        if (!item.hasTagCompound()) return;
        if (!item.getTagCompound().hasKey("effect")) return;
        int time = item.getTagCompound().getInteger("effect");
        if (time == 0) {
            item.getTagCompound().removeTag("effect");
            return;
        }

        item.getTagCompound().setInteger("effect", time - 1);

        for (int i = 0; i < 32; ++i) {
            worldIn.spawnParticle(EnumParticleTypes.PORTAL, pos.getX() + 0.5D, pos.getY() + rand.nextDouble() * 2.0D, pos.getZ() + 0.5D, rand.nextGaussian(),
                    0.0D, rand.nextGaussian(), new int[0]);
        }

    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

        for (EnumFacing f : EnumFacing.HORIZONTALS) {
            setEnder(worldIn, pos.offset(f), worldIn.getBlockState(pos), rand, f);
        }

    }

    //石の向き
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
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

    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { FACING });
    }

}
