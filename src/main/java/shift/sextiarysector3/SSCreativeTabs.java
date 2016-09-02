package shift.sextiarysector3;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class SSCreativeTabs {

	public static void initCreativeTabs() {

		SextiarySectorAPI.TabSSCore = new CreativeTabSSCore();
		SextiarySectorAPI.TabSSForestry = new CreativeTabSSForestry();
		SextiarySectorAPI.TabSSMining = new CreativeTabSSMining();
		SextiarySectorAPI.TabSSIndustry = new CreativeTabSSIndustry();

	}

	private static class CreativeTabSSCore extends CreativeTabs {

		public CreativeTabSSCore() {
			super("ss.core");
		}

		@Override
		public Item getTabIconItem() {
			return null;// SSBlocks.LargeFurnace.g;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() {
			return new ItemStack(Items.BOAT, 1);
		}

	}

	private static class CreativeTabSSForestry extends CreativeTabs {

		public CreativeTabSSForestry() {
			super("ss.forestry");
		}

		@Override
		public Item getTabIconItem() {
			return null;// SSBlocks.LargeFurnace.g;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() {
			return new ItemStack(SSBlocks.rubberLog, 1);
		}

	}

	private static class CreativeTabSSMining extends CreativeTabs {

		public CreativeTabSSMining() {
			super("ss.mining");
		}

		@Override
		public Item getTabIconItem() {
			return null;//SSBlocks.LargeFurnace.g;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() {
			return new ItemStack(SSBlocks.orichalcumOre, 1);
		}

	}

	private static class CreativeTabSSIndustry extends CreativeTabs {

		public CreativeTabSSIndustry() {
			super("ss.industry");
		}

		@Override
		public Item getTabIconItem() {
			return null;//SSBlocks.LargeFurnace.g;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() {
			return new ItemStack(SSItems.plastic, 1);
		}

	}

}
