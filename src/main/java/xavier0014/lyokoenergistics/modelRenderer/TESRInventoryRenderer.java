package xavier0014.lyokoenergistics.modelRenderer;

import org.lwjgl.opengl.GL11;

import xavier0014.lyokoenergistics.init.ModBlock;
import xavier0014.lyokoenergistics.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class TESRInventoryRenderer implements ISimpleBlockRenderingHandler{
	
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,RenderBlocks renderer) {
		if (block == ModBlock.materializationscanner && metadata == 0) {
			GL11.glPushMatrix();
			GL11.glTranslated(0D,1.0D, 0D);
			GL11.glRotated(180D, 0.0D, 0.0D, 1.0D);
			GL11.glRotated(180D, 0.0D, 1.0D, 0.0D);
			Minecraft.getMinecraft().getTextureManager().bindTexture(RendererMaterializationScanner.texture);
			RendererMaterializationScanner.model.render();
			GL11.glPopMatrix();
		}
		if (block == ModBlock.modeltest && metadata == 0) {
			GL11.glPushMatrix();
			GL11.glTranslated(0D,1.0D, 0D);
			GL11.glRotated(180D, 0.0D, 0.0D, 1.0D);
			GL11.glRotated(180D, 0.0D, 1.0D, 0.0D);
			Minecraft.getMinecraft().getTextureManager().bindTexture(RenderTowerScreen.texture);
			RenderTowerScreen.model.render();
			GL11.glPopMatrix();
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.tesrRenderId;
	}

}
