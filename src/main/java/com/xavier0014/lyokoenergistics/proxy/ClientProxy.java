package com.xavier0014.lyokoenergistics.proxy;

import com.xavier0014.lyokoenergistics.model.ModelMaterializationScanner;
import com.xavier0014.lyokoenergistics.modelRenderer.RendererMaterializationScanner;
import com.xavier0014.lyokoenergistics.modelRenderer.TESRInventoryRenderer;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	public static int tesrRenderId;

	@Override
	public void registerRender() {
		tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileMaterializationScanner.class, new RendererMaterializationScanner());
	}

}
