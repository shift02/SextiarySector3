package shift.sextiarysector3;

import com.google.common.base.Predicate;

import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SSPotions {

    //ポーション効果
    public static PotionType haste;
    public static PotionType longHaste;
    public static PotionType strongHaste;

    public static Predicate<ItemStack> predicateOrichalcum = new PotionHelper.ItemPredicateInstance(SSItems.orichalcumGem);

    public static Predicate<ItemStack> predicate = new PotionHelper.ItemPredicateInstance(SSItems.silverNugget);

    public static void initPotion() {

        haste = new PotionType(new PotionEffect[] { new PotionEffect(MobEffects.HASTE, 3600) });
        longHaste = new PotionType("haste", new PotionEffect[] { new PotionEffect(MobEffects.HASTE, 9600) });
        strongHaste = new PotionType("haste", new PotionEffect[] { new PotionEffect(MobEffects.HASTE, 1800, 1) });

        GameRegistry.register(haste, new ResourceLocation("haste"));
        GameRegistry.register(longHaste, new ResourceLocation("long_haste"));
        GameRegistry.register(strongHaste, new ResourceLocation("strong_haste"));

        PotionHelper.registerPotionTypeConversion(PotionTypes.AWKWARD, predicateOrichalcum, haste);

    }
}
