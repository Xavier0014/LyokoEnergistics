package xavier0014.lyokoenergistics.modelRenderer;

import org.lwjgl.opengl.GL11;

import xavier0014.lyokoenergistics.reference.Reference;
import xavier0014.lyokoenergistics.tileentity.TileVirtualisationScanner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderVirtualisationScanner extends TileEntitySpecialRenderer{
	ResourceLocation texture;
	ResourceLocation objModelLocation;
	IModelCustom model;
	
	public RenderVirtualisationScanner(){
        texture = new ResourceLocation(Reference.MOD_id, "models/VirtualisationScanner.png");
        objModelLocation = new ResourceLocation(Reference.MOD_id, "models/virtualisaton scanner.obj");
        model = AdvancedModelLoader.loadModel(objModelLocation);
}

	@Override
	public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float timeSinceLastTick) {
		TileVirtualisationScanner te2 = (TileVirtualisationScanner) te;
		if (ifrender(te2)) {
			float rotation = te2.rotation + (timeSinceLastTick / 2F);
			float scale = te2.scale;
			bindTexture(texture);
			GL11.glPushMatrix();
			GL11.glTranslated(posX + 0.5, posY + 0.5, posZ + 0.5);
//			GL11.glScalef(scale, scale, scale);
			GL11.glPushMatrix();
//			GL11.glRotatef(rotation, 0F, 0F, 0F);
			model.renderAll();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
	}
	
	public boolean ifrender(TileVirtualisationScanner te) {
		World world = te.getWorldObj();
		if(world.getTileEntity(te.xCoord+1, te.yCoord, te.zCoord) instanceof TileVirtualisationScanner && world.getTileEntity(te.xCoord, te.yCoord, te.zCoord+1) instanceof TileVirtualisationScanner && world.getTileEntity(te.xCoord, te.yCoord, te.zCoord-1) instanceof TileVirtualisationScanner
				&& world.getTileEntity(te.xCoord-1, te.yCoord, te.zCoord) instanceof TileVirtualisationScanner && world.getTileEntity(te.xCoord, te.yCoord+5, te.zCoord) instanceof TileVirtualisationScanner)
			return true;
		return false;
	}
	
}
