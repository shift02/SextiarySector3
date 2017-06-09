package shift.sextiarysector3;

import java.io.File;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.sextiarysector3.api.SextiarySectorAPI;
import shift.sextiarysector3.api.energy.CapabilityGearForce;
import shift.sextiarysector3.api.energy.CapabilityGearForceHandler;
import shift.sextiarysector3.api.industry.CapabilityShaftHandler;
import shift.sextiarysector3.module.IModule;
import shift.sextiarysector3.module.ModuleColor;
import shift.sextiarysector3.module.ModuleSap;
import shift.sextiarysector3.module.ModuleToolMaterial;
import shift.sextiarysector3.proxy.CommonProxy;
import shift.sextiarysector3.tileentity.CapabilityGFShaftHandler;
import shift.sextiarysector3.util.UtilRegistry;

@Mod(modid = SextiarySector3.MODID, version = SextiarySector3.VERSION, updateJSON = SextiarySector3.UPDATE_JSON, guiFactory = SextiarySector3.GUI_FACTORY)
public class SextiarySector3 {

    public static final String MODID = "sextiarysector3";
    public static final String VERSION = "1.3.3";

    public static final String UPDATE_JSON = "https://shift02.github.io/SextiarySector3/Update.json";

    public static final String GUI_FACTORY = "shift.sextiarysector3.config.SSConfigGuiFactory";

    @Mod.Instance(MODID)
    public static SextiarySector3 instance;

    @SidedProxy(modId = MODID, clientSide = "shift.sextiarysector3.proxy.ClientProxy", serverSide = "shift.sextiarysector3.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final Logger log = LogManager.getLogger(SextiarySectorAPI.MODID);

    public static boolean isDebug = false;

    public static final ArrayList<IModule> modules = new ArrayList<IModule>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        //Json生成用
        isDebug = event.getSourceFile().isDirectory() && (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        UtilRegistry.itemModel = new File(event.getSourceFile().getParentFile(), "src/main/resources/assets/sextiarysector3/models/item");
        UtilRegistry.blockState = new File(event.getSourceFile().getParentFile(), "src/main/resources/assets/sextiarysector3/blockstates");

        SSConfig.syncConfig();

        SSCreativeTabs.initCreativeTabs();

        //Module
        modules.add(ModuleSap.getInstance());
        modules.add(ModuleColor.getInstance());
        modules.add(ModuleToolMaterial.getInstance());
        //modules.add(ModuleTrain.getInstance());

        for (IModule m : modules) {
            m.preInit(event);
        }

        proxy.preInitModuleClient(event);

        SSItems.initItem();
        SSBlocks.initBlock();
        SSFluids.initFluid();
        SSEntitys.initEntity();

        SSEvents.initEvent(event);

        SSRecipes.initRecipes();

        SSOreDictionary.init();

        SSAchievements.initAchievements();

        proxy.initTileEntitySpecialRenderer();

        CapabilityShaftHandler.register();
        CapabilityGFShaftHandler.register();
        CapabilityGearForceHandler.register();
        CapabilityGearForce.register();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        for (IModule m : modules) {
            m.load(event);
        }
        proxy.loadModuleClient(event);

        SSWorlds.initVillages();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        for (IModule m : modules) {
            m.postInit(event);
        }

    }

}
