package shift.sextiarysector3.event;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {

	//ツールチップ
	@SubscribeEvent
	public void RenderTooltipEvent(ItemTooltipEvent event) {

		//event.setScreenWidth(event.getScreenWidth() + 100);
		//event.setScreenHeight(event.getScreenHeight() + 100);

		/*event.getLines().add("");
		event.getLines().add("");
		event.getLines().add("");
		event.getLines().add("");
		event.getLines().add("");
		event.getLines().add("");
		event.getLines().add("");*/

		return;

		/*
		event.getToolTip().add("");
		event.getToolTip().add("");
		event.getToolTip().add("");
		event.getToolTip().add("");*/

	}

	public ItemStack bb;

	//ツールチップ
	@SubscribeEvent
	public void RenderTooltipEvent(RenderTooltipEvent.PostText event) {

		return;

		/*
		int x = event.getX();
		int y = event.getY();
		ItemStack item = event.getStack();
		
		Minecraft minecraft = Minecraft.getMinecraft();
		RenderItem renderItem = minecraft.getRenderItem();
		
		//ツールチップ全般で呼ばれるのでアイテムがnullならスキップ
		if (item == null) return;
		
		if (bb == null) {
			bb = new ItemStack(Items.DIAMOND);
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.disableLighting();
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableColorMaterial();
		GlStateManager.enableLighting();
		renderItem.zLevel = 100.0F;
		renderItem.renderItemAndEffectIntoGUI(item, x, y + 30);
		renderItem.renderItemAndEffectIntoGUI(bb, x + 16, y + 30);
		renderItem.zLevel = 0.0F;
		
		GlStateManager.disableLighting();
		GlStateManager.depthMask(true);
		GlStateManager.enableDepth();*/

	}

}
