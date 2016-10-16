package xavier0014.lyokoenergistics.modelRenderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import xavier0014.lyokoenergistics.model.ModelLE;
import xavier0014.lyokoenergistics.model.ModelTowerScreen;
import xavier0014.lyokoenergistics.reference.Reference;
import xavier0014.lyokoenergistics.tileentity.TileBlockModelTest;

public class RenderTowerScreen extends TileEntitySpecialRenderer{
	
	
	public static ModelLE model = new ModelTowerScreen();
	public static ResourceLocation texture = new ResourceLocation(Reference.MOD_id+":models/blocks/towerScreen.png");
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x,double y, double z, float partialRenderTick) {
		this.renderTileTowerScrennAt((TileBlockModelTest)entity, x, y, z, partialRenderTick);
	}
	
	private void renderTileTowerScrennAt(TileBlockModelTest entity, double x, double y, double z, float partialRenderTick) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		GL11.glRotated(180D, 0.0D, 0.0D, 1.0D);
		GL11.glRotated(90D * entity.getDirection()+180, 0.0D, 1.0D, 0.0D);
		this.bindTexture(texture);
		model.render();
		GL11.glPopMatrix();
	}
	
}
