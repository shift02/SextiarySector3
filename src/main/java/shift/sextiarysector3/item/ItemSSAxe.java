package shift.sextiarysector3.item;

import net.minecraft.item.ItemAxe;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class ItemSSAxe extends ItemAxe {

	public ItemSSAxe(ToolMaterial material, float damage, float speed) {
		super(material, damage, speed);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

}
