package shift.sextiarysector3.util;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.SextiarySector3;

public class UtilRegistry {

	public static File itemModel;

	public static void registerNormalItem(Item item, String registryName, String resource) {

		GameRegistry.register(item.setRegistryName(SextiarySector3.MODID, registryName));

		if (getSide().isClient()) {

			ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);
			// アイテム状態の登録
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(l, "inventory"));

		}

		File f = new File(itemModel, resource + ".json");

		if (SextiarySector3.isDebug && !f.exists()) {
			ItemJsonUtil.generationItemGson(f, resource);
		}

	}

	public static void registerNormalBlock(Block block, String registryName, String resource) {

		ItemBlock itemBlock = new ItemBlock(block);

		GameRegistry.register(block.setRegistryName(SextiarySector3.MODID, registryName));
		GameRegistry.register(itemBlock.setRegistryName(SextiarySector3.MODID, registryName));

		if (getSide().isClient() && block.getMaterial(null) != Material.AIR) {

			ResourceLocation l = new ResourceLocation(SextiarySector3.MODID, resource);

			// ブロック状態の登録
			ModelLoader.setCustomStateMapper(block, new DefaultStateMapper(l));
			// アイテム状態の登録
			ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(l, "inventory"));
		}

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
