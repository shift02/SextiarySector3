package shift.sextiarysector3.recipe;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;
import shift.sextiarysector3.recipe.template.ShapedPotionCakeOreRecipe;

public class RecipesCooking {

    public static void addRecipes(CraftingManager p_77608_1_) {

        //メイプルケーキ
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.mapleCake, 1),
                new Object[] {
                        "xxx", "yzy", "aaa",
                        'x', SSItems.mapleBottle,
                        'y', Items.SUGAR,
                        'z', "egg",
                        'a', "cropWheat"
                }));

        //ポーションケーキ

        ItemStack[] POTION = new ItemStack[] {
                PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_STRENGTH),
                PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_SWIFTNESS),
                PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.FIRE_RESISTANCE),
                PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_LEAPING)
        };
        ItemStack[] CAKE = new ItemStack[] {
                new ItemStack(SSBlocks.strengthCake, 1),
                new ItemStack(SSBlocks.swiftnessCake, 1),
                new ItemStack(SSBlocks.fireresistanceCake, 1),
                new ItemStack(SSBlocks.leapingCake, 1)
        };
        for (int i = 0; i < POTION.length; i++) {

            p_77608_1_.getRecipeList().add(new ShapedPotionCakeOreRecipe(CAKE[i],
                    new Object[] {
                            "xxx", "yzy", "aaa",
                            'x', POTION[i],
                            'y', SSItems.mapleBottle,
                            'z', "egg",
                            'a', "cropWheat"
                    }));

        }

    }

}
