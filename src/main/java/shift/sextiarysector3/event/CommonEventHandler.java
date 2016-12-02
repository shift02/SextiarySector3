package shift.sextiarysector3.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shift.sextiarysector3.SSConfig;

public class CommonEventHandler {

    //葉っぱ
    @SubscribeEvent
    public void DecayEvent(BlockEvent.HarvestDropsEvent event) {

        if (event.isSilkTouching()) return;

        if (!(event.getState().getBlock() instanceof BlockLeaves)) return;

        if (event.getHarvester() != null && event.getHarvester().getActiveItemStack() != null
                && event.getHarvester().getActiveItemStack().getItem() instanceof ItemShears)
            return;

        if (event.getWorld().rand.nextBoolean()) event.getDrops().add(new ItemStack(Items.STICK, event.getWorld().rand.nextInt(1) + 1));
        //if (event.getWorld().rand.nextInt(3) == 0) event.getDrops().add(new ItemStack(SSItems.leaf, event.world.rand.nextInt(2) + 1));

        if (!SSConfig.fastDecayLeaves) return;

        byte b0 = 2;
        int i1 = b0 + 1;

        //if (event.getWorld().checkChunksExist(
        //		event.getPos().getX() - i1, event.getPos().getY() - i1, event.getPos().getZ() - i1, event.getPos().getX() + i1, event.getPos().getY() + i1,
        //		event.getPos().getZ() + i1)) {
        for (int j1 = -b0; j1 <= b0; ++j1) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {

                    BlockPos pos = event.getPos().add(j1, k1, l1);
                    IBlockState state = event.getWorld().getBlockState(pos);
                    Block block = state.getBlock();

                    if (block.isLeaves(state, event.getWorld(), pos)) {
                        //block.updateTick(event.world, event.x + j1, event.y + k1, event.z + l1, event.world.rand);
                        event.getWorld().scheduleBlockUpdate(pos, block, 20 + event.getWorld().rand.nextInt(8), 1);
                    }
                }
            }
        }
        //}

        //for (int i = 0; i < 2; i++) {
        //	this.updateLeavesTick(event);
        //}

    }

}
