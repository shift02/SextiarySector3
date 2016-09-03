package shift.sextiarysector3;

import net.minecraftforge.oredict.OreDictionary;

public class SSOreDictionary {

	public static void init() {

		//Ingot
		OreDictionary.registerOre("ingotCopper", SSItems.copperIngot);
		OreDictionary.registerOre("ingotSilver", SSItems.silverIngot);
		//Gem
		OreDictionary.registerOre("gemOrichalcum", SSItems.orichalcumGem);

	}

}
