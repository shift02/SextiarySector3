package shift.sextiarysector3;

import net.minecraft.creativetab.CreativeTabs;
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
        SextiarySectorAPI.TabSSPharmacy = new CreativeTabSSPharmacy();
        SextiarySectorAPI.TabSSCooking = new CreativeTabSSCooking();
        SextiarySectorAPI.TabSSEconomy = new CreativeTabSSEconomy();

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
            return new ItemStack(SSItems.orichalcumGem, 1);
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

    private static class CreativeTabSSPharmacy extends CreativeTabs {

        public CreativeTabSSPharmacy() {
            super("ss.pharmacy");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSItems.potionCapsule, 1);
        }

    }

    private static class CreativeTabSSCooking extends CreativeTabs {

        public CreativeTabSSCooking() {
            super("ss.cooking");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSBlocks.mapleCake, 1);
        }

    }

    private static class CreativeTabSSEconomy extends CreativeTabs {

        public CreativeTabSSEconomy() {
            super("ss.economy");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSBlocks.creeperChest, 1);
        }

    }

}
