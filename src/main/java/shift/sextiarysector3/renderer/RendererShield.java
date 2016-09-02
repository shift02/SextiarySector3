package shift.sextiarysector3.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelShield;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.tileentity.TileEntityShield;

public class RendererShield extends TileEntitySpecialRenderer<TileEntityShield> {

	public static final ResourceLocation SHIELD_BASE_TEXTURE = new ResourceLocation(SextiarySector3.MODID, "textures/entity/plastic_shield.png");

	private final ModelShield modelShield = new ModelShield();

	@Override
	public void renderTileEntityAt(TileEntityShield tes, double x, double y, double z, float partialTicks, int destroyStage) {

		Minecraft.getMinecraft().getTextureManager().bindTexture(this.SHIELD_BASE_TEXTURE);

		GlStateManager.pushMatrix();
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		this.modelShield.render();
		GlStateManager.popMatrix();

	}

}
