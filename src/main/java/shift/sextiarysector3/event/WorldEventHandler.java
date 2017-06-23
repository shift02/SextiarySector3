package shift.sextiarysector3.event;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSConfig;
import shift.sextiarysector3.world.WorldGenEnderStone;
import shift.sextiarysector3.world.WorldGenMapleTree;
import shift.sextiarysector3.world.WorldGenRubberTree;

public class WorldEventHandler {

    public Random randomGenerato;

    private WorldGenMinable copperOreGen;
    private WorldGenMinable silverOreGen;

    private WorldGenMinable orichalcumOreGen;

    private WorldGenMinable coalLargeGen;
    private WorldGenMinable ironLargeGen;
    private WorldGenMinable goldLargeGen;

    private WorldGenMinable copperLargeGen;
    private WorldGenMinable silverLargeGen;

    private int chunk_X;
    private int chunk_Z;
    private World currentWorld;
    private Biome biome;

    @SubscribeEvent
    public void onOreGenEvent(OreGenEvent.Pre event) {

        this.randomGenerato = event.getRand();
        this.chunk_X = event.getPos().getX();
        this.chunk_Z = event.getPos().getZ();
        this.currentWorld = event.getWorld();
        this.biome = event.getWorld().getBiomeForCoordsBody(event.getPos());

        copperOreGen = new WorldGenMinable(SSBlocks.copperOre.getDefaultState(), 10);
        silverOreGen = new WorldGenMinable(SSBlocks.silverOre.getDefaultState(), 8);

        orichalcumOreGen = new WorldGenMinable(SSBlocks.orichalcumOre.getDefaultState(), 7);

        coalLargeGen = new WorldGenMinable(SSBlocks.coalLargeOre.getDefaultState(), 16);
        ironLargeGen = new WorldGenMinable(SSBlocks.ironLargeOre.getDefaultState(), 8);
        goldLargeGen = new WorldGenMinable(SSBlocks.goldLargeOre.getDefaultState(), 8);

        copperLargeGen = new WorldGenMinable(SSBlocks.copperLargeOre.getDefaultState(), 8);
        silverLargeGen = new WorldGenMinable(SSBlocks.silverLargeOre.getDefaultState(), 8);

        //生成
        if (SSConfig.generateCopperOre) this.genStandardOre1(20, this.copperOreGen, 0, 64);
        if (SSConfig.generateSilverOre) this.genStandardOre1(2, this.silverOreGen, 0, 32);

        if (BiomeDictionary.isBiomeOfType(biome, Type.HOT)) {

            if (SSConfig.generateOrichalcumOre) this.genStandardOre1(1, orichalcumOreGen, 0, 18);

        }

        if (SSConfig.generateCoalLargeOre) this.genStandardOre1(10, this.coalLargeGen, 0, 128);
        if (SSConfig.generateIronLargeOre) this.genStandardOre1(10, this.ironLargeGen, 0, 64);
        if (SSConfig.generateGoldLargeOre) this.genStandardOre1(1, this.goldLargeGen, 0, 32);

        if (SSConfig.generateCopperLargeOre) this.genStandardOre1(4, this.copperLargeGen, 0, 32);
        if (SSConfig.generateSilverLargeOre) this.genStandardOre1(1, this.silverLargeGen, 0, 32);

    }

    protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {

        for (int l = 0; l < par1; ++l) {
            int i1 = this.chunk_X + this.randomGenerato.nextInt(16);
            int j1 = this.randomGenerato.nextInt(par4 - par3) + par3;
            int k1 = this.chunk_Z + this.randomGenerato.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerato, new BlockPos(i1, j1, k1));
        }

    }

    @SubscribeEvent
    public void onDecorateBiomeEvent(DecorateBiomeEvent.Pre event) {

        this.onDecorateBiomeEventForTree(event);

        this.onDecorateBiomeEventForMapleTree(event);

        this.onDecorateBiomeEventForEnderStone(event);

    }

    public void onDecorateBiomeEventForTree(DecorateBiomeEvent.Pre event) {

        this.randomGenerato = event.getRand();
        World worldIn = event.getWorld();
        BlockPos chunkPos = event.getPos();
        Biome biome = worldIn.getBiomeForCoordsBody(chunkPos);

        float tem = biome.getFloatTemperature(chunkPos);
        float rain = biome.getRainfall();

        if (tem < 0.75 || rain < 0.65) return;

        int k1 = biome.theBiomeDecorator.treesPerChunk;//;this.treesPerChunk;

        if (randomGenerato.nextFloat() < biome.theBiomeDecorator.field_189870_A)//this.field_189870_A)
        {
            ++k1;
        }

        for (int j2 = 0; j2 < k1; ++j2) {
            int k6 = randomGenerato.nextInt(16) + 8;
            int l = randomGenerato.nextInt(16) + 8;
            WorldGenAbstractTree worldgenabstracttree = new WorldGenRubberTree(false, false);
            worldgenabstracttree.setDecorationDefaults();
            BlockPos blockpos = worldIn.getHeight(chunkPos.add(k6, 0, l));

            if (worldgenabstracttree.generate(worldIn, randomGenerato, blockpos)) {
                worldgenabstracttree.generateSaplings(worldIn, randomGenerato, blockpos);
            }
        }

    }

    public void onDecorateBiomeEventForMapleTree(DecorateBiomeEvent.Pre event) {

        this.randomGenerato = event.getRand();
        World worldIn = event.getWorld();
        BlockPos chunkPos = event.getPos();
        Biome biome = worldIn.getBiomeForCoordsBody(chunkPos);

        float tem = biome.getFloatTemperature(chunkPos);
        float rain = biome.getRainfall();

        if (tem > 0.6) return;

        int k1 = biome.theBiomeDecorator.treesPerChunk;//;this.treesPerChunk;

        if (randomGenerato.nextFloat() < biome.theBiomeDecorator.field_189870_A)//this.field_189870_A)
        {
            ++k1;
        }

        for (int j2 = 0; j2 < k1; ++j2) {
            int k6 = randomGenerato.nextInt(16) + 8;
            int l = randomGenerato.nextInt(16) + 8;
            WorldGenAbstractTree worldgenabstracttree = new WorldGenMapleTree(false, false);
            worldgenabstracttree.setDecorationDefaults();
            BlockPos blockpos = worldIn.getHeight(chunkPos.add(k6, 0, l));

            if (blockpos.getY() < 75) continue;

            if (worldgenabstracttree.generate(worldIn, randomGenerato, blockpos)) {
                worldgenabstracttree.generateSaplings(worldIn, randomGenerato, blockpos);
            }
        }

    }

    public void onDecorateBiomeEventForEnderStone(DecorateBiomeEvent.Pre event) {

        this.randomGenerato = event.getRand();
        World worldIn = event.getWorld();
        BlockPos chunkPos = event.getPos();
        Biome biome = worldIn.getBiomeForCoordsBody(chunkPos);

        int i = randomGenerato.nextInt(16) + 8;
        int j = randomGenerato.nextInt(16) + 8;
        int height = worldIn.getHeight(chunkPos.add(i, 0, j)).getY() * 2; // could == 0, which crashes nextInt
        if (height < 1) height = 1;
        int k = randomGenerato.nextInt(height);

        if (this.randomGenerato.nextInt(10) == 0) {

            WorldGenEnderStone we = (new WorldGenEnderStone());
            we.generate(worldIn, randomGenerato, chunkPos.add(i, k, j));

        }

    }
}
