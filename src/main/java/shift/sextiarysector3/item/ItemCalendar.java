package shift.sextiarysector3.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.util.SeasonManager;

public class ItemCalendar extends ItemSSBase {

    public ItemCalendar() {

        this.addPropertyOverride(new ResourceLocation("data"), new IItemPropertyGetter() {

            @SideOnly(Side.CLIENT)
            double rotation;
            @SideOnly(Side.CLIENT)
            double rota;
            @SideOnly(Side.CLIENT)
            long lastUpdateTick;

            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {

                boolean flag = entityIn != null;
                Entity entity = flag ? entityIn : stack.getItemFrame();

                if (worldIn == null && entity != null) {
                    worldIn = entity.worldObj;
                }

                if (worldIn == null || entity == null) {
                    return 0.0F;
                } else {

                    double d0;

                    if (worldIn.provider.isSurfaceWorld()) {

                        //TODO 日付処理
                        d0 = SeasonManager.getInstance().getDay(worldIn) / 30.0f - 0.001;

                    } else {
                        d0 = Math.random();
                    }

                    //d0 = this.wobble(worldIn, d0);
                    return MathHelper.positiveModulo((float) d0, 1.0F);

                }
            }

            @SideOnly(Side.CLIENT)
            private double wobble(World p_185087_1_, double p_185087_2_) {

                if (p_185087_2_ == 0 || p_185087_2_ == 1) return p_185087_2_;

                if (p_185087_1_.getTotalWorldTime() != this.lastUpdateTick) {
                    this.lastUpdateTick = p_185087_1_.getTotalWorldTime();
                    double d0 = p_185087_2_ - this.rotation;

                    boolean f = false;
                    if (d0 < -0.5D) {
                        ++d0;
                        f = true;
                    }

                    this.rota += d0 * 0.1D;
                    this.rota *= 0.9D;
                    this.rotation += this.rota;

                    if (f) return p_185087_2_;

                }

                return this.rotation;

            }
        });
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {

        tooltip.add(SeasonManager.getInstance().getDay(playerIn.worldObj) + " Day");

    }
}
