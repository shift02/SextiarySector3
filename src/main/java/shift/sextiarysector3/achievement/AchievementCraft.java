package shift.sextiarysector3.achievement;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class AchievementCraft extends AchievementBase {

    public AchievementCraft(String p_i45302_1_, int p_i45302_3_, int p_i45302_4_, ItemStack p_i45302_5_, Achievement p_i45302_6_, ArrayList<Achievement> a) {
        super(p_i45302_1_, p_i45302_3_, p_i45302_4_, p_i45302_5_, p_i45302_6_, a);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onItemCraftedEvent(ItemCraftedEvent event) {
        if (checkItem(event.crafting, this.theItemStack) && event.player != null) {
            event.player.addStat(this, 1);
        }
    }

    private boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_) {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

}
