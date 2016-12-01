package shift.sextiarysector3.config;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import shift.sextiarysector3.SSConfig;
import shift.sextiarysector3.SextiarySector3;

public class SSConfigGui extends GuiConfig {

    public SSConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(), SextiarySector3.MODID, false, false,
                I18n.format(SSConfig.SS_LANG + "title"));
    }

    private static List<IConfigElement> getConfigElements() {

        List<IConfigElement> list = Lists.newArrayList();

        for (String name : SSConfig.config.getCategoryNames()) {

            ConfigCategory category = SSConfig.config.getCategory(name);

            if (category.showInGui()) {

                list.add(new ConfigElement(category));
            }
        }

        return list;
    }

}