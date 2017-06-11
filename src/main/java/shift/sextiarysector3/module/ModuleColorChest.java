package shift.sextiarysector3.module;

import shift.sextiarysector3.block.BlockSSChest;
import shift.sextiarysector3.tileentity.TileEntitySSChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticBlackChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticBlueChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticBrownChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticCyanChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticGrayChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticGreenChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticLightBlueChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticLimeChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticMagentaChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticOrangeChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticPinkChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticPurpleChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticRedChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticSilverChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticWhiteChest;
import shift.sextiarysector3.tileentity.color.TileEntityPlasticYellowChest;

public class ModuleColorChest {

    public static Class<? extends TileEntitySSChest>[] plasticColorChestTile = new Class[] {
            TileEntityPlasticWhiteChest.class,
            TileEntityPlasticOrangeChest.class,
            TileEntityPlasticMagentaChest.class,
            TileEntityPlasticLightBlueChest.class,
            TileEntityPlasticYellowChest.class,
            TileEntityPlasticLimeChest.class,
            TileEntityPlasticPinkChest.class,
            TileEntityPlasticGrayChest.class,
            TileEntityPlasticSilverChest.class,
            TileEntityPlasticCyanChest.class,
            TileEntityPlasticPurpleChest.class,
            TileEntityPlasticBlueChest.class,
            TileEntityPlasticBrownChest.class,
            TileEntityPlasticGreenChest.class,
            TileEntityPlasticRedChest.class,
            TileEntityPlasticBlackChest.class
    };

    public static BlockSSChest.Type[] plasticColorChestType = new BlockSSChest.Type[] {
            BlockSSChest.Type.PLASTIC_WHITE,
            BlockSSChest.Type.PLASTIC_ORANGE,
            BlockSSChest.Type.PLASTIC_MAGENTA,
            BlockSSChest.Type.PLASTIC_LIGHT_BLUE,
            BlockSSChest.Type.PLASTIC_YELLOW,
            BlockSSChest.Type.PLASTIC_LIME,
            BlockSSChest.Type.PLASTIC_PINK,
            BlockSSChest.Type.PLASTIC_GRAY,
            BlockSSChest.Type.PLASTIC_SILVER,
            BlockSSChest.Type.PLASTIC_CYAN,
            BlockSSChest.Type.PLASTIC_PURPLE,
            BlockSSChest.Type.PLASTIC_BLUE,
            BlockSSChest.Type.PLASTIC_BROWN,
            BlockSSChest.Type.PLASTIC_GREEN,
            BlockSSChest.Type.PLASTIC_RED,
            BlockSSChest.Type.PLASTIC_BLACK
    };

}
