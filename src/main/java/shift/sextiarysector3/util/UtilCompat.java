package shift.sextiarysector3.util;

import net.minecraft.item.ItemStack;

public class UtilCompat {

    /**
     * ItemStackがnullか確認するメソッド
     * @param item
     * @return nullならtrue
     */
    public static boolean isNullFromItemStack(ItemStack item) {
        return item == null;
    }

}
