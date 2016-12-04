package shift.sextiarysector3;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import shift.sextiarysector3.achievement.AchievementFurnace;
import shift.sextiarysector3.achievement.AchievementPageBase;

public class SSAchievements {

    private static ArrayList<Achievement> core = Lists.newArrayList();

    //Core
    public static Achievement blueStone;

    public static void initAchievements() {

        blueStone = new AchievementFurnace("bluestone", 0, 0, new ItemStack(SSItems.bluestone), (Achievement) null, core).initIndependentStat().registerStat();

        AchievementPage.registerAchievementPage(new AchievementPageBase("achievement.ss.core", core));

    }

}
