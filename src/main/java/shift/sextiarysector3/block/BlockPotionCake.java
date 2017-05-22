package shift.sextiarysector3.block;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class BlockPotionCake extends BlockSSCake {

    private Potion potion;
    private int color;

    public BlockPotionCake(Potion potion, int color) {
        super();
        this.potion = potion;
        this.color = color;

        this.setCreativeTab(SextiarySectorAPI.TabSSPharmacy);

    }

    @Override
    protected void eatedCake(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {

        player.addPotionEffect(new PotionEffect(potion));

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

        int i = this.color;

        double d0 = (i >> 16 & 255) / 255.0D;
        double d1 = (i >> 8 & 255) / 255.0D;
        double d2 = (i >> 0 & 255) / 255.0D;

        worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, pos.getX() + 0.5, pos.getY() + 0.4, pos.getZ() + 0.5, d0, d1, d2, new int[0]);

    }

}
