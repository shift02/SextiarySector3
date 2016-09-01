package shift.sextiarysector3;

import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector3.event.CommonEventHandler;

public class SSEvents {

	public static void initEvent() {

		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());

	}

}
