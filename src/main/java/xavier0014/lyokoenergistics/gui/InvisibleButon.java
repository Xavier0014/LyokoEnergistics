package xavier0014.lyokoenergistics.gui;

import org.lwjgl.opengl.GL11;

import xavier0014.lyokoenergistics.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class InvisibleButon extends GuiButton{
	ResourceLocation button = new ResourceLocation(Reference.MOD_id + "textures/gui/widgets.png");

	public InvisibleButon(int id, int posx, int posy, int x, int y, String name) {
		super(id, posx, posy, x, y, name);
	}
	
	@Override
	 public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_){
	        if (this.visible){
	            FontRenderer fontrenderer = p_146112_1_.fontRenderer;
	            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	            this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
	            int k = this.getHoverState(this.field_146123_n);
	            GL11.glEnable(GL11.GL_BLEND);
	            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	            this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
	            int l = 14737632;

	            if (packedFGColour != 0){
	                l = packedFGColour;
	            }
	            else if (!this.enabled) {
	                l = 10526880;
	            }
	            else if (this.field_146123_n)
	            {
	                l = 16777120;
	            }

	            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
	        }
	    }

}
