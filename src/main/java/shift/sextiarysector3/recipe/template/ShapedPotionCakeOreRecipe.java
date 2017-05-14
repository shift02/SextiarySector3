package shift.sextiarysector3.recipe.template;

import java.util.Iterator;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ShapedPotionCakeOreRecipe extends ShapedOreRecipe {

    public ShapedPotionCakeOreRecipe(ItemStack result, Object[] recipe) {
        super(result, recipe);

    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror) {
        for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++) {
            for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++) {
                int subX = x - startX;
                int subY = y - startY;
                Object target = null;

                if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
                    if (mirror) {
                        target = input[width - subX - 1 + subY * width];
                    } else {
                        target = input[subX + subY * width];
                    }
                }

                ItemStack slot = inv.getStackInRowAndColumn(x, y);

                if (target instanceof ItemStack) {
                    if (!this.itemMatches((ItemStack) target, slot, false)) {
                        return false;
                    }
                } else if (target instanceof List) {
                    boolean matched = false;

                    Iterator<ItemStack> itr = ((List<ItemStack>) target).iterator();
                    while (itr.hasNext() && !matched) {
                        matched = this.itemMatches(itr.next(), slot, false);
                    }

                    if (!matched) {
                        return false;
                    }
                } else if (target == null && slot != null) {
                    return false;
                }
            }
        }

        return true;
    }

    protected boolean itemMatches(ItemStack target, ItemStack input, boolean strict) {

        if (!OreDictionary.itemMatches(target, input, strict)) return false;

        if (target == null || input == null) return true;

        if (PotionUtils.getPotionFromItem(target) == PotionUtils.getPotionFromItem(input)) return true;

        return false;

    }

}
