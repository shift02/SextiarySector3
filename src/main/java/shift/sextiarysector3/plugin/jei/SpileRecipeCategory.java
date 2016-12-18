package shift.sextiarysector3.plugin.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.util.Translator;

public class SpileRecipeCategory extends RecipeCategoryBase<SpileRecipeWrapper> {

    public static final String SPILE = SextiarySector3.MODID + ".spile";

    private final IDrawable background;
    private final String localizedName;

    protected static final int inputSlot = 1;
    protected static final int outputFluidSlot = 0;

    public SpileRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper);

        ResourceLocation location = new ResourceLocation(SextiarySector3.MODID, "textures/guis/jei/simple.png");
        background = guiHelper.createDrawable(location, 55, 16, 82, 54);
        localizedName = Translator.translateToLocal("gui.ss3.category.spile");

    }

    @Override
    public String getUid() {

        return SPILE;
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
    public void setRecipe(IRecipeLayout recipeLayout, SpileRecipeWrapper recipeWrapper) {

        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiItemStacks.init(inputSlot, true, 0, 18);
        guiFluidStacks.init(outputFluidSlot, false, 60 - 4 + 1, 18 - 4 + 1, 16 + 8, 16 + 8, 1000, true, null);

        guiItemStacks.setFromRecipe(inputSlot, recipeWrapper.getInputs());
        guiFluidStacks.set(outputFluidSlot, recipeWrapper.getFluidOutputs());

    }

}
