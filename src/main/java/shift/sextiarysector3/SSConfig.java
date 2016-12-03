package shift.sextiarysector3;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Lists;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;

public class SSConfig {

    public static Configuration config;

    public static final String SS_LANG = "config.ss.";

    //General
    public static boolean fastDecayLeaves;

    //World
    public static boolean generateCopperOre;
    public static boolean generateSilverOre;
    public static boolean generateOrichalcumOre;

    public static boolean generateCoalLargeOre;
    public static boolean generateIronLargeOre;
    public static boolean generateGoldLargeOre;

    public static boolean generateCopperLargeOre;
    public static boolean generateSilverLargeOre;

    public static final String CATEGORY_WORLD = "world";

    public static void initConfig() {

        if (config != null) return;

        File file = new File(Loader.instance().getConfigDir(), "SextiarySector3.cfg");
        config = new Configuration(file);

        try {

            config.load();

        } catch (Exception e) {

            File dest = new File(file.getParentFile(), file.getName() + ".bak");

            if (dest.exists()) {

                dest.delete();
            }

            file.renameTo(dest);

            FMLLog.log(Level.ERROR, e, "A critical error occured reading the " + file.getName() + " file, defaults will be used - the invalid file is backed up at " + dest.getName());

        }

    }

    public static void syncConfig() {

        initConfig();

        //一般
        String category = Configuration.CATEGORY_GENERAL;

        Property prop;
        List<String> propOrder = Lists.newArrayList();

        //Fast Decay Leaves - 葉の高速消滅
        prop = config.get(category, "fast_decay_leaves", true);
        prop.setLanguageKey(SS_LANG + category + "." + prop.getName());
        prop.setComment(I18n.translateToLocal(prop.getLanguageKey() + ".tooltip"));
        prop.setComment(prop.getComment() + " [default: " + prop.getDefault() + "]");
        propOrder.add(prop.getName());
        fastDecayLeaves = prop.getBoolean(fastDecayLeaves);

        config.setCategoryLanguageKey(category, SS_LANG + category);
        config.setCategoryPropertyOrder(category, propOrder);

        //ワールド
        category = CATEGORY_WORLD;
        propOrder = Lists.newArrayList();

        //Generate Copper Ore - 銅鉱石の生成
        prop = config.get(category, "generate_copper_ore", true);
        prop.setLanguageKey(SS_LANG + category + "." + prop.getName());
        prop.setComment(I18n.translateToLocal(prop.getLanguageKey() + ".tooltip"));
        prop.setComment(prop.getComment() + " [default: " + prop.getDefault() + "]");
        propOrder.add(prop.getName());
        generateCopperOre = prop.getBoolean(generateCopperOre);

        //Generate Silver Ore - 銀鉱石の生成
        prop = config.get(category, "generate_silver_ore", true);
        prop.setLanguageKey(SS_LANG + category + "." + prop.getName());
        prop.setComment(I18n.translateToLocal(prop.getLanguageKey() + ".tooltip"));
        prop.setComment(prop.getComment() + " [default: " + prop.getDefault() + "]");
        propOrder.add(prop.getName());
        generateSilverOre = prop.getBoolean(generateSilverOre);

        //Generate Orichalcum Ore - オリハルコンの生成
        prop = config.get(category, "generate_orichalcum_ore", true);
        prop.setLanguageKey(SS_LANG + category + "." + prop.getName());
        prop.setComment(I18n.translateToLocal(prop.getLanguageKey() + ".tooltip"));
        prop.setComment(prop.getComment() + " [default: " + prop.getDefault() + "]");
        propOrder.add(prop.getName());
        generateOrichalcumOre = prop.getBoolean(generateOrichalcumOre);

        //Generate CoalLarge Ore - 大きな石炭の生成
        prop = config.get(category, "generate_coal_large_ore", true);
        prop.setLanguageKey(SS_LANG + category + "." + prop.getName());
        prop.setComment(I18n.translateToLocal(prop.getLanguageKey() + ".tooltip"));
        prop.setComment(prop.getComment() + " [default: " + prop.getDefault() + "]");
        propOrder.add(prop.getName());
        generateCoalLargeOre = prop.getBoolean(generateCoalLargeOre);

        //Generate IronLarge Ore - 大きな鉄鉱石の生成
        prop = config.get(category, "generate_iron_large_ore", true);
        prop.setLanguageKey(SS_LANG + category + "." + prop.getName());
        prop.setComment(I18n.translateToLocal(prop.getLanguageKey() + ".tooltip"));
        prop.setComment(prop.getComment() + " [default: " + prop.getDefault() + "]");
        propOrder.add(prop.getName());
        generateIronLargeOre = prop.getBoolean(generateIronLargeOre);

        //Generate GoldLarge Ore - 大きな金鉱石の生成
        prop = config.get(category, "generate_gold_large_ore", true);
        prop.setLanguageKey(SS_LANG + category + "." + prop.getName());
        prop.setComment(I18n.translateToLocal(prop.getLanguageKey() + ".tooltip"));
        prop.setComment(prop.getComment() + " [default: " + prop.getDefault() + "]");
        propOrder.add(prop.getName());
        generateGoldLargeOre = prop.getBoolean(generateGoldLargeOre);

        //Generate CopperLarge Ore - 大きな銅鉱石の生成
        prop = config.get(category, "generate_copper_large_ore", true);
        prop.setLanguageKey(SS_LANG + category + "." + prop.getName());
        prop.setComment(I18n.translateToLocal(prop.getLanguageKey() + ".tooltip"));
        prop.setComment(prop.getComment() + " [default: " + prop.getDefault() + "]");
        propOrder.add(prop.getName());
        generateCopperLargeOre = prop.getBoolean(generateCopperLargeOre);

        //Generate SilverLarge Ore - 大きな銀鉱石の生成
        prop = config.get(category, "generate_silver_large_ore", true);
        prop.setLanguageKey(SS_LANG + category + "." + prop.getName());
        prop.setComment(I18n.translateToLocal(prop.getLanguageKey() + ".tooltip"));
        prop.setComment(prop.getComment() + " [default: " + prop.getDefault() + "]");
        propOrder.add(prop.getName());
        generateSilverLargeOre = prop.getBoolean(generateSilverLargeOre);

        config.setCategoryLanguageKey(category, SS_LANG + category);
        config.setCategoryPropertyOrder(category, propOrder);

        //更新
        if (config.hasChanged()) {

            config.save();
        }

    }

}
