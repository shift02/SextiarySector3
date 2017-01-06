package shift.sextiarysector3;

import net.minecraftforge.oredict.OreDictionary;

public class SSOreDictionary {

    public static void init() {

        //Ingot
        OreDictionary.registerOre("ingotCopper", SSItems.copperIngot);
        OreDictionary.registerOre("ingotSilver", SSItems.silverIngot);
        //Gem
        OreDictionary.registerOre("gemOrichalcum", SSItems.orichalcumGem);

        //Dust
        OreDictionary.registerOre("dustIron", SSItems.ironDust);
        OreDictionary.registerOre("dustGold", SSItems.goldDust);
        OreDictionary.registerOre("dustCopper", SSItems.copperDust);
        OreDictionary.registerOre("dustSilver", SSItems.silverDust);

        OreDictionary.registerOre("dustBluestone", SSItems.bluestone);

        //Ore_Block
        OreDictionary.registerOre("blockCopper", SSBlocks.copperBlock);
        OreDictionary.registerOre("blockSilver", SSBlocks.silverBlock);
        OreDictionary.registerOre("blockOrichalcum", SSBlocks.orichalcumBlock);

        OreDictionary.registerOre("plastic", SSItems.plastic);
        OreDictionary.registerOre("rubber", SSItems.rubber);

    }

}
