package shift.sextiarysector3.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.api.shop.IShopMemory;
import shift.sextiarysector3.module.ModuleShop.ShopSeasonBase;

public class ItemShopMemory extends ItemSSBase implements IShopMemory {

    private ResourceLocation monitor;
    private ShopSeasonBase shop;

    public ItemShopMemory(String resource) {
        this.monitor = new ResourceLocation(SextiarySector3.MODID, "textures/models/monitor/monitor_" + resource + ".png");
        this.setMaxStackSize(1);
        this.setCreativeTab(SextiarySectorAPI.TabSSEconomy);
    }

    @Override
    public ResourceLocation getMonitorResource() {
        return monitor;
    }

    @Override
    public int getShopID(World world, EntityPlayer player) {

        return this.shop.getID();

    }

    public void setShopData(ShopSeasonBase shop) {
        this.shop = shop;
    }

}