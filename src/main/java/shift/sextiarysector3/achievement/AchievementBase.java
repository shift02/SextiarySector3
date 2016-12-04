package shift.sextiarysector3.achievement;

import java.util.ArrayList;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.SextiarySector3;

public class AchievementBase extends Achievement {

    private final String achievementDescription2;

    public AchievementBase(String p_i45302_1_, int p_i45302_3_, int p_i45302_4_, ItemStack p_i45302_5_, Achievement p_i45302_6_, ArrayList<Achievement> a) {
        super("achievement.ss" + p_i45302_1_, "ss." + p_i45302_1_, p_i45302_3_, p_i45302_4_, p_i45302_5_, p_i45302_6_);
        this.achievementDescription2 = "achievement.ss." + p_i45302_1_ + ".desc.unlocked";
        a.add(this);
    }

    @SideOnly(Side.CLIENT)
    public String getDescription() {

        if (((EntityPlayerSP) SextiarySector3.proxy.getClientPlayer()).getStatFileWriter().hasAchievementUnlocked(this)) {
            return I18n.translateToLocal(this.achievementDescription2);
        } else {
            return super.getDescription();
        }

    }

}