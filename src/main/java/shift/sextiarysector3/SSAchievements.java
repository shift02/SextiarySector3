package shift.sextiarysector3;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import shift.sextiarysector3.achievement.AchievementBase;
import shift.sextiarysector3.achievement.AchievementCraft;
import shift.sextiarysector3.achievement.AchievementFurnace;
import shift.sextiarysector3.achievement.AchievementPageBase;
import shift.sextiarysector3.achievement.AchievementSSOre;

public class SSAchievements {

    private static ArrayList<Achievement> core = Lists.newArrayList();
    private static ArrayList<Achievement> forestry = Lists.newArrayList();
    private static ArrayList<Achievement> mining = Lists.newArrayList();
    private static ArrayList<Achievement> industry = Lists.newArrayList();

    //Core
    public static Achievement blueStone;
    public static Achievement shield;
    public static Achievement sanctuary;
    public static Achievement depthMeter;

    //林業
    public static Achievement spile;
    public static Achievement sapBottle;
    public static Achievement rubberBottle;

    //鉱業
    public static Achievement newOre;
    public static Achievement silver;

    //工業
    public static Achievement plastic;

    public static void initAchievements() {

        //コア
        blueStone = new AchievementFurnace("bluestone", 0, 0, new ItemStack(SSItems.bluestone), (Achievement) null, core).initIndependentStat().registerStat();

        //林業
        spile = new AchievementCraft("spile", 0, 0, new ItemStack(SSBlocks.spile), (Achievement) null, forestry).initIndependentStat().registerStat();
        sapBottle = new AchievementBase("sap_bottle", 2, 1, new ItemStack(SSItems.sapBottle), (Achievement) spile, forestry).registerStat();
        rubberBottle = new AchievementBase("rubber_bottle", 2, -1, new ItemStack(SSItems.rubberBottle), (Achievement) spile, forestry).registerStat();

        //鉱業
        newOre = new AchievementSSOre("new_ore", 0, 0, new ItemStack(SSItems.orichalcumGem), (Achievement) null, mining).initIndependentStat().registerStat();
        silver = new AchievementFurnace("silver", 2, 1, new ItemStack(SSItems.silverIngot), (Achievement) newOre, mining).registerStat();

        //鉱業 -> コア
        sanctuary = new AchievementCraft("sanctuary", 2, 1, new ItemStack(SSBlocks.sanctuary), (Achievement) silver, core).registerStat();
        depthMeter = new AchievementFurnace("depth_meter", 2, -1, new ItemStack(SSItems.depthMeter), (Achievement) silver, core).registerStat();

        //工業
        plastic = new AchievementFurnace("plastic", 0, 0, new ItemStack(SSItems.plastic), (Achievement) sapBottle, industry).registerStat();

        //工業 -> コア
        shield = new AchievementCraft("shield", 4, -2, new ItemStack(SSItems.plasticShield), (Achievement) plastic, core).registerStat();

        //登録
        AchievementPage.registerAchievementPage(new AchievementPageBase("achievement.ss.core", core));
        AchievementPage.registerAchievementPage(new AchievementPageBase("achievement.ss.forestry", forestry));
        AchievementPage.registerAchievementPage(new AchievementPageBase("achievement.ss.mining", mining));
        AchievementPage.registerAchievementPage(new AchievementPageBase("achievement.ss.industry", industry));

    }

}
