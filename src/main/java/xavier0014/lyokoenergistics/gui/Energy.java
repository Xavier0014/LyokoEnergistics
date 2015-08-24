package xavier0014.lyokoenergistics.gui;

import xavier0014.lyokoenergistics.reference.Reference;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class Energy extends Gui{
	
	private double energieStore;
	public static final ResourceLocation texture = new ResourceLocation(Reference.MOD_id,"textures/gui/energyGui.png");
	public Energy(int energieStore,int energieMax) {
		this.energieStore = (energieStore*61)/energieMax;
	}
	
	public void renderEnergie(int x,int y){
		if (energieStore >= 0 && energieStore <= 20) {
			this.drawTexturedModalRect(x, (int) (y +(61 -energieStore*3)-1) , 1, 1, 3, (int) energieStore*3);
		}else {
			this.drawTexturedModalRect(x, (int) (y +(61 -61)) , 1, 1, 3, (int) 61);
		}
		if (energieStore >= 15 && energieStore <= 35) {
			this.drawTexturedModalRect(x+4, (int) (y +(61 -(energieStore-15)*3)-1) , 1, 1, 3, (int) (energieStore-15)*3);
		}else if (!(energieStore < 15)) {
			this.drawTexturedModalRect(x+4, (int) (y +(61 -61)) , 1, 1, 3, (int) 61);
		}
		if (energieStore >= 30 && energieStore <= 50) {
			this.drawTexturedModalRect(x+8, (int) (y +(61 -(energieStore-30)*3)-1) , 1, 1, 3, (int) (energieStore-30)*3);
		}else if (!(energieStore < 30)) {
			this.drawTexturedModalRect(x+8, (int) (y +(61 -61)) , 1, 1, 3, (int) 61);
		}
		if (energieStore >= 40 && energieStore <= 60) {
			this.drawTexturedModalRect(x+12, (int) (y +(61 -(energieStore-40)*3)-1) , 1, 1, 3, (int) (energieStore-40)*3);
		}else if (!(energieStore < 40)) {
			this.drawTexturedModalRect(x+12, (int) (y +(61 -61)) , 1, 1, 3, (int) 61);
		}
	}
}
