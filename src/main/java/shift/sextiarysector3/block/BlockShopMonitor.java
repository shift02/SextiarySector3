package shift.sextiarysector3.block;

import javax.annotation.Nullable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.mceconomy3.api.MCEconomyAPI;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.api.shop.IShopMemory;
import shift.sextiarysector3.item.ItemSpanner;
import shift.sextiarysector3.tileentity.TileEntityShopMonitor;
import shift.sextiarysector3.util.UtilCompat;

public class BlockShopMonitor extends BlockSSHorizontal implements ITileEntityProvider {

    public static final PropertyBool SWITCH = PropertyBool.create("switch");

    protected static final AxisAlignedBB SHOP_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.875D, 0.875D);

    public BlockShopMonitor() {
        super(Material.IRON);

        this.setHardness(3.0F);
        this.setResistance(40.0F);
        this.setHarvestLevel("spanner", 1);

        this.setSoundType(SoundType.METAL);
        //this.setBlockUnbreakable();
        this.setCreativeTab(SextiarySectorAPI.TabSSEconomy);

        this.setDefaultState(
                this.getDefaultState()
                        .withProperty(SWITCH, Boolean.valueOf(false)));

    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { super.FACING, SWITCH });
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY,
            float hitZ) {

        TileEntityShopMonitor tileEntity = (TileEntityShopMonitor) worldIn.getTileEntity(pos);

        ItemStack item = heldItem;

        if (!UtilCompat.isNullFromItemStack(item) && item.getItem() instanceof IShopMemory) {

            if (tileEntity.getMemory() != null) return false;

            tileEntity.setMemory(item);

            playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);

            //if (!world.isRemote) world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.6F);
            //if (!worldIn.isRemote) worldIn.playSoundEffect(x, y, z, "random.wood_click", 1.0F, worldIn.rand.nextFloat() * 0.1F + 0.9F);

            if (!worldIn.isRemote) worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.7F, 0.6F);
            //if (!world.isRemote) world.playAuxSFX(1001, x, y, z, 0);

            return true;

        }

        if (item != null && item.getItem() instanceof ItemSpanner) {

            if (tileEntity.getMemory() == null) return false;

            ItemStack memory = tileEntity.getMemory();
            tileEntity.setMemory(null);

            if (!worldIn.isRemote) {

                //worldIn.playSoundEffect(x, y, z, "random.break", 1.0F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
                worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.8F, 0.4F);

                EntityItem eItem = new EntityItem(worldIn, pos.getX() + 0.5d, pos.getY() + 0.6d, pos.getZ() + 0.5d, memory);

                worldIn.spawnEntityInWorld(eItem);
            }

            return true;

        }

        ItemStack tItem = tileEntity.getMemory();

        if (playerIn.isSneaking()) {

            if (tItem == null) {

                return true;

            }

            if (!worldIn.isRemote) worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6F);

            if (worldIn.isRemote) return true;

            tileEntity.changeON();
            //worldIn.markBlockRangeForRenderUpdate(x, y, z, x, y, z);

            //worldIn.notifyBlockUpdate(pos, state, state, 3);

            return true;

        } else if (tileEntity.getOn()) {

            if (tItem == null) {

                if (worldIn.isRemote) return true;

                tileEntity.changeON();
                //worldIn.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                //worldIn.notifyBlockUpdate(pos, state, state, 4);
                return true;

            }

            if (!(tItem.getItem() instanceof IShopMemory)) return true;

            IShopMemory memory = (IShopMemory) tItem.getItem();

            MCEconomyAPI.openShopGui(memory.getShopID(worldIn, playerIn), playerIn, worldIn, pos.getX(), pos.getY(), pos.getZ());

        }

        return true;

    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {

        if (state.getValue(SWITCH)) return 15;

        return 0;

    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

        TileEntityShopMonitor tileentity = (TileEntityShopMonitor) worldIn.getTileEntity(pos);

        if (tileentity.getMemory() != null) {

            this.dropItem(tileentity.getMemory(), worldIn, pos);
            //worldIn.func_147453_f(par2, par3, par4, par5);

        }

        super.breakBlock(worldIn, pos, state);
    }

    private void dropItem(ItemStack itemstack, World par1World, BlockPos pos) {

        if (itemstack != null) {
            float f = this.RANDOM.nextFloat() * 0.8F + 0.1F;
            float f1 = this.RANDOM.nextFloat() * 0.8F + 0.1F;
            float f2 = this.RANDOM.nextFloat() * 0.8F + 0.1F;

            while (itemstack.stackSize > 0) {
                int k1 = this.RANDOM.nextInt(21) + 10;

                if (k1 > itemstack.stackSize) {
                    k1 = itemstack.stackSize;
                }

                itemstack.stackSize -= k1;
                EntityItem entityitem = new EntityItem(par1World, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

                if (itemstack.hasTagCompound()) {
                    entityitem.getEntityItem().setTagCompound(itemstack.getTagCompound().copy());
                }

                float f3 = 0.05F;
                entityitem.motionX = (float) this.RANDOM.nextGaussian() * f3;
                entityitem.motionY = (float) this.RANDOM.nextGaussian() * f3 + 0.2F;
                entityitem.motionZ = (float) this.RANDOM.nextGaussian() * f3;
                par1World.spawnEntityInWorld(entityitem);
            }
        }

    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    //15 1111
    //7   111
    @Override
    public IBlockState getStateFromMeta(int meta) {

        int f = meta & 0b0111;

        EnumFacing enumfacing = EnumFacing.getFront(f);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        int on = meta & 0b1000;
        boolean sw = on == 0b1000;

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(SWITCH, Boolean.valueOf(sw));
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        int meta = state.getValue(FACING).getIndex();

        if (state.getValue(SWITCH)) {
            meta |= 0b1000;
        }

        return meta;

    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SHOP_AABB;
    }

    //当たり判定。サボテンやソウルサンドを参考にすると良い。ココの設定をすると、onEntityCollidedWithBlockが呼ばれるようになる
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {

        return SHOP_AABB;

    }

    //ブロックに視点を合わせた時に出てくる黒い線のアレ
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {

        return SHOP_AABB.offset(pos);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityShopMonitor();
    }

}
