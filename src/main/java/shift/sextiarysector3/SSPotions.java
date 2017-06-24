package shift.sextiarysector3;

import com.google.common.base.Predicate;

import net.minecraft.init.Items;
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

    public static PotionType glowing;
    public static PotionType longGlowing;
    public static PotionType strongGlowing;

    public static Predicate<ItemStack> predicate2 = new PotionHelper.ItemPredicateInstance(Items.REDSTONE);
    public static Predicate<ItemStack> predicate5 = new PotionHelper.ItemPredicateInstance(Items.GLOWSTONE_DUST);

    public static Predicate<ItemStack> predicateOrichalcum = new PotionHelper.ItemPredicateInstance(SSItems.orichalcumGem);

    public static Predicate<ItemStack> predicateShiningFlower = new PotionHelper.ItemPredicateInstance(SSItems.shiningFlower);

    public static Predicate<ItemStack> predicate = new PotionHelper.ItemPredicateInstance(SSItems.silverNugget);

    public static void initPotion() {

        haste = new PotionType(new PotionEffect[] { new PotionEffect(MobEffects.HASTE, 3600) });
        longHaste = new PotionType("haste", new PotionEffect[] { new PotionEffect(MobEffects.HASTE, 9600) });
        strongHaste = new PotionType("haste", new PotionEffect[] { new PotionEffect(MobEffects.HASTE, 1800, 1) });

        GameRegistry.register(haste, new ResourceLocation("haste"));
        GameRegistry.register(longHaste, new ResourceLocation("long_haste"));
        GameRegistry.register(strongHaste, new ResourceLocation("strong_haste"));

        glowing = new PotionType(new PotionEffect[] { new PotionEffect(MobEffects.GLOWING, 3600) });
        longGlowing = new PotionType("glowing", new PotionEffect[] { new PotionEffect(MobEffects.GLOWING, 9600) });
        //strongGlowing = new PotionType("glowing", new PotionEffect[] { new PotionEffect(MobEffects.GLOWING, 1800, 1) });

        GameRegistry.register(glowing, new ResourceLocation("glowing"));
        GameRegistry.register(longGlowing, new ResourceLocation("long_glowing"));
        //GameRegistry.register(strongGlowing, new ResourceLocation("strong_glowing"));

        PotionHelper.registerPotionTypeConversion(PotionTypes.AWKWARD, predicateOrichalcum, haste);
        PotionHelper.registerPotionTypeConversion(haste, predicate2, longHaste);
        PotionHelper.registerPotionTypeConversion(haste, predicate5, strongHaste);

        PotionHelper.registerPotionTypeConversion(PotionTypes.AWKWARD, predicateShiningFlower, glowing);
        PotionHelper.registerPotionTypeConversion(glowing, predicate2, longGlowing);

    }
}
