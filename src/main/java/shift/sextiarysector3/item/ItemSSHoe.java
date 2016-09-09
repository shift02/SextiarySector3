package shift.sextiarysector3.item;

import net.minecraft.item.ItemHoe;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class ItemSSHoe extends ItemHoe {

	public ItemSSHoe(ToolMaterial material) {
		super(material);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

}
