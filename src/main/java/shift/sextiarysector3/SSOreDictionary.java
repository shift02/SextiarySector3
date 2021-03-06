package shift.sextiarysector3;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SSOreDictionary {

    public static void init() {

        //Ingot
        OreDictionary.registerOre("ingotCopper", SSItems.copperIngot);
        OreDictionary.registerOre("ingotSilver", SSItems.silverIngot);
        //Gem
        OreDictionary.registerOre("gemOrichalcum", SSItems.orichalcumGem);

        //Ore
        OreDictionary.registerOre("oreCopper", SSBlocks.copperOre);
        OreDictionary.registerOre("oreSilver", SSBlocks.silverOre);
        OreDictionary.registerOre("oreOrichalcum", SSBlocks.orichalcumOre);

        //Nugget
        OreDictionary.registerOre("nuggetIron", SSItems.ironNugget);
        OreDictionary.registerOre("nuggetCopper", SSItems.copperNugget);
        OreDictionary.registerOre("nuggetSilver", SSItems.silverNugget);

        //Dust
        OreDictionary.registerOre("dustCoal", SSItems.coalDust);

        OreDictionary.registerOre("dustIron", SSItems.ironDust);
        OreDictionary.registerOre("dustGold", SSItems.goldDust);
        OreDictionary.registerOre("dustCopper", SSItems.copperDust);
        OreDictionary.registerOre("dustSilver", SSItems.silverDust);

        OreDictionary.registerOre("dustBluestone", SSItems.bluestone);

        //Ore_Block
        OreDictionary.registerOre("blockCopper", SSBlocks.copperBlock);
        OreDictionary.registerOre("blockSilver", SSBlocks.silverBlock);
        OreDictionary.registerOre("blockOrichalcum", SSBlocks.orichalcumBlock);

        //Other_Block
        OreDictionary.registerOre("blockPlastic", SSBlocks.plasticBlock);
        OreDictionary.registerOre("blockRubber", SSBlocks.rubberBlock);

        OreDictionary.registerOre("chestPlastic", SSBlocks.plasticChest);
        OreDictionary.registerOre("chestWood", SSBlocks.creeperChest);

        OreDictionary.registerOre("blockConveyor", SSBlocks.conveyor);

        //Log
        OreDictionary.registerOre("logWood", SSBlocks.rubberLog);
        OreDictionary.registerOre("logWood", SSBlocks.mapleLog);

        //treeSapling
        OreDictionary.registerOre("treeSapling", SSBlocks.rubberSapling);
        OreDictionary.registerOre("treeSapling", SSBlocks.mapleSapling);
        OreDictionary.registerOre("treeSapling", SSBlocks.plumSapling);
        OreDictionary.registerOre("treeSapling", SSBlocks.sakuraSapling);

        //treeLeaves
        OreDictionary.registerOre("treeLeaves", SSBlocks.rubberLeaves);
        OreDictionary.registerOre("treeLeaves", SSBlocks.mapleLeaves);
        OreDictionary.registerOre("treeLeaves", SSBlocks.plumLeaves);
        OreDictionary.registerOre("treeLeaves", SSBlocks.sakuraLeaves);

        OreDictionary.registerOre("plastic", SSItems.plastic);
        OreDictionary.registerOre("rubber", SSItems.rubber);

        //Gear
        OreDictionary.registerOre("gearWood", SSItems.woodGear);

        OreDictionary.registerOre("itemDepthMeter", SSItems.depthMeter);

        OreDictionary.registerOre("blockWool", new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE));

        //PlasticChest
        OreDictionary.registerOre("chestPlastic", SSBlocks.plasticChest);
        for (int i = 0; i < SSBlocks.plasticColorChest.length; i++) {
            OreDictionary.registerOre("chestPlastic", SSBlocks.plasticColorChest[i]);
        }

        //Other
        OreDictionary.registerOre("syrupMaple", SSItems.mapleBottle);

        OreDictionary.registerOre("itemSpanner", SSItems.ironSpanner);

    }

}
