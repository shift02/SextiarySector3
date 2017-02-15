package shift.sextiarysector3.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.module.IModule;
import shift.sextiarysector3.renderer.RendererShield;
import shift.sextiarysector3.renderer.block.RendererShaft;
import shift.sextiarysector3.tileentity.TileEntityShaft;
import shift.sextiarysector3.tileentity.TileEntityShield;
import shift.sextiarysector3.util.DefaultStateMapper;

public class ClientProxy extends CommonProxy {

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public void setCustomModelResourceLocation(Item item, int metadata, String resource) {

        ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);
        // アイテム状態の登録
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(l, "inventory"));

    }

    public void setCustomStateMapper(Block block, String resource) {

        ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);

        // ブロック状態の登録
        ModelLoader.setCustomStateMapper(block, new DefaultStateMapper(l));

    }

    public <T extends TileEntity> void setCustomTileEntitySpecialRenderer(Item itemBlock,
            Class<T> tileEntityClass) {

        ForgeHooksClient.registerTESRItemStack(itemBlock, 0, tileEntityClass);

    }

    public void initTileEntitySpecialRenderer() {

        //メンドイけど手動で設定
        //Item
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShield.class, new RendererShield());

        //Block
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShaft.class, new RendererShaft());

    }

    public void preInitModuleClient(FMLPreInitializationEvent event) {
        for (IModule m : SextiarySector3.modules) {
            m.preInitClient(event);
        }
    }

    public void loadModuleClient(FMLInitializationEvent event) {
        for (IModule m : SextiarySector3.modules) {
            m.loadClient(event);
        }
    }

}
