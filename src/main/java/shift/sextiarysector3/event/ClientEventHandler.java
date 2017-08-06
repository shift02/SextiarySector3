package shift.sextiarysector3.event;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shift.sextiarysector3.SSItems;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.item.ItemRubberGroves;
import shift.sextiarysector3.util.UtilCompat;

public class ClientEventHandler {

    //ツールチップ
    @SubscribeEvent
    public void RenderTooltipEvent(ItemTooltipEvent event) {

        //event.setScreenWidth(event.getScreenWidth() + 100);
        //event.setScreenHeight(event.getScreenHeight() + 100);

        /*event.getLines().add("");
        event.getLines().add("");
        event.getLines().add("");
        event.getLines().add("");
        event.getLines().add("");
        event.getLines().add("");
        event.getLines().add("");*/

        return;

        /*
        event.getToolTip().add("");
        event.getToolTip().add("");
        event.getToolTip().add("");
        event.getToolTip().add("");*/

    }

    public ItemStack bb;

    //ツールチップ
    @SubscribeEvent
    public void RenderTooltipEvent(RenderTooltipEvent.PostText event) {

        return;

        /*
        int x = event.getX();
        int y = event.getY();
        ItemStack item = event.getStack();
        
        Minecraft minecraft = Minecraft.getMinecraft();
        RenderItem renderItem = minecraft.getRenderItem();
        
        //ツールチップ全般で呼ばれるのでアイテムがnullならスキップ
        if (item == null) return;
        
        if (bb == null) {
        	bb = new ItemStack(Items.DIAMOND);
        }
        
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableLighting();
        renderItem.zLevel = 100.0F;
        renderItem.renderItemAndEffectIntoGUI(item, x, y + 30);
        renderItem.renderItemAndEffectIntoGUI(bb, x + 16, y + 30);
        renderItem.zLevel = 0.0F;
        
        GlStateManager.disableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();*/

    }

