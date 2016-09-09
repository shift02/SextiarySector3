package shift.sextiarysector3.item;

import net.minecraft.item.ItemSpade;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class ItemSSSpade extends ItemSpade {

	public ItemSSSpade(ToolMaterial material) {
		super(material);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

}
