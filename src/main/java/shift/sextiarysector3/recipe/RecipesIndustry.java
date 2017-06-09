package shift.sextiarysector3.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;

public class RecipesIndustry {

    public static void addRecipes(CraftingManager p_77608_1_) {

        //plasticブロック
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.plasticBlock, 1),
                new Object[] {
                        "xxx", "xxx", "xxx",
                        'x', "plastic"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.rubberBlock, 1),
                new Object[] {
                        "xxx", "xxx", "xxx",
                        'x', "rubber"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.rubberGloves, 1),
                new Object[] {
                        " x", "x ",
                        'x', "rubber"
                }));

        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSItems.blueGel, 1),
                new Object[] {
                        "dustBluestone",
                        "slimeball"
                }));

        //歯車
        String[] MATERIAL = new String[] {
                "plankWood"
        };
        ItemStack[] GEAR = new ItemStack[] {
                new ItemStack(SSItems.woodGear, 2)
        };
        for (int i = 0; i < MATERIAL.length; i++) {

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(GEAR[i],
                    new Object[] {
                            " x ", "xyx", " x ",
                            'x', MATERIAL[i],
                            'y', "dustBluestone"
                    }));

        }

        //足場ブロック
        ItemStack[] PLANKS = new ItemStack[] {
                new ItemStack(Blocks.PLANKS, 1, 0),
                new ItemStack(Blocks.PLANKS, 1, 2),
                new ItemStack(Blocks.PLANKS, 1, 1),
                new ItemStack(Blocks.PLANKS, 1, 3),
                new ItemStack(Blocks.PLANKS, 1, 5),
                new ItemStack(Blocks.PLANKS, 1, 4)
        };
        ItemStack[] FENCE = new ItemStack[] {
                new ItemStack(Blocks.OAK_FENCE),
                new ItemStack(Blocks.BIRCH_FENCE),
                new ItemStack(Blocks.SPRUCE_FENCE),
                new ItemStack(Blocks.JUNGLE_FENCE),
                new ItemStack(Blocks.DARK_OAK_FENCE),
                new ItemStack(Blocks.ACACIA_FENCE)
        };
        for (int i = 0; i < PLANKS.length; i++) {

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.woodScaffold, 16, i),
                    new Object[] {
                            "xxx", "yzy", "yzy",
                            'x', PLANKS[i],
                            'y', FENCE[i],
                            'z', "stickWood"
                    }));

        }

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.woodShaft, 4),
                new Object[] {
                        "xzx", "xyx", "xzx",
                        'x', "plankWood",
                        'y', "dustBluestone",
                        'z', SSItems.blueGel
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.woodGearBox),
                new Object[] {
                        "xzx", "zyz", "xzx",
                        'x', "plankWood",
                        'y', "dustBluestone",
                        'z', "gearWood"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.conveyor, 2),
                new Object[] {
                        "xxx", "yzy",
                        'x', "rubber",
                        'y', "ingotIron",
                        'z', "dustBluestone"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.smallWindmill, 1),
                new Object[] {
                        "xyx", "yzy", "xyx",
                        'x', "blockWool",
                        'y', "plankWood",
                        'z', SSBlocks.woodShaft
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.ironSpanner, 1),
                new Object[] {
                        " x ", " xx", "x  ",
                        'x', "ingotIron"
                }));

    }

}
