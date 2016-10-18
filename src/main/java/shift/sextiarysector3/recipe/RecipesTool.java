package shift.sextiarysector3.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector3.SSItems;

public class RecipesTool {

    public static void addRecipes(CraftingManager craftingManager) {

        //バニラツール
        //銅
        addShovel(craftingManager, "ingotCopper", new ItemStack(SSItems.copperShovel));
        addPickaxe(craftingManager, "ingotCopper", new ItemStack(SSItems.copperPickaxe));
        addAxe(craftingManager, "ingotCopper", new ItemStack(SSItems.copperAxe));
        addSword(craftingManager, "ingotCopper", new ItemStack(SSItems.copperSword));
        addHoe(craftingManager, "ingotCopper", new ItemStack(SSItems.copperHoe));

        addShovel(craftingManager, "ingotSilver", new ItemStack(SSItems.silverShovel));
        addPickaxe(craftingManager, "ingotSilver", new ItemStack(SSItems.silverPickaxe));
        addAxe(craftingManager, "ingotSilver", new ItemStack(SSItems.silverAxe));
        addSword(craftingManager, "ingotSilver", new ItemStack(SSItems.silverSword));
        addHoe(craftingManager, "ingotSilver", new ItemStack(SSItems.silverHoe));

    }

    private static void addAxe(CraftingManager p_77608_1_, String material, ItemStack item) {

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(item,
                new Object[] { "yy", "yx", " x",
                        Character.valueOf('y'), material,
                        Character.valueOf('x'), "stickWood",
                }));

    }

    private static void addHoe(CraftingManager p_77608_1_, String material, ItemStack item) {

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(item,
                new Object[] { "yy", " x", " x",
                        Character.valueOf('y'), material,
                        Character.valueOf('x'), "stickWood",
                }));

    }

    private static void addPickaxe(CraftingManager p_77608_1_, String material, ItemStack item) {

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(item,
                new Object[] { "yyy", " x ", " x ",
                        Character.valueOf('y'), material,
                        Character.valueOf('x'), "stickWood",
                }));

    }

    private static void addShovel(CraftingManager p_77608_1_, String material, ItemStack item) {

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(item,
                new Object[] { "y", "x", "x",
                        Character.valueOf('y'), material,
                        Character.valueOf('x'), "stickWood",
                }));

    }

    private static void addSword(CraftingManager p_77608_1_, String material, ItemStack item) {

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(item,
                new Object[] { "y", "y", "x",
                        Character.valueOf('y'), material,
                        Character.valueOf('x'), "stickWood",
                }));

    }

}
