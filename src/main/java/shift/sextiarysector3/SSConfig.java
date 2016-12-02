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

        if (config.hasChanged()) {

            config.save();
        }

    }

}
