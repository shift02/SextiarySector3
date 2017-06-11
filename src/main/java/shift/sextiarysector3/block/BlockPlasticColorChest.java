package shift.sextiarysector3.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.tileentity.TileEntitySSChest;

public class BlockPlasticColorChest extends BlockSSChest {

    Class<? extends TileEntitySSChest> tile;

    public BlockPlasticColorChest(Class<? extends TileEntitySSChest> tile, BlockSSChest.Type type) {
        super(type);

        this.tile = tile;

        this.setCreativeTab(SextiarySectorAPI.TabSSIndustry);

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        try {
            return tile.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}
