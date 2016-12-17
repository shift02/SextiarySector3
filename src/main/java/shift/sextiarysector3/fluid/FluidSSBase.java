package shift.sextiarysector3.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.SextiarySector3;

public class FluidSSBase extends Fluid {

    public FluidSSBase(String fluidName, String still, String flowing) {
        super(fluidName, new ResourceLocation(SextiarySector3.MODID, "fluids/" + still), new ResourceLocation(SextiarySector3.MODID, "fluids/" + flowing));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void preTextureStitchEvent(TextureStitchEvent.Pre event) {

        // System.out.println(event.getMap().getBasePath() + " : " + event.getMap().getMipmapLevels());

        event.getMap().registerSprite(getStill());
        event.getMap().registerSprite(getFlowing());

        //if (event.getMap().getBasePath()) == 0) {
        //
        //}

    }

    public String getUnlocalizedName() {
        return "fluid." + this.unlocalizedName + ".name";
    }

}
