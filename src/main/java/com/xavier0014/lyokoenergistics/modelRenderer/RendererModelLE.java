package com.xavier0014.lyokoenergistics.modelRenderer;

import org.lwjgl.opengl.GL11;

import com.xavier0014.lyokoenergistics.model.ModelLE;
import com.xavier0014.lyokoenergistics.model.ModelMaterializationScanner;
import com.xavier0014.lyokoenergistics.reference.Reference;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RendererModelLE extends TileEntitySpecialRenderer{
	
	public static ModelLE model;
	public static ResourceLocation texture;
	
	public RendererModelLE(ResourceLocation texture,ModelLE model) {
		this.model = model;
		this.texture = texture;
		this.func_147497_a(TileEntityRendererDispatcher.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity entity, double x,double y, double z, float partialRenderTick) {
		this.renderTileMaterializationScannerAt((TileMaterializationScanner)entity, x, y, z, partialRenderTick);
	}
	
	private void renderTileMaterializationScannerAt(TileMaterializationScanner entity, double x, double y, double z, float partialRenderTick) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		GL11.glRotated(180D, 0.0D, 0.0D, 1.0D);
		GL11.glRotated(90D * entity.getDirection()+180, 0.0D, 1.0D, 0.0D);
		this.bindTexture(texture);
		this.model.render();
		GL11.glPopMatrix();
	}

}
