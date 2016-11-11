package shift.sextiarysector3.event;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shift.sextiarysector3.world.WorldGenEnderStone;
import shift.sextiarysector3.world.WorldGenRubberTree;

public class WorldEventHandler {

    public Random randomGenerato;

    @SubscribeEvent
    public void onOreGenEvent(OreGenEvent.Pre event) {

        this.randomGenerato = event.getRand();
    }

    @SubscribeEvent
    public void onDecorateBiomeEvent(DecorateBiomeEvent.Pre event) {

        this.onDecorateBiomeEventForTree(event);

        this.onDecorateBiomeEventForEnderStone(event);

    }

    public void onDecorateBiomeEventForTree(DecorateBiomeEvent.Pre event) {

        this.randomGenerato = event.getRand();
        World worldIn = event.getWorld();
        BlockPos chunkPos = event.getPos();
        Biome biome = worldIn.getBiomeGenForCoords(chunkPos);

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

    public void onDecorateBiomeEventForEnderStone(DecorateBiomeEvent.Pre event) {

        this.randomGenerato = event.getRand();
        World worldIn = event.getWorld();
        BlockPos chunkPos = event.getPos();
        Biome biome = worldIn.getBiomeGenForCoords(chunkPos);

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
