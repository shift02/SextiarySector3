package shift.sextiarysector3.util;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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

			ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);
			// アイテム状態の登録
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(l, "inventory"));

		}

		File f = new File(itemModel, resource + ".json");

		if (SextiarySector3.isDebug && !f.exists()) {
			ItemJsonUtil.generationItemGson(f, resource, parent);
		}

	}

	public static <T extends TileEntity> void registerCustomItem(Item item, String registryName, String resource,
			Class<T> tileEntityClass, TileEntitySpecialRenderer<? super T> specialRenderer) {

		GameRegistry.register(item.setRegistryName(SextiarySector3.MODID, registryName));

		GameRegistry.registerTileEntity(tileEntityClass, SextiarySector3.MODID + ":" + registryName);

		if (getSide().isClient()) {

			ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);
			// アイテム状態の登録
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(l, "inventory"));

			//C
			ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
			ForgeHooksClient.registerTESRItemStack(item, 0, tileEntityClass);

		}

		File f = new File(itemModel, resource + ".json");

		if (SextiarySector3.isDebug && !f.exists()) {
			ItemJsonUtil.generationItemGson(f, resource, null);
		}

	}

	public static void registerNormalBlock(Block block, String registryName, String resource) {

		ItemBlock itemBlock = new ItemBlock(block);
		registerNormalBlock(block, itemBlock, registryName, resource);

		//Json生成
		File f = new File(blockState, resource + ".json");

		if (SextiarySector3.isDebug && !f.exists()) {
			BlockJsonUtil.generationBlockStateGson(f, resource, null);
		}

	}

	public static void registerNormalBlock(Block block, Item itemBlock, String registryName, String resource) {

		//登録
		registerBlock(block, itemBlock, registryName);

		//描画の登録
		if (getSide().isClient() && block.getMaterial(null) != Material.AIR) {

			ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);

			// ブロック状態の登録
			ModelLoader.setCustomStateMapper(block, new DefaultStateMapper(l));
			// アイテム状態の登録
			ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(l, "inventory"));
		}

	}

	public static <T extends TileEntity> void registerTESRBlock(
			Block block, Item itemBlock,
			Class<T> tileEntityClass, TileEntitySpecialRenderer<? super T> specialRenderer,
			String registryName, String resource) {

		//登録
		registerBlock(block, itemBlock, registryName);

		//描画の登録
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
		ForgeHooksClient.registerTESRItemStack(itemBlock, 0, tileEntityClass);

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

	@SideOnly(Side.CLIENT)
	public static class DefaultStateMapper extends StateMapperBase {

		ResourceLocation location;

		public DefaultStateMapper(ResourceLocation location) {
			this.location = location;
		}

		protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
			return new ModelResourceLocation(
					new ResourceLocation(this.location.getResourceDomain(), this.location.getResourcePath()),
					this.getPropertyString(state.getProperties()));
		}
	}

}
