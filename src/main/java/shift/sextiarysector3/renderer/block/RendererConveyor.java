package shift.sextiarysector3.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.CapabilityItemHandler;
import shift.sextiarysector3.tileentity.TileEntityConveyor;
import shift.sextiarysector3.util.UtilCompat;

public class RendererConveyor extends TileEntitySpecialRenderer<TileEntityConveyor> {

    @Override
    public void renderTileEntityAt(TileEntityConveyor tileentity, double x, double y, double z, float partialTicks, int destroyStage) {

        if (tileentity == null) return;

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.08F, (float) z + 0.5F);

        ItemStack item = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP).getStackInSlot(0);

        if (!UtilCompat.isNullFromItemStack(item)) {

            float f1 = MathHelper.sin((tileentity.getWorld().getWorldInfo().getWorldTime() + partialTicks) / 10.0F + 0) * 0.05F + 0.05F;
            float f2 = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(item, null, null).getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y;
            GlStateManager.translate(0, f1 + 0.25F * f2, 0);

            if (!Minecraft.getMinecraft().getRenderItem().shouldRenderItemIn3D(item) || item.getItem() instanceof ItemSkull) {
                //GlStateManager.translate(0, -0.05, 0);
            }

            float f3 = ((tileentity.getWorld().getWorldInfo().getWorldTime() + partialTicks) / 20.0F + 0) * (180F / (float) Math.PI);
            GlStateManager.rotate(f3, 0.0F, 1.0F, 0.0F);

            Minecraft.getMinecraft().getRenderItem().renderItem(item, TransformType.GROUND);
        }

        GL11.glPopMatrix();

    }

}
