package shift.sextiarysector3.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public void setCustomModelResourceLocation(Item item, int metadata, String resource) {

    }

    public void setCustomStateMapper(Block block, String resource) {

    }

    public void setCustomModelResourceLocation(Item item, int metadata, String resource, IBlockState state) {

    }

    public <T extends TileEntity> void setCustomTileEntitySpecialRenderer(Item itemBlock,
            Class<T> tileEntityClass) {

    }

    public void initTileEntitySpecialRenderer() {

    }

    public void preInitModuleClient(FMLPreInitializationEvent event) {

    }

    public void loadModuleClient(FMLInitializationEvent event) {

    }

    public boolean hasAchievementUnlocked(EntityPlayer player, Achievement achievement) {

        EntityPlayerMP playerMP = (EntityPlayerMP) player;

        StatisticsManager state = playerMP.getStatFile();

        return state.hasAchievementUnlocked(achievement);

    }

}
