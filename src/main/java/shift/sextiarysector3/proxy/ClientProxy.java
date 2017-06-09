package shift.sextiarysector3.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.entity.EntityConveyorItem;
import shift.sextiarysector3.module.IModule;
import shift.sextiarysector3.renderer.RenderEntityConveyorItem;
import shift.sextiarysector3.renderer.RendererShield;
import shift.sextiarysector3.renderer.block.RendererConveyor;
import shift.sextiarysector3.renderer.block.RendererCreeperChest;
import shift.sextiarysector3.renderer.block.RendererShaft;
import shift.sextiarysector3.renderer.block.RendererShopMonitor;
import shift.sextiarysector3.renderer.block.RendererSmallWindmill;
import shift.sextiarysector3.tileentity.TileEntityConveyor;
import shift.sextiarysector3.tileentity.TileEntityCreeperChest;
import shift.sextiarysector3.tileentity.TileEntityShield;
import shift.sextiarysector3.tileentity.TileEntityShopMonitor;
import shift.sextiarysector3.tileentity.TileEntitySmallWindmill;
import shift.sextiarysector3.tileentity.shaft.TileEntityWoodShaft;
import shift.sextiarysector3.util.DefaultStateMapper;

public class ClientProxy extends CommonProxy {

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public void setCustomModelResourceLocation(Item item, int metadata, String resource) {

        ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);
        // アイテム状態の登録
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(l, "inventory"));

    }

    @Override
    public void setCustomModelResourceLocation(Item item, int metadata, String resource, IBlockState state) {

        ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);

        //バリアントを足す
        String variant = propertyStringMapper.getPropertyString(state.getProperties());

        // アイテム状態の登録
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(l, variant));

    }

    @Override
    public void setCustomStateMapper(Block block, String resource) {

        ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);

        // ブロック状態の登録
        ModelLoader.setCustomStateMapper(block, new DefaultStateMapper(l));

    }

    private final StateMapperBase propertyStringMapper = new StateMapperBase() {
        @Override
        protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
            return new ModelResourceLocation("minecraft:stone");
        }
    };

    @Override
    public <T extends TileEntity> void setCustomTileEntitySpecialRenderer(Item itemBlock,
            Class<T> tileEntityClass) {

        ForgeHooksClient.registerTESRItemStack(itemBlock, 0, tileEntityClass);

    }

    @Override
    public void initTileEntitySpecialRenderer() {

        //メンドイけど手動で設定
        //Item
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShield.class, new RendererShield());

        //Block
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodShaft.class, new RendererShaft());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityConveyor.class, new RendererConveyor());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmallWindmill.class, new RendererSmallWindmill());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCreeperChest.class, new RendererCreeperChest());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShopMonitor.class, new RendererShopMonitor());

        //En

        RenderingRegistry.registerEntityRenderingHandler(EntityConveyorItem.class, new IRenderFactory<EntityConveyorItem>() {
            @Override
            public Render<? super EntityConveyorItem> createRenderFor(RenderManager manager) {
                return new RenderEntityConveyorItem(manager, Minecraft.getMinecraft().getRenderItem());
            }
        });

    }

    @Override
    public void preInitModuleClient(FMLPreInitializationEvent event) {
        for (IModule m : SextiarySector3.modules) {
            m.preInitClient(event);
        }
    }

    @Override
    public void loadModuleClient(FMLInitializationEvent event) {
        for (IModule m : SextiarySector3.modules) {
            m.loadClient(event);
        }
    }

    @Override
    public boolean hasAchievementUnlocked(EntityPlayer player, Achievement achievement) {

        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;

            StatisticsManager state = playerMP.getStatFile();

            return state.hasAchievementUnlocked(achievement);
        }

        EntityPlayerSP playerSP = (EntityPlayerSP) player;

        StatisticsManager state = playerSP.getStatFileWriter();

        return state.hasAchievementUnlocked(achievement);

    }

}
