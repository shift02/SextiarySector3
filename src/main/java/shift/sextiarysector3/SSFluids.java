package shift.sextiarysector3;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import shift.sextiarysector3.fluid.FluidSSBase;

public class SSFluids {

	public static Fluid sap;

	public static void initFluid() {

		sap = new FluidSSBase("sap", "water_still", "nwater_still");
		FluidRegistry.registerFluid(sap);

	}

}
