package shift.sextiarysector3.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class BlockSSBase extends Block {

    protected int dropSize = 1;

    public BlockSSBase(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(SextiarySectorAPI.TabSSCore);
    }

    public BlockSSBase(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
        this.setCreativeTab(SextiarySectorAPI.TabSSCore);
    }

    @Override
    public int quantityDropped(Random random) {
        return this.getDropSize();
    }

    public int getDropSize() {
        return dropSize;
    }

    public BlockSSBase setDropSize(int dropSize) {
        this.dropSize = dropSize;
        return this;
    }

}
