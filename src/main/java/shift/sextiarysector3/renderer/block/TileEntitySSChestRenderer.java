package shift.sextiarysector3.renderer.block;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector3.block.BlockSSChest;
import shift.sextiarysector3.tileentity.TileEntitySSChest;

public class TileEntitySSChestRenderer extends TileEntitySpecialRenderer<TileEntitySSChest> {

    private static final ResourceLocation TEXTURE_NORMAL_DOUBLE = new ResourceLocation("textures/entity/chest/normal_double.png");
    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation("textures/entity/chest/normal.png");
    private final ModelChest simpleChest = new ModelChest();
    private final ModelChest largeChest = new ModelLargeChest();
    private boolean isChristmas;

    private TileEntitySSChest tileE = new TileEntitySSChest();

    public TileEntitySSChestRenderer() {
        Calendar calendar = Calendar.getInstance();

        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            this.isChristmas = true;
        }
    }

    @Override
    public void renderTileEntityAt(TileEntitySSChest te, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        int i;

        if (te == null) {
            te = this.tileE;
        }

        if (te.hasWorldObj()) {
            Block block = te.getBlockType();
            i = te.getBlockMetadata();

            if (block instanceof BlockSSChest && i == 0) {
                ((BlockSSChest) block).checkForSurroundingChests(te.getWorld(), te.getPos(), te.getWorld().getBlockState(te.getPos()));
                i = te.getBlockMetadata();
            }

            te.checkForAdjacentChests();
        } else {
            i = 0;
        }

        if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null) {
            ModelChest modelchest;

            if (te.adjacentChestXPos == null && te.adjacentChestZPos == null) {
                modelchest = this.simpleChest;

                if (destroyStage >= 0) {
                    this.bindTexture(DESTROY_STAGES[destroyStage]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(4.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                } else {
                    this.bindTexture(this.getNormalSize());
                }
            } else {
                modelchest = this.largeChest;

                if (destroyStage >= 0) {
                    this.bindTexture(DESTROY_STAGES[destroyStage]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(8.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                } else {
                    this.bindTexture(this.getDoubleSize());
                }
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            if (destroyStage < 0) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            int j = 0;

            if (i == 2) {
                j = 180;
            }

            if (i == 3) {
                j = 0;
            }

            if (i == 4) {
                j = 90;
            }

            if (i == 5) {
                j = -90;
            }

            if (i == 2 && te.adjacentChestXPos != null) {
                GlStateManager.translate(1.0F, 0.0F, 0.0F);
            }

            if (i == 5 && te.adjacentChestZPos != null) {
                GlStateManager.translate(0.0F, 0.0F, -1.0F);
            }

            GlStateManager.rotate(j, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;

            if (te.adjacentChestZNeg != null) {
                float f1 = te.adjacentChestZNeg.prevLidAngle + (te.adjacentChestZNeg.lidAngle - te.adjacentChestZNeg.prevLidAngle) * partialTicks;

                if (f1 > f) {
                    f = f1;
                }
            }

            if (te.adjacentChestXNeg != null) {
                float f2 = te.adjacentChestXNeg.prevLidAngle + (te.adjacentChestXNeg.lidAngle - te.adjacentChestXNeg.prevLidAngle) * partialTicks;

                if (f2 > f) {
                    f = f2;
                }
            }

            f = 1.0F - f;
            f = 1.0F - f * f * f;
            modelchest.chestLid.rotateAngleX = -(f * ((float) Math.PI / 2F));
            modelchest.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (destroyStage >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }
        }
    }

    public ResourceLocation getNormalSize() {
        return this.TEXTURE_NORMAL;
    }

    public ResourceLocation getDoubleSize() {
        return this.TEXTURE_NORMAL_DOUBLE;
    }

}