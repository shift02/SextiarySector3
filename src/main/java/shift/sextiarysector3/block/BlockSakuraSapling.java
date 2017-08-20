package shift.sextiarysector3.block;

import net.minecraft.world.gen.feature.WorldGenerator;
import shift.sextiarysector3.world.WorldGenSakuraTree;

public class BlockSakuraSapling extends BlockSSSapling {

    @Override
    public WorldGenerator getTreeGen() {
        return new WorldGenSakuraTree(true);
    }

}
