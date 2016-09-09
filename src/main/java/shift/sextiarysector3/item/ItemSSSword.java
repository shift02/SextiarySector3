package shift.sextiarysector3.item;

import net.minecraft.item.ItemSword;
import shift.sextiarysector3.api.SextiarySectorAPI;

public class ItemSSSword extends ItemSword {

	public ItemSSSword(ToolMaterial material) {
		super(material);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

}
