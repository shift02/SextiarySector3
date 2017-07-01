package shift.sextiarysector3.block;

import net.minecraft.world.gen.feature.WorldGenerator;
import shift.sextiarysector3.world.WorldGenPlumTree;

public class BlockPlumSapling extends BlockSSSapling {

    @Override
    public WorldGenerator getTreeGen() {
        return new WorldGenPlumTree(true);
    }

}
