package shift.sextiarysector3.renderer.block;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector3.SextiarySector3;

public class RendererPlasticColorChest extends TileEntitySSChestRenderer {

    private ResourceLocation TEXTURE_NORMAL;
    private ResourceLocation TEXTURE_NORMAL_DOUBLE;

    public RendererPlasticColorChest(EnumDyeColor c) {
        TEXTURE_NORMAL = new ResourceLocation(SextiarySector3.MODID, "textures/models/chest/plastic_" + c.getName() + "_chest.png");
        TEXTURE_NORMAL_DOUBLE = new ResourceLocation(SextiarySector3.MODID, "textures/models/chest/plastic_" + c.getName() + "_chest_double.png");
    }

    @Override
    public ResourceLocation getNormalSize() {
        return this.TEXTURE_NORMAL;
    }

    @Override
    public ResourceLocation getDoubleSize() {
        return this.TEXTURE_NORMAL_DOUBLE;
    }
}
