package shift.sextiarysector3.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.util.IMetaItem;

public class BlockWoodScaffold extends BlockScaffold implements IMetaItem {

    public static final PropertyEnum<WoodType> WOOD = PropertyEnum.create("wood", WoodType.class);

    public BlockWoodScaffold() {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(WOOD, WoodType.OAK));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {

        for (WoodType type : WoodType.values()) {
            list.add(new ItemStack(itemIn, 1, type.ordinal()));
        }

    }

    @Override
    public IBlockState getStateFromMeta(int meta) {

        IBlockState iblockstate = this.getDefaultState().withProperty(WOOD, WoodType.values()[meta]);

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        int i = 0;
        i = state.getValue(WOOD).ordinal();

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { WOOD });
    }

    public static enum WoodType implements IStringSerializable {
        OAK, BIRCH, SPRUCE, JUNGLE, BIG_OAK, ACACIA;

        @Override
        public String getName() {
            return this.name().toLowerCase();
        }
    }

    @Override
    public int getMetaSize() {
        return WoodType.values().length;
    }

    /*@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }*/

}
