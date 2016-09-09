package shift.sextiarysector3.item;

import net.minecraft.item.ItemPickaxe;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class ItemSSPickaxe extends ItemPickaxe {

	public ItemSSPickaxe(ToolMaterial material) {
		super(material);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

}
