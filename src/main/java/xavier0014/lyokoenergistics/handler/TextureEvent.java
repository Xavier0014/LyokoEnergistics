package xavier0014.lyokoenergistics.handler;

import xavier0014.lyokoenergistics.init.ModBlock;
import xavier0014.lyokoenergistics.init.ModFluid;
import net.minecraftforge.client.event.TextureStitchEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TextureEvent {
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPostTextureStitch(TextureStitchEvent.Post event)
	{
		if(event.map.getTextureType() == 0)
		{
			ModFluid.DigitalSea.setIcons(ModBlock.digitalSea.getBlockTextureFromSide(1), ModBlock.digitalSea.getBlockTextureFromSide(2));
		}
	}
}
