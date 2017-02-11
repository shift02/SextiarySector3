package shift.sextiarysector3.module;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.CanUpdate;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;
import shift.sextiarysector3.block.BlockEnderStoneMonument;
import shift.sextiarysector3.util.UtilCompat;

public class ModuleWarp implements IModule {

    private static ModuleWarp instance;

    private ModuleWarp() {
    }

    public static ModuleWarp getInstance() {
        if (instance == null) {
            instance = new ModuleWarp();
        }
        return instance;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void load(FMLInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @SubscribeEvent
    public void onIntelact(PlayerInteractEvent.EntityInteract event) {

        //ItemStack stack = event.getItemStack();
        //if (stack == null) return;
        if (!(event.getTarget() instanceof EntityItemFrame)) return;

        EntityItemFrame eF = (EntityItemFrame) event.getTarget();

        if (UtilCompat.isNullFromItemStack(eF.getDisplayedItem())) return;

        ItemStack item = eF.getDisplayedItem();

        if (item.getItem() != SSItems.enderCard) return;

        if (!item.hasTagCompound()) return;

        if (!item.getTagCompound().hasKey("power")) return;

        if (!eF.onValidSurface()) return;

        World worldObj = event.getEntity().worldObj;

        IBlockState state = worldObj.getBlockState(eF.getHangingPosition().offset(eF.getHorizontalFacing().getOpposite()));

        if (state.getBlock() == null) return;

        if (state.getBlock() != SSBlocks.enderStone) return;

        EnumFacing f1 = (EnumFacing) state.getValue(BlockEnderStoneMonument.FACING);
        if (!f1.equals(eF.getHorizontalFacing())) return;

        event.setCanceled(true);

        EntityPlayer player = event.getEntityPlayer();

        int x = item.getTagCompound().getInteger("x");
        int y = item.getTagCompound().getInteger("y");
        int z = item.getTagCompound().getInteger("z");
        EnumFacing f = EnumFacing.getFront(item.getTagCompound().getInteger("facing"));

        if (!worldObj.isRemote) {

            if (player instanceof EntityPlayerMP) {
                EntityPlayerMP entityplayermp = (EntityPlayerMP) player;

                if (entityplayermp.connection.getNetworkManager().isChannelOpen() && entityplayermp.worldObj == worldObj
                        && !entityplayermp.isPlayerSleeping()) {

                    if (player.isRiding()) {
                        player.dismountRidingEntity();
                    }

                    player.rotationYaw = f.getHorizontalAngle();
                    player.setPositionAndUpdate(x + 0.5, y + 0.5, z + 0.5);

                    player.fallDistance = 0.0F;
                    //player.attackEntityFrom(DamageSource.fall, event.getAttackDamage());

                }
            } else if (player != null) {
                player.setPositionAndUpdate(x + 0.5, y + 0.5, z + 0.5);
                player.fallDistance = 0.0F;
            }

        }

        //if (stack.getItem() != Items.STICK) return;

        //event.getTarget().getEntityData().setInteger("stop", event.getTarget().getEntityData().getInteger("stop") + 1);

    }

    @SubscribeEvent
    public void onCanUpdate(CanUpdate event) {

        if (event.getEntity().getEntityData().getInteger("stop") % 2 == 1) {
            event.setCanUpdate(false);
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void loadClient(FMLInitializationEvent event) {

    }
}
