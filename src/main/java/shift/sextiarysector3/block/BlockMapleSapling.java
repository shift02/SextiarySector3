package shift.sextiarysector3.block;

import net.minecraft.world.gen.feature.WorldGenerator;
import shift.sextiarysector3.world.WorldGenMapleTree;

public class BlockMapleSapling extends BlockSSSapling {

    public WorldGenerator getTreeGen() {
        return new WorldGenMapleTree(true, false);
    }

}
