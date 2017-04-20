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
import shift.sextiarysector3.api.energy.CapabilityShaftHandler;
import shift.sextiarysector3.block.BlockShaft;
import shift.sextiarysector3.renderer.model.ModelShaft;
import shift.sextiarysector3.tileentity.TileEntityShaft;

public class RendererShaft extends TileEntitySpecialRenderer<TileEntityShaft> {

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

        this.bindTexture(woodShaftTextures);

        modelShaft.render(null, 0, 0, 0, 0, 0, 1.0f);
        modelShaft.renderIn(null, 0, 0, 0, 0, 0, 1.0f);
        modelShaft.renderOut(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

        this.bindTexture(MC_BLOCK_SHEET);
    }

    private static final ResourceLocation woodShaftTextures = new ResourceLocation(SextiarySector3.MODID, "textures/models/shaft/wood_shaft_off.png");
    private static final ResourceLocation woodShaftOnTextures = new ResourceLocation(SextiarySector3.MODID, "textures/models/shaft/wood_shaft_on.png");
    private static final ResourceLocation stoneShaftTextures = new ResourceLocation("sextiarysector:textures/models/stone_shaft.png");
    private static final ResourceLocation steelShaftTextures = new ResourceLocation("sextiarysector:textures/models/steel_shaft.png");
    private static final ResourceLocation ninjaShaftTextures = new ResourceLocation("sextiarysector:textures/models/ninja_shaft.png");
    private static final ResourceLocation orichalcumShaftTextures = new ResourceLocation("sextiarysector:textures/models/orichalcum_shaft.png");

    static public ModelShaft modelShaft = new ModelShaft();

    @Override
    public void renderTileEntityAt(TileEntityShaft tileentity, double x, double y, double z, float partialTicks, int destroyStage) {

        if (tileentity == null) {

            renderInventoryBlock();
            return;
        }

        TileEntityShaft tile = tileentity;

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
            if (tileentity.getRotateNowStep() == tileentity.getRotateOldStep()) {
                this.bindTexture(woodShaftTextures);
            } else {
                this.bindTexture(woodShaftOnTextures);
            }
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

        IBlockState state = tileentity.getWorld().getBlockState(tileentity.getPos());

        EnumFacing f = state.getValue(BlockShaft.FACING);

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

        if (!this.isIn(tileentity, state.getValue(BlockShaft.FACING))) {
            modelShaft.renderIn(null, 0, 0, 0, 0, 0, 1.0f);
        }

        if (!this.isOut(tileentity, state.getValue(BlockShaft.FACING))) {
            modelShaft.renderOut(null, 0, 0, 0, 0, 0, 1.0f);
        }

        GL11.glPopMatrix();

        if (f.getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE) f = f.getOpposite();

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

    /*
    public static void renderTileEntityAt2(TileEntity tileentity, double x, double y, double z, float f) {
    
        if (!(tileentity instanceof IShaft)) return;
    
        IShaft tile = (IShaft) tileentity;
    
        //System.out.println("renderTileEntityAt");
    
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        float scale = 0.0625f;
        GL11.glScalef(scale, scale, scale);
    
        switch (tile.getStorage().getMaxPower()) {
        case 1:
            bind(woodShaftTextures);
            break;
        case 2:
            bind(stoneShaftTextures);
            break;
        case 3:
            bind(steelShaftTextures);
            break;
        case 4:
            bind(ninjaShaftTextures);
            break;
        case 5:
            bind(orichalcumShaftTextures);
            break;
        }
    
        switch (tile.getDirection()) {
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
    
        if (!isIn(tileentity)) {
            modelShaft.renderIn(null, 0, 0, 0, 0, 0, 1.0f);
        }
    
        if (!isOut(tileentity)) {
            modelShaft.renderOut(null, 0, 0, 0, 0, 0, 1.0f);
        }
    
        //傾きのスピード
        GL11.glRotatef(tile.getRotateStep(), 0, 0, 1);
    
        modelShaft.render(null, 0, 0, 0, 0, 0, 1.0f);
    
        GL11.glPopMatrix();
    
    }
    
    private static void bind(ResourceLocation res) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }*/

    public static boolean isOut(TileEntity tileentity, EnumFacing facing) {

        IBlockState state = tileentity.getWorld().getBlockState(tileentity.getPos());
        BlockPos pos = tileentity.getPos();

        pos = pos.offset(facing);

        TileEntity tile2 = tileentity.getWorld().getTileEntity(pos);
        IBlockState state2 = tileentity.getWorld().getBlockState(pos);
        boolean hasShaft = false;
        if (tile2 != null) {
            hasShaft = tile2.hasCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, null);
        }

        if (hasShaft && state2.getValue(BlockShaft.FACING).getAxis() == state.getValue(BlockShaft.FACING).getAxis()) {
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

        if (hasShaft && state2.getValue(BlockShaft.FACING).getAxis() == state.getValue(BlockShaft.FACING).getAxis()) {
            return true;
        } else {
            return false;
        }

    }

}
