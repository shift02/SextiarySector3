package shift.sextiarysector3;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import shift.sextiarysector3.world.village.ComponentEnderStone;
import shift.sextiarysector3.world.village.VillageCreationHandlerEnderStone;

public class SSWorlds {

    public static void initVillages() {

        VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreationHandlerEnderStone());
        MapGenStructureIO.registerStructureComponent(ComponentEnderStone.class, "VISSES");
        //MapGenStructureIO.func_143031_a(ComponentEnderStone.class, "VISSES");

    }

}
