package shift.sextiarysector3.module;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.sextiarysector3.SSBlocks;

public class ModuleColor implements IModule {

	private static ModuleColor instance;

	private ModuleColor() {
	}

	public static ModuleColor getInstance() {
		if (instance == null) {
			instance = new ModuleColor();
		}
		return instance;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Override
	public void load(FMLInitializationEvent event) {

		final BlockColors block = FMLClientHandler.instance().getClient().getBlockColors();

		block.registerBlockColorHandler(new IBlockColor() {

			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {

				return (worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic());

			}
		}, SSBlocks.rubberLeaves);

		ItemColors itemcolors = FMLClientHandler.instance().getClient().getItemColors();

		itemcolors.registerItemColorHandler(new IItemColor() {
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
				return block.colorMultiplier(iblockstate, (IBlockAccess) null, (BlockPos) null, tintIndex);
			}
		}, new Block[] { SSBlocks.rubberLeaves });

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
