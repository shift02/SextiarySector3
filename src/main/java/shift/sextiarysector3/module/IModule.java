package shift.sextiarysector3.module;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModule {

    public void preInit(FMLPreInitializationEvent event);

    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event);

    public void load(FMLInitializationEvent event);

    @SideOnly(Side.CLIENT)
    public void loadClient(FMLInitializationEvent event);

    public void postInit(FMLPostInitializationEvent event);

}
