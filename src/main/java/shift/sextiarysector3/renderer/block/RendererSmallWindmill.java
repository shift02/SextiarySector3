package shift.sextiarysector3.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.block.BlockSmallWindmill;
import shift.sextiarysector3.renderer.model.ModelSmallWindMill;
import shift.sextiarysector3.tileentity.TileEntitySmallWindmill;

public class RendererSmallWindmill extends TileEntitySpecialRenderer<TileEntitySmallWindmill> {

    private static final ResourceLocation windmillTextures = new ResourceLocation(SextiarySector3.MODID, "textures/models/small_windmill.png");
    static public ModelBase modelWindmill = new ModelSmallWindMill();
    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

    public void renderInventoryBlock() {

        GL11.glPushMatrix();

        float scale = 0.0625f / 2.0f;
        GL11.glScalef(scale, scale, scale);

        //GL11.glRotatef(90, 1, 0, 0);

        GL11.glRotatef(90, 0, -1, 0);
        this.bindTexture(windmillTextures);

        ((ModelSmallWindMill) modelWindmill).renderinOut(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glRotatef(-(FMLClientHandler.instance().getClient().getMinecraft().getSystemTime() / 50) % 360, 0, 0, 1);

        modelWindmill.render(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

        this.bindTexture(MC_BLOCK_SHEET);

    }

    @Override
    public void renderTileEntityAt(TileEntitySmallWindmill tileentity, double x, double y, double z, float partialTicks, int destroyStage) {

        if (tileentity == null) {

            renderInventoryBlock();
            return;
        }

        //破壊処理
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        //

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        float scale = 0.0625f;
        //float scale =  0.125f;
        GL11.glScalef(scale, scale, scale);

        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 2.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            this.bindTexture(windmillTextures);
        }

        GlStateManager.enableRescaleNormal();

        IBlockState state = tileentity.getWorld().getBlockState(tileentity.getPos());
        EnumFacing f = state.getValue(BlockSmallWindmill.FACING);

        switch (f) {
        case UP:
            GL11.glRotatef(90, 1, 0, 0);
            break;
        case DOWN:
            GL11.glRotatef(90, -1, 0, 0);
            break;
        case WEST:
            GL11.glRotatef(90, 0, 1, 0);
            break;
        case EAST:
            GL11.glRotatef(90, 0, -1, 0);
            break;
        case SOUTH:
            GL11.glRotatef(180, 0, 1, 0);
            break;
        default:
            break;
        }

        ((ModelSmallWindMill) modelWindmill).renderinOut(null, 0, 0, 0, 0, 0, 1.0f);
        //傾きのスピード
        //GL11.glRotatef(tile.getRotateStep(), 0, 0, 1);

        modelWindmill.render(null, 0, 0, 0, 0, 0, 1.0f);

        GlStateManager.disableRescaleNormal();

        GL11.glPopMatrix();

        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

    }

}
