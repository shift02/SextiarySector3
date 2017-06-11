package shift.sextiarysector3.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shift.sextiarysector3.SSAchievements;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;
import shift.sextiarysector3.SextiarySector3;
import shift.sextiarysector3.tileentity.TileEntitySSChest;
import shift.sextiarysector3.util.UtilCompat;

/**
 * 実績関係のイベントを登録する
 * @author Shift02
 *
 */
public class AchievementEventHandler {

    @SubscribeEvent
    public void useEvent(PlayerInteractEvent event) {

        ItemStack item = event.getItemStack();
        EntityPlayer p = event.getEntityPlayer();

        if (!UtilCompat.isNullFromItemStack(item) && item.getItem() == Items.FIREWORKS && item.hasTagCompound()) {

            NBTTagCompound nbt = item.getTagCompound().getCompoundTag("Fireworks");

            if (nbt != null) {
                NBTTagList nbttaglist = nbt.getTagList("Explosions", 10);

                if (nbttaglist != null && nbttaglist.tagCount() > 0) {

                    NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(0);

                    byte b0 = nbttagcompound1.getByte("Type");
                    if (b0 == 3) {
                        p.addStat(SSAchievements.creeperFirework, 1);
                    }

                }

            }

        }

    }

    /**クリーパーチェスト*/
    @SubscribeEvent
    public void LivingSleepingEvent(PlayerWakeUpEvent event) {

        if (event.getEntityPlayer().worldObj.isRemote) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        if (!(player instanceof EntityPlayerMP)) return;

        if (!SextiarySector3.proxy.hasAchievementUnlocked(player, SSAchievements.creeperFirework)
                || SextiarySector3.proxy.hasAchievementUnlocked(player, SSAchievements.creeperChest)) {
            return;
        }

        int x = (int) player.posX;
        int y = (int) player.posY;
        int z = (int) player.posZ;
        BlockPos pos = new BlockPos(x, y, z);
        World world = player.worldObj;

        int range = 1;
        for (int i = -range; i < range; i++) {
            for (int j = -range; j < range; j++) {
                for (int k = -range; k < range; k++) {

                    if (world.isAirBlock(pos.add(i, k, j))) {
                        if (generateChest(world, pos.add(i, k, j))) {
                            player.addStat(SSAchievements.creeperChest, 1);
                            return;
                        }
                    }

                }
            }
        }

    }

    protected boolean generateChest(World world, BlockPos pos) {
        boolean isGen = world.setBlockState(pos, SSBlocks.creeperChest.getDefaultState());

        if (isGen) {

            TileEntitySSChest tileEntityChest = (TileEntitySSChest) world.getTileEntity(pos);
            if (tileEntityChest != null) {

                //tileEntityChest.setInventorySlotContents(0, new ItemStack(SSBlocks.shippingBox));
                tileEntityChest.setInventorySlotContents(1, new ItemStack(SSBlocks.shopMonitor, 2));
                tileEntityChest.setInventorySlotContents(2, new ItemStack(SSItems.creeperMemory));
                tileEntityChest.setInventorySlotContents(3, new ItemStack(SSItems.bluestone, 32));
                tileEntityChest.setInventorySlotContents(4, new ItemStack(Items.GUNPOWDER, 16));
                tileEntityChest.setInventorySlotContents(5, new ItemStack(Items.DIAMOND, 4));

                return true;

            }
        }
        return false;
    }

}
