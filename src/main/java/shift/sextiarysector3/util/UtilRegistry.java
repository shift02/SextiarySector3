package shift.sextiarysector3.util;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import shift.sextiarysector3.SextiarySector3;

public class UtilRegistry {

    public static File itemModel;

    public static File blockState;

    public static void registerNormalItem(Item item, String registryName, String resource) {
        registerNormalItem(item, registryName, resource, null);
    }

    public static void registerToolItem(Item item, String registryName, String resource) {
        registerNormalItem(item, registryName, resource, "item/handheld");
    }

    public static void registerNormalItem(Item item, String registryName, String resource, String parent) {

        GameRegistry.register(item.setRegistryName(SextiarySector3.MODID, registryName));

        if (getSide().isClient()) {

            SextiarySector3.proxy.setCustomModelResourceLocation(item, 0, resource);

            if (item instanceof ISubItem) {

                for (int i = 1; i < ((ISubItem) item).getSubSize(); i++) {
                    SextiarySector3.proxy.setCustomModelResourceLocation(item, i, ((ISubItem) item).getResourcesLocation(i));
                }

            }

        }

        File f = new File(itemModel, resource + ".json");

        if (SextiarySector3.isDebug && !f.exists() && getSide().isClient()) {
            ItemJsonUtil.generationItemGson(f, resource, parent);
        }

    }

    public static void registerAnimationItem(Item item, String registryName, String resource, int size) {

        GameRegistry.register(item.setRegistryName(SextiarySector3.MODID, registryName));

        if (getSide().isClient()) {

            SextiarySector3.proxy.setCustomModelResourceLocation(item, 0, resource);

        }

        File f = new File(itemModel, resource + ".json");

        if (SextiarySector3.isDebug && !f.exists() && getSide().isClient()) {
            ItemJsonUtil.generationAnimationItemGson(f, itemModel, resource, size);
        }

    }

    public static <T extends TileEntity> void registerCustomItem(Item item, String registryName, String resource,
            Class<T> tileEntityClass) {

        GameRegistry.register(item.setRegistryName(SextiarySector3.MODID, registryName));

        GameRegistry.registerTileEntity(tileEntityClass, SextiarySector3.MODID + ":" + registryName);

        if (getSide().isClient()) {

            SextiarySector3.proxy.setCustomModelResourceLocation(item, 0, resource);

            //C
            SextiarySector3.proxy.setCustomTileEntitySpecialRenderer(item, tileEntityClass);

        }

        File f = new File(itemModel, resource + ".json");

        if (SextiarySector3.isDebug && !f.exists() && getSide().isClient()) {
            ItemJsonUtil.generationItemGson(f, resource, null);
        }

    }

    public static void registerNormalBlock(Block block, String registryName, String resource) {

        ItemBlock itemBlock = new ItemBlock(block);
        registerNormalBlock(block, itemBlock, registryName, resource);

        //Json生成
        File f = new File(blockState, resource + ".json");

        if (SextiarySector3.isDebug && !f.exists() && getSide().isClient()) {
            BlockJsonUtil.generationBlockStateGson(f, resource, null);
        }

    }

    public static void registerNormalBlock(Block block, Item itemBlock, String registryName, String resource) {

        //登録
        registerBlock(block, itemBlock, registryName);

        //描画の登録
        if (getSide().isClient() && block.getMaterial(null) != Material.AIR) {

            // ブロック状態の登録
            SextiarySector3.proxy.setCustomStateMapper(block, resource);

            if (block instanceof IMetaItem) {

                //Meta
                for (int i = 0; i < ((IMetaItem) block).getMetaSize(); i++) {

                    SextiarySector3.proxy.setCustomModelResourceLocation(itemBlock, i, resource, block.getStateFromMeta(i));

                }
            } else {

                //普通
                // アイテム状態の登録
                SextiarySector3.proxy.setCustomModelResourceLocation(itemBlock, 0, resource);
            }

        }

    }

    public static <T extends TileEntity> void registerTESRBlock(
            Block block,
            Class<T> tileEntityClass,
            String registryName, String resource) {

        ItemBlock itemBlock = new ItemBlock(block);
        registerTESRBlock(block, itemBlock, tileEntityClass, registryName, resource);

    }

    public static <T extends TileEntity> void registerTESRBlock(
            Block block, Item itemBlock,
            Class<T> tileEntityClass,
            String registryName, String resource) {

        //登録
        registerBlock(block, itemBlock, registryName);

        //描画の登録
        if (getSide().isClient()) {

            // ブロック状態の登録
            SextiarySector3.proxy.setCustomStateMapper(block, resource);

            SextiarySector3.proxy.setCustomTileEntitySpecialRenderer(itemBlock, tileEntityClass);
            SextiarySector3.proxy.setCustomModelResourceLocation(itemBlock, 0, resource);
        }

    }

    /**
     * Blockを登録
     * @param block 登録するブロック
     * @param itemBlock アイテム状態のブロック
     * @param registryName 登録名
     */
    private static void registerBlock(Block block, Item itemBlock, String registryName) {

        GameRegistry.register(block.setRegistryName(SextiarySector3.MODID, registryName));
        GameRegistry.register(itemBlock.setRegistryName(SextiarySector3.MODID, registryName));

    }

    public static Side getSide() {
        return FMLCommonHandler.instance().getSide();
    }

}
