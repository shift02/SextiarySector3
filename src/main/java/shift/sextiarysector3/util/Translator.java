package shift.sextiarysector3.util;

import net.minecraft.util.text.translation.I18n;

/**
 * 多言語処理のメソッドが非推奨なのでいつ消されてもいいようのWhopper
 * @author Shift02
 *
 */
public class Translator {

    public static String translateToLocal(String key) {

        return I18n.translateToLocal(key);

    }

}
