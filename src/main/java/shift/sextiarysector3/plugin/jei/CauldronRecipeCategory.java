package shift.sextiarysector3.plugin.jei;

import java.util.Collections;
import java.util.List;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector3.SextiarySector3;

public class CauldronRecipeCategory extends RecipeCategoryBase<CauldronRecipeWrapper> {

    public static final String CAULDRON = "minecraft.cauldron";

    private final IDrawable background;
    private final String localizedName;

    protected static final int inputSlot = 1;
    protected static final int fluidSlot = 2;
    protected static final int cauldronSlot = 3;
    protected static final int outputSlot = 0;

    private static List<ItemStack> cauldron;

    private final ICraftingGridHelper craftingGridHelper;

    public CauldronRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper);
        ResourceLocation location = new ResourceLocation(SextiarySector3.MODID, "textures/guis/jei/jei_cauldron.png");
        background = guiHelper.createDrawable(location, 35, 16, 110, 54);
        localizedName = Translator.translateToLocal("gui.jei.category.cauldron");

        this.cauldron = Collections.singletonList(new ItemStack(Items.CAULDRON));

        craftingGridHelper = guiHelper.createCraftingGridHelper(inputSlot, outputSlot);

    }

    @Override
    public String getUid() {

        return this.CAULDRON;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void drawAnimations(Minecraft minecraft) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CauldronRecipeWrapper recipeWrapper) {

        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiItemStacks.init(inputSlot, true, 0, 18);
        guiFluidStacks.init(fluidSlot, false, 44 + 1, 18 + 1, 16, 16, 1000, true, null);
        guiItemStacks.init(cauldronSlot, false, 44, 36);
        guiItemStacks.init(outputSlot, false, 88, 18);

        guiItemStacks.setFromRecipe(inputSlot, recipeWrapper.getInputs());
        guiFluidStacks.set(fluidSlot, recipeWrapper.getFluidInputs());
        guiItemStacks.setFromRecipe(cauldronSlot, this.cauldron);
        guiItemStacks.set(outputSlot, recipeWrapper.getOutputs());

    }

}