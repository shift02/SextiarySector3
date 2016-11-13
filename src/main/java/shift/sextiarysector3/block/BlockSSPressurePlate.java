package shift.sextiarysector3.block;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSSPressurePlate extends BlockSSBasePressurePlate {

    public static final PropertyBool POWERED = PropertyBool.create("powered");
    private final BlockSSPressurePlate.Sensitivity sensitivity;

    public BlockSSPressurePlate(Material materialIn, BlockSSPressurePlate.Sensitivity sensitivityIn) {
        super(materialIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.valueOf(false)));
        this.sensitivity = sensitivityIn;
        this.setSoundType(SoundType.WOOD);
        this.setHardness(0.5f);
    }

    protected int getRedstoneStrength(IBlockState state) {
        return ((Boolean) state.getValue(POWERED)).booleanValue() ? 15 : 0;
    }

    protected IBlockState setRedstoneStrength(IBlockState state, int strength) {
        return state.withProperty(POWERED, Boolean.valueOf(strength > 0));
    }

    protected void playClickOnSound(World worldIn, BlockPos color) {
        if (this.blockMaterial == Material.WOOD) {
            worldIn.playSound((EntityPlayer) null, color, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
        } else {
            worldIn.playSound((EntityPlayer) null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
        }
    }

    protected void playClickOffSound(World worldIn, BlockPos pos) {
        if (this.blockMaterial == Material.WOOD) {
            worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.7F);
        } else {
            worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
        }
    }

    protected int computeRedstoneStrength(World worldIn, BlockPos pos) {
        AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
        List<? extends Entity> list;

        switch (this.sensitivity) {
        case AGEABLE:
            list = worldIn.<Entity> getEntitiesWithinAABB(EntityAgeable.class, axisalignedbb);
            break;
        case ENEMY:
            list = worldIn.<Entity> getEntitiesWithinAABB(EntityMob.class, axisalignedbb);
            break;
        case XPORB:
            list = worldIn.<Entity> getEntitiesWithinAABB(EntityXPOrb.class, axisalignedbb);
            break;
        case PLAYER:
            list = worldIn.<Entity> getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
            break;
        default:
            return 0;
        }

        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (!entity.doesEntityNotTriggerPressurePlate()) {
                    return 15;
                }
            }
        }

        return 0;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(POWERED, Boolean.valueOf(meta == 1));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((Boolean) state.getValue(POWERED)).booleanValue() ? 1 : 0;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { POWERED });
    }

    public static enum Sensitivity {
        AGEABLE, ENEMY, XPORB, PLAYER;
    }

}