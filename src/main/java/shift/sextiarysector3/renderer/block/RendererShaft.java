package shift.sextiarysector3.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.api.industry.CapabilityShaftHandler;
import shift.sextiarysector3.api.industry.IShaft;
import shift.sextiarysector3.renderer.model.ModelShaft;
import shift.sextiarysector3.tileentity.TileEntityShaftOld;

public class RendererShaft extends TileEntitySpecialRenderer<TileEntityShaftOld> {

    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

    /*
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    
        if (modelID != this.getRenderId()) {
            return;
        }
    
        GL11.glPushMatrix();
    
        float scale = 0.0625f;
        GL11.glScalef(scale, scale, scale);
    
        GL11.glRotatef(90, 1, 0, 0);
    
        if (block == SSBlocks.woodShaft) {
            this.bind(woodShaftTextures);
        } else if (block == SSBlocks.stoneShaft) {
            this.bind(stoneShaftTextures);
        } else if (block == SSBlocks.steelShaft) {
            this.bind(this.steelShaftTextures);
        } else if (block == SSBlocks.ninjaShaft) {
            this.bind(ninjaShaftTextures);
        } else if (block == SSBlocks.orichalcumShaft) {
            this.bind(orichalcumShaftTextures);
        }
    
        modelShaft.render(null, 0, 0, 0, 0, 0, 1.0f);
        modelShaft.renderIn(null, 0, 0, 0, 0, 0, 1.0f);
        modelShaft.renderOut(null, 0, 0, 0, 0, 0, 1.0f);
    
        GL11.glPopMatrix();
    
        this.bind(MC_BLOCK_SHEET);
    
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
            RenderBlocks renderer) {
        return false;
    }
    
    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
    
    @Override
    public int getRenderId() {
        return SextiarySector.instance.proxy.ShaftRenderType;
    }*/

    public void renderInventoryBlock() {
        GL11.glPushMatrix();

        float scale = 0.0625f;
        GL11.glScalef(scale, scale, scale);

        GL11.glTranslatef(8.0f, 8.0f, 8.0f);
        GL11.glRotatef(90, 1, 0, 0);
        //GlStateManager.translate(0.5F, 2.5F, 0.5F);

        this.bindTexture(this.getTexture());

        modelShaft.render(null, 0, 0, 0, 0, 0, 1.0f);
        modelShaft.renderIn(null, 0, 0, 0, 0, 0, 1.0f);
        modelShaft.renderOut(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

        this.bindTexture(MC_BLOCK_SHEET);
    }

    private static final ResourceLocation woodShaftTextures = new ResourceLocation(SextiarySector3.MODID, "textures/models/shaft/wood_shaft.png");
    //private static final ResourceLocation woodShaftTextures = new ResourceLocation(SextiarySector3.MODID, "textures/models/shaft/wood_shaft_off.png");
    private static final ResourceLocation woodShaftOnTextures = new ResourceLocation(SextiarySector3.MODID, "textures/models/shaft/wood_shaft_on.png");
    private static final ResourceLocation stoneShaftTextures = new ResourceLocation("sextiarysector:textures/models/stone_shaft.png");
    private static final ResourceLocation steelShaftTextures = new ResourceLocation("sextiarysector:textures/models/steel_shaft.png");
    private static final ResourceLocation ninjaShaftTextures = new ResourceLocation("sextiarysector:textures/models/ninja_shaft.png");
    private static final ResourceLocation orichalcumShaftTextures = new ResourceLocation("sextiarysector:textures/models/orichalcum_shaft.png");

    static public ModelShaft modelShaft = new ModelShaft();

    @Override
    public void renderTileEntityAt(TileEntityShaftOld tileentity, double x, double y, double z, float partialTicks, int destroyStage) {

        if (tileentity == null) {

            renderInventoryBlock();
            return;
        }

        TileEntityShaftOld tile = tileentity;

        //System.out.println("renderTileEntityAt");]

        //破壊処理
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        float scale = 0.0625f;
        GL11.glScalef(scale, scale, scale);

        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 2.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            //if (tileentity.getRotateNowStep() == tileentity.getRotateOldStep()) {
            this.bindTexture(this.getTexture());
            ///} else {
            //    this.bindTexture(woodShaftOnTextures);
            //}
        }

        /*
        switch (tile.getStorage().getMaxPower()) {
        case 1:
            this.bindTexture(woodShaftTextures);
            break;
        case 2:
            this.bindTexture(stoneShaftTextures);
            break;
        case 3:
            this.bindTexture(steelShaftTextures);
            break;
        case 4:
            this.bindTexture(ninjaShaftTextures);
            break;
        case 5:
            this.bindTexture(orichalcumShaftTextures);
            break;
        }*/

        GlStateManager.enableRescaleNormal();

        IShaft is = tileentity.getCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, null);

        //IBlockState state = tileentity.getWorld().getBlockState(tileentity.getPos());

        EnumFacing f = is.getFacing();//state.getValue(BlockShaft.FACING);

        GL11.glPushMatrix();

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

        GL11.glPopMatrix();

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

        if (!this.isIn(tileentity, f)) {
            modelShaft.renderIn(null, 0, 0, 0, 0, 0, 1.0f);
        }

        if (!this.isOut(tileentity, f)) {
            modelShaft.renderOut(null, 0, 0, 0, 0, 0, 1.0f);
        }

        //傾きのスピード
        float rotate = lerp(tile.getRotateOldStep(), tile.getRotateNowStep(), partialTicks);
        GL11.glRotatef(rotate, 0, 0, 1);

        modelShaft.render(null, 0, 0, 0, 0, 0, 1.0f);

        GlStateManager.disableRescaleNormal();

        GL11.glPopMatrix();

        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

    }

    public float lerp(float prev, float now, float progress) {
        return prev + (now - prev) * progress;
    }

    public static boolean isOut(TileEntity tileentity, EnumFacing facing) {

        IBlockState state = tileentity.getWorld().getBlockState(tileentity.getPos());
        BlockPos pos = tileentity.getPos();

        pos = pos.offset(facing);

        TileEntity tile2 = tileentity.getWorld().getTileEntity(pos);
        //IBlockState state2 = tileentity.getWorld().getBlockState(pos);
        boolean hasShaft = false;
        if (tile2 != null) {
            hasShaft = tile2.hasCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, null);
        }

        if (hasShaft && tile2.getCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, null).getFacing() == facing) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isIn(TileEntity tileentity, EnumFacing facing) {

        IBlockState state = tileentity.getWorld().getBlockState(tileentity.getPos());
        BlockPos pos = tileentity.getPos();

        pos = pos.offset(facing.getOpposite());

        TileEntity tile2 = tileentity.getWorld().getTileEntity(pos);
        IBlockState state2 = tileentity.getWorld().getBlockState(pos);
        boolean hasShaft = false;
        if (tile2 != null) {
            hasShaft = tile2.hasCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, null);
        }

        if (hasShaft && tile2.getCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, null).getFacing() == facing) {
            return true;
        } else {
            return false;
        }

    }

    public ResourceLocation getTexture() {
        return woodShaftTextures;

    }

}
