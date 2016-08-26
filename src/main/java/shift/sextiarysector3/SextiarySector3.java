package shift.sextiarysector3;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.util.UtilRegistry;

@Mod(modid = SextiarySector3.MODID, version = SextiarySector3.VERSION)
public class SextiarySector3 {
	public static final String MODID = "sextiarysector3";
	public static final String VERSION = "1.0";

	public static final Logger log = LogManager.getLogger(SextiarySectorAPI.MODID);

	public static boolean isDebug = false;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		//Json生成用
		isDebug = event.getSourceFile().isDirectory();
		UtilRegistry.itemModel = new File(event.getSourceFile().getParentFile(), "src/main/resources/assets/sextiarysector3/models/item");

		SSCreativeTabs.initCreativeTabs();

		SSItems.initItem();
		SSBlocks.initBlock();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName());
	}
}
