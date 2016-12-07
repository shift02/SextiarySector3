package shift.sextiarysector3.achievement;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSItems;

public class AchievementSSOre extends AchievementBase {

    public AchievementSSOre(String p_i45302_1_, int p_i45302_3_, int p_i45302_4_, ItemStack p_i45302_5_, Achievement p_i45302_6_, ArrayList<Achievement> a) {
        super(p_i45302_1_, p_i45302_3_, p_i45302_4_, p_i45302_5_, p_i45302_6_, a);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onItemPickupEvent(ItemPickupEvent event) {

        if (event.player == null) return;

        if (isSSOre(event.pickedUp.getEntityItem())) {
            event.player.addStat(this, 1);
        }

    }

    private boolean isSSOre(ItemStack item) {

        if (checkItem(item, new ItemStack(SSBlocks.copperOre))) return true;
        if (checkItem(item, new ItemStack(SSBlocks.silverOre))) return true;
        if (checkItem(item, new ItemStack(SSItems.orichalcumGem))) return true;
        if (checkItem(item, new ItemStack(SSBlocks.copperLargeOre))) return true;
        if (checkItem(item, new ItemStack(SSBlocks.silverLargeOre))) return true;

        return false;

    }

    private boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_) {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }
}