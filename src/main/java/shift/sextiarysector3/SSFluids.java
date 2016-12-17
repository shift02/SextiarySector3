package shift.sextiarysector3;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import shift.sextiarysector3.fluid.FluidSSBase;

public class SSFluids {

    public static Fluid sap;
    public static Fluid rubber;

    public static void initFluid() {

        sap = new FluidSSBase("sap", "sap", "sap");
        FluidRegistry.registerFluid(sap);

        rubber = new FluidSSBase("rubber", "rubber", "rubber");
        FluidRegistry.registerFluid(rubber);

    }

}
