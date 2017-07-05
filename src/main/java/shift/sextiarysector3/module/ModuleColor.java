package shift.sextiarysector3.module;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;
import shift.sextiarysector3.api.season.Season;
import shift.sextiarysector3.util.SeasonManager;

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
    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {

    }

    @Override
    public void load(FMLInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void loadClient(FMLInitializationEvent event) {

        final BlockColors block = FMLClientHandler.instance().getClient().getBlockColors();

        net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();

        //ゴム
        block.registerBlockColorHandler(new IBlockColor() {

            @Override
            public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {

                return (worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic());

            }
        }, SSBlocks.rubberLeaves);

        //カエデ
        block.registerBlockColorHandler(new IBlockColor() {

            @Override
            public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {

                if (SeasonManager.getInstance().getSeason(mc.theWorld) == Season.AUTUMN) return 0xFE2E2E;

                return (worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic());

            }
        }, SSBlocks.mapleLeaves);

        //梅
        block.registerBlockColorHandler(new IBlockColor() {

            @Override
            public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {

                if (tintIndex > 0) return 0xFFFFFF;

                if (tintIndex == 0 && SeasonManager.getInstance().getSeason(mc.theWorld) == Season.SPRING) return 0xfce0e0;

                return (worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic());

            }
        }, SSBlocks.plumLeaves);

        //バニラ
        block.registerBlockColorHandler(new IBlockColor() {
            @Override
            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {

                BlockPlanks.EnumType blockplanks$enumtype = state.getValue(BlockOldLeaf.VARIANT);

                if (SeasonManager.getInstance().getSeason(mc.theWorld) == Season.AUTUMN) {

                    if (blockplanks$enumtype == BlockPlanks.EnumType.BIRCH) {
                        return 0xFF8000;
                    }

                }

                return blockplanks$enumtype == BlockPlanks.EnumType.SPRUCE ? ColorizerFoliage.getFoliageColorPine()
                        : (blockplanks$enumtype == BlockPlanks.EnumType.BIRCH ? ColorizerFoliage.getFoliageColorBirch()
                                : (worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic()));
            }
        }, new Block[] { Blocks.LEAVES });

        ItemColors itemcolors = FMLClientHandler.instance().getClient().getItemColors();

        itemcolors.registerItemColorHandler(new IItemColor() {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                return block.colorMultiplier(iblockstate, (IBlockAccess) null, (BlockPos) null, tintIndex);
            }
        }, new Block[] { SSBlocks.rubberLeaves });

        itemcolors.registerItemColorHandler(new IItemColor() {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                return block.colorMultiplier(iblockstate, (IBlockAccess) null, (BlockPos) null, tintIndex);
            }
        }, new Block[] { SSBlocks.mapleLeaves });

        itemcolors.registerItemColorHandler(new IItemColor() {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                return block.colorMultiplier(iblockstate, (IBlockAccess) null, (BlockPos) null, tintIndex);
            }
        }, new Block[] { SSBlocks.plumLeaves });

        //ポーション
        itemcolors.registerItemColorHandler(new IItemColor() {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                return tintIndex > 0 ? -1 : PotionUtils.getPotionColorFromEffectList(PotionUtils.getEffectsFromStack(stack));
            }
        }, new Item[] { SSItems.potionCapsule });

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
