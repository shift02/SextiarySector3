package shift.sextiarysector3.util;

import net.minecraft.item.ItemStack;

/**
 * バージョン互換のためのクラス
 * @author Shift02
 *
 */
public class UtilCompat {

    /**
     * ItemStackがnullか確認するメソッド
     * @param item
     * @return nullならtrue
     */
    public static boolean isNullFromItemStack(ItemStack item) {
        return item == null;
    }

    /**
     * NullなItemStackを取得
     * @return null Stack
     */
    public static ItemStack getNullItemStack() {
        return null;
    }

}