    @SubscribeEvent
    public void onDrawBlockHighlight(DrawBlockHighlightEvent event) {

        EntityPlayer player = event.getPlayer();
        RayTraceResult movingObjectPositionIn = event.getTarget();
        int execute = event.getSubID();
        float partialTicks = event.getPartialTicks();
        EnumFacing facing = movingObjectPositionIn.sideHit;

        movingObjectPositionIn = ((ItemRubberGroves) SSItems.rubberGloves).rayTrace(player.worldObj, player, false);

        if (execute != 0) return;
        if (movingObjectPositionIn == null) return;
        if (movingObjectPositionIn.typeOfHit != RayTraceResult.Type.BLOCK) return;
        if (UtilCompat.isNullFromItemStack(player.getHeldItem(EnumHand.MAIN_HAND)) && UtilCompat.isNullFromItemStack(player.getHeldItem(EnumHand.OFF_HAND))) return;

        boolean isRubber = false;

        isRubber = (!UtilCompat.isNullFromItemStack(player.getHeldItem(EnumHand.MAIN_HAND)) && player.getHeldItem(EnumHand.MAIN_HAND).getItem() == SSItems.rubberGloves);
        if (!isRubber) isRubber = ((!UtilCompat.isNullFromItemStack(player.getHeldItem(EnumHand.OFF_HAND))) && player.getHeldItem(EnumHand.OFF_HAND).getItem() == SSItems.rubberGloves);

        if (!isRubber) return;

        //向きの上書き
        facing = movingObjectPositionIn.sideHit;

        BlockPos blockpos = movingObjectPositionIn.getBlockPos();

        boolean isFacing = false;

        for (IProperty<?> prop : player.worldObj.getBlockState(blockpos).getProperties().keySet()) {

            if (prop.getName().equals("facing") && prop instanceof PropertyDirection) {
                isFacing = true;
            }

        }

        if (!isFacing) return;

        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(2.0F);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        //BlockPos blockpos = movingObjectPositionIn.getBlockPos();
        IBlockState iblockstate = player.worldObj.getBlockState(blockpos);

        AxisAlignedBB FULL_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

        FULL_BLOCK_AABB = FULL_BLOCK_AABB.offset(blockpos);

        if (iblockstate.getMaterial() != Material.AIR && player.worldObj.getWorldBorder().contains(blockpos)) {
            double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) partialTicks;
            double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) partialTicks;
            double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) partialTicks;
            func_189697_a(FULL_BLOCK_AABB.expandXyz(0.0020000000949949026D).offset(-d0, -d1, -d2), 0.9F, 0.9F, 0.9F, 0.8F, facing);
        }

        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();

    }

    public static void func_189697_a(AxisAlignedBB p_189697_0_, float p_189697_1_, float p_189697_2_, float p_189697_3_, float p_189697_4_, EnumFacing facing) {
        func_189694_a(p_189697_0_.minX, p_189697_0_.minY, p_189697_0_.minZ, p_189697_0_.maxX, p_189697_0_.maxY, p_189697_0_.maxZ, p_189697_1_, p_189697_2_, p_189697_3_, p_189697_4_, facing);
    }

    public static void func_189694_a(double p_189694_0_, double p_189694_2_, double p_189694_4_, double p_189694_6_, double p_189694_8_, double p_189694_10_, float p_189694_12_, float p_189694_13_,
            float p_189694_14_, float p_189694_15_, EnumFacing facing) {

        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);

        if (facing.equals(EnumFacing.UP)) {
            drawLineUpDoun(vertexbuffer, p_189694_0_, p_189694_2_, p_189694_4_, p_189694_6_, p_189694_8_, p_189694_10_, p_189694_12_, p_189694_13_, p_189694_14_, p_189694_15_);
        }

        if (facing.equals(EnumFacing.DOWN)) {
            drawLineUpDoun(vertexbuffer, p_189694_0_, p_189694_2_, p_189694_4_, p_189694_6_, p_189694_8_ - 1.01, p_189694_10_, p_189694_12_, p_189694_13_, p_189694_14_, p_189694_15_);
        }

        if (facing.equals(EnumFacing.NORTH)) {
            drawLineNorthSouth(vertexbuffer, p_189694_0_, p_189694_2_, p_189694_4_, p_189694_6_, p_189694_8_, p_189694_10_, p_189694_12_, p_189694_13_, p_189694_14_, p_189694_15_);
        }

        if (facing.equals(EnumFacing.SOUTH)) {
            drawLineNorthSouth(vertexbuffer, p_189694_0_, p_189694_2_, p_189694_4_ + 1.01, p_189694_6_, p_189694_8_, p_189694_10_, p_189694_12_, p_189694_13_, p_189694_14_, p_189694_15_);
        }

        if (facing.equals(EnumFacing.WEST)) {
            drawLineWE(vertexbuffer, p_189694_0_, p_189694_2_, p_189694_4_, p_189694_6_, p_189694_8_, p_189694_10_, p_189694_12_, p_189694_13_, p_189694_14_, p_189694_15_);
        }

        if (facing.equals(EnumFacing.EAST)) {
            drawLineWE(vertexbuffer, p_189694_0_ + 1.01, p_189694_2_, p_189694_4_, p_189694_6_, p_189694_8_, p_189694_10_, p_189694_12_, p_189694_13_, p_189694_14_, p_189694_15_);
        }

        tessellator.draw();

    }

    public static void drawLineUpDoun(VertexBuffer p_189698_0_, double p_189698_1_, double p_189698_3_, double p_189698_5_, double p_189698_7_, double p_189698_9_, double p_189698_11_,
            float p_189698_13_, float p_189698_14_, float p_189698_15_, float p_189698_16_) {

        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, 0).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_, p_189698_9_, p_189698_11_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_11_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();

        p_189698_0_.pos(p_189698_1_ + 0.25, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, 0).endVertex();
        p_189698_0_.pos(p_189698_1_ + 0.25, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_ - 0.25, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_ - 0.25, p_189698_9_, p_189698_11_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_ + 0.25, p_189698_9_, p_189698_11_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_ + 0.25, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();

        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_5_ + 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, 0).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_5_ + 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_, p_189698_9_, p_189698_5_ + 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_, p_189698_9_, p_189698_11_ - 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_11_ - 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_5_ + 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
    }

    public static void drawLineNorthSouth(VertexBuffer p_189698_0_, double p_189698_1_, double p_189698_3_, double p_189698_5_, double p_189698_7_, double p_189698_9_, double p_189698_11_,
            float p_189698_13_, float p_189698_14_, float p_189698_15_, float p_189698_16_) {

        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, 0).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();

        p_189698_0_.pos(p_189698_1_ + 0.25, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, 0).endVertex();
        p_189698_0_.pos(p_189698_1_ + 0.25, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_ - 0.25, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_ - 0.25, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_ + 0.25, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_ + 0.25, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();

        p_189698_0_.pos(p_189698_1_, p_189698_3_ + 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, 0).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_ + 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_, p_189698_3_ + 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_7_, p_189698_9_ - 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_ - 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_ + 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();

    }

    public static void drawLineWE(VertexBuffer p_189698_0_, double p_189698_1_, double p_189698_3_, double p_189698_5_, double p_189698_7_, double p_189698_9_, double p_189698_11_,
            float p_189698_13_, float p_189698_14_, float p_189698_15_, float p_189698_16_) {

        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, 0).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_11_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_11_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();

        p_189698_0_.pos(p_189698_1_, p_189698_3_ + 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, 0).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_ + 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_ + 0.25, p_189698_11_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_ - 0.25, p_189698_11_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_ - 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_ + 0.25, p_189698_5_).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();

        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_5_ + 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, 0).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_5_ + 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_11_ - 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_11_ - 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_9_, p_189698_5_ + 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();
        p_189698_0_.pos(p_189698_1_, p_189698_3_, p_189698_5_ + 0.25).color(p_189698_13_, p_189698_14_, p_189698_15_, p_189698_16_).endVertex();

    }

    //手の描画

    @SubscribeEvent
    public void onDrawBlockHighlight(RenderSpecificHandEvent event) {

        if (UtilCompat.isNullFromItemStack(event.getItemStack())) return;

        if (event.getItemStack().getItem() != SSItems.rubberGloves) return;

        GlStateManager.pushMatrix();

        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().thePlayer;
        boolean flag = event.getHand() == EnumHand.MAIN_HAND;
        EnumHandSide enumhandside = flag ? abstractclientplayer.getPrimaryHand() : abstractclientplayer.getPrimaryHand().opposite();

        this.renderArmFirstPerson(Minecraft.getMinecraft().getRenderManager(), event.getEquipProgress(), event.getSwingProgress(), enumhandside);
        event.setCanceled(true);

        GlStateManager.popMatrix();

    }

    private ResourceLocation rubberSkin = new ResourceLocation(SextiarySector3.MODID, "textures/entity/rubber_gloves.png");

    private void renderArmFirstPerson(RenderManager renderManager, float p_187456_1_, float p_187456_2_, EnumHandSide p_187456_3_) {

        boolean flag = p_187456_3_ != EnumHandSide.LEFT;
        float f = flag ? 1.0F : -1.0F;
        float f1 = MathHelper.sqrt_float(p_187456_2_);
        float f2 = -0.3F * MathHelper.sin(f1 * (float) Math.PI);
        float f3 = 0.4F * MathHelper.sin(f1 * ((float) Math.PI * 2F));
        float f4 = -0.4F * MathHelper.sin(p_187456_2_ * (float) Math.PI);
        GlStateManager.translate(f * (f2 + 0.64000005F), f3 + -0.6F + p_187456_1_ * -0.6F, f4 + -0.71999997F);
        GlStateManager.rotate(f * 45.0F, 0.0F, 1.0F, 0.0F);
        float f5 = MathHelper.sin(p_187456_2_ * p_187456_2_ * (float) Math.PI);
        float f6 = MathHelper.sin(f1 * (float) Math.PI);
        GlStateManager.rotate(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().thePlayer;
        //Minecraft.getMinecraft().getTextureManager().bindTexture(abstractclientplayer.getLocationSkin());
        Minecraft.getMinecraft().getTextureManager().bindTexture(rubberSkin);
        GlStateManager.translate(f * -1.0F, 3.6F, 3.5F);
        GlStateManager.rotate(f * 120.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f * -135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(f * 5.6F, 0.0F, 0.0F);
        RenderPlayer renderplayer = (RenderPlayer) renderManager.getEntityRenderObject(abstractclientplayer);
        GlStateManager.disableCull();

        if (flag) {
            renderplayer.renderRightArm(abstractclientplayer);
        } else {
            renderplayer.renderLeftArm(abstractclientplayer);
        }

        GlStateManager.enableCull();
    }

    //Block破壊
    @SubscribeEvent
    public void renderExtraBlockBreak(RenderWorldLastEvent event) {

        //event.getContext()

        //RenderGlobal.c.drawBlockDamageTexture(null, null, null, 0);
        //event.getContext().sendBlockBreakProgress(0, null, 0);

    }

}
