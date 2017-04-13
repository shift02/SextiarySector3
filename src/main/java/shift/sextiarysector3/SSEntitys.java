package shift.sextiarysector3;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import shift.sextiarysector3.entity.EntityConveyorItem;

public class SSEntitys {

    public static void initEntity() {

        EntityRegistry.registerModEntity(EntityConveyorItem.class, "conveyor_item", 0, SextiarySector3.instance, 250, 1, true);

    }
}
