package shift.sextiarysector3;

import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector3.event.CommonEventHandler;
import shift.sextiarysector3.event.WorldEventHandler;

public class SSEvents {

	public static void initEvent() {

		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());

		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());

	}

}
