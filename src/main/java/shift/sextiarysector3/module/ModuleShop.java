package shift.sextiarysector3.module;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.mceconomy3.api.MCEconomyAPI;
import shift.mceconomy3.api.shop.IProduct;
import shift.mceconomy3.api.shop.IShop;
import shift.mceconomy3.api.shop.ProductBase;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;
import shift.sextiarysector3.api.season.Season;
import shift.sextiarysector3.util.SeasonManager;

public class ModuleShop implements IModule {

    private static ModuleShop instance;

    private ModuleShop() {
    }

    public static ModuleShop getInstance() {
        if (instance == null) {
            instance = new ModuleShop();
        }
        return instance;
    }

    public static ShopSeasonBase creeper;

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void loadClient(FMLInitializationEvent event) {

    }

    @Override
    public void load(FMLInitializationEvent event) {

        //クリーパー
        creeper = new ShopSeasonBase("shop.ss.creeper");

        creeper.addProduct(new ProductBase(new ItemStack(Items.BREAD, 1), 120));

        creeper.addProduct(new ProductBase(new ItemStack(SSItems.bluestone, 2), 320));

        creeper.addProduct(new ProductBase(new ItemStack(SSItems.seasonstone, 1), 3000));

        creeper.addProduct(new ProductBase(new ItemStack(SSBlocks.shopMonitor, 1), 14000));

        //春
        creeper.addProduct(Season.SPRING, new ProductBase(new ItemStack(SSBlocks.plumSapling, 1), 7000));

        creeper.addProduct(Season.SPRING, new ProductBase(new ItemStack(SSBlocks.sakuraSapling, 1), 8200));

        SSItems.creeperMemory.setShopData(creeper);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    static public class ShopSeasonBase implements IShop {

        private ArrayList<IProduct>[] productList;
        private String name;

        private int id;

        public ShopSeasonBase(String name) {

            this.name = name;
            this.productList = new ArrayList[4];
            for (int i = 0; i < this.productList.length; i++) {
                this.productList[i] = new ArrayList<IProduct>();
            }

            this.id = MCEconomyAPI.registerShop(this);

        }

        public int getID() {
            return this.id;
        }

        @Override
        public String getShopName(World world, EntityPlayer player) {
            return this.name;
        }

        @Override
        public void addProduct(IProduct product) {
            for (int i = 0; i < this.productList.length; i++) {
                this.productList[i].add(product);
            }
        }

        public void addProduct(Season season, IProduct product) {
            this.productList[season.ordinal()].add(product);
        }

        @Override
        public ArrayList<IProduct> getProductList(World world, EntityPlayer player) {

            if (world == null) return this.productList[0];

            return this.productList[SeasonManager.getInstance().getSeason(world).ordinal()];

        }

    }

}
