package shift.sextiarysector3.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.api.shop.IShopMemory;
import shift.sextiarysector3.block.BlockSSHorizontal;
import shift.sextiarysector3.block.BlockShopMonitor;
import shift.sextiarysector3.renderer.model.ModelMonitor;
import shift.sextiarysector3.tileentity.TileEntityShopMonitor;

public class RendererShopMonitor extends TileEntitySpecialRenderer<TileEntityShopMonitor> {

    private static final ResourceLocation monitorTextures = new ResourceLocation(SextiarySector3.MODID, "textures/models/monitor/monitor.png");
    static public ModelBase modelMonitor = new ModelMonitor();

    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

    public void renderInventoryBlock() {

        GL11.glPushMatrix();

        float scale = 0.0625f / 1.0f;
        GL11.glScalef(scale, scale, scale);

        GL11.glTranslatef(8.0f, 8.0f, 8.0f);
        GL11.glRotatef(90, 1, 0, 0);

        /*
        GL11.glRotatef(180, 1, 0, 0);
        
        GL11.glRotatef(90, 0, -1, 0);*/
        this.bindTexture(monitorTextures);

        modelMonitor.render(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

        this.bindTexture(MC_BLOCK_SHEET);

    }

    @Override
    public void renderTileEntityAt(TileEntityShopMonitor tileentity, double x, double y, double z, float partialTicks, int destroyStage) {

        if (tileentity == null) {
            this.renderInventoryBlock();
            return;
        }

        IBlockState state = tileentity.getWorld().getBlockState(tileentity.getPos());
        EnumFacing f = state.getValue(BlockSSHorizontal.FACING);
        boolean on = state.getValue(BlockShopMonitor.SWITCH);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        float scale = 0.0625f;
        //float scale =  0.125f;
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(180, 1, 0, 0);

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
        case NORTH:
            GL11.glRotatef(180, 0, 1, 0);
        default:
            break;
        }

        if (!on) {

            this.bindTexture(monitorTextures);

        } else {

            if (tileentity.getMemory() != null) {

                if (tileentity.getMemory().getItem() instanceof IShopMemory) {
                    IShopMemory memory = (IShopMemory) tileentity.getMemory().getItem();

                    this.bindTexture(memory.getMonitorResource());
                } else {
                    this.bindTexture(monitorTextures);
                }

            } else {
                this.bindTexture(monitorTextures);
            }

        }

        modelMonitor.render(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

    }

}
