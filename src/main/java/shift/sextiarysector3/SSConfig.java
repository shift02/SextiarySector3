package shift.sextiarysector3;

import java.io.File;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;

public class SSConfig {

    public static Configuration config;

    public static final String SS_LANG = "config.ss.";

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

        if (config.hasChanged()) {

            config.save();
        }

    }

}
