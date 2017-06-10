package shift.sextiarysector3.renderer.block;

import net.minecraft.util.ResourceLocation;
import shift.sextiarysector3.SextiarySector3;

public class RendererPlasticChest extends TileEntitySSChestRenderer {

    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(SextiarySector3.MODID, "textures/models/chest/plastic_chest.png");
    private static final ResourceLocation TEXTURE_NORMAL_DOUBLE = new ResourceLocation(SextiarySector3.MODID, "textures/models/chest/plastic_chest_double.png");

    @Override
    public ResourceLocation getNormalSize() {
        return this.TEXTURE_NORMAL;
    }

    @Override
    public ResourceLocation getDoubleSize() {
        return this.TEXTURE_NORMAL_DOUBLE;
    }
}
