package shift.sextiarysector3;

import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector3.event.CommonEventHandler;
import shift.sextiarysector3.event.WorldEventHandler;
import shift.sextiarysector3.module.ModuleWarp;

public class SSEvents {

	public static void initEvent() {

		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());

		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());

		//MinecraftForge.EVENT_BUS.register(ModuleTrain.getInstance());
		MinecraftForge.EVENT_BUS.register(ModuleWarp.getInstance());

	}

}
