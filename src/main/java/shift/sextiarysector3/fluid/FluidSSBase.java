package shift.sextiarysector3.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import shift.sextiarysector3.SextiarySector3;

public class FluidSSBase extends Fluid {

	public FluidSSBase(String fluidName, String still, String flowing) {
		super(fluidName, new ResourceLocation(SextiarySector3.MODID, "fuilds/" + still), new ResourceLocation(SextiarySector3.MODID, "fuilds/" + flowing));
	}

}
