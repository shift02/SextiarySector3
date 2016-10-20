package shift.sextiarysector3;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.sextiarysector3.event.ClientEventHandler;
import shift.sextiarysector3.event.CommonEventHandler;
import shift.sextiarysector3.event.WorldEventHandler;
import shift.sextiarysector3.module.ModuleWarp;

public class SSEvents {

	public static void initEvent(FMLPreInitializationEvent event) {

		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());

		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());

		//MinecraftForge.EVENT_BUS.register(ModuleTrain.getInstance());
		MinecraftForge.EVENT_BUS.register(ModuleWarp.getInstance());

		if (event.getSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
		}

	}

}
