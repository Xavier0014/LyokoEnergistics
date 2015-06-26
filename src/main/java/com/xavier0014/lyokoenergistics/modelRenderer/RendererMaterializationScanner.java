package com.xavier0014.lyokoenergistics.modelRenderer;

import org.lwjgl.opengl.GL11;

import com.xavier0014.lyokoenergistics.model.ModelMaterializationScanner;
import com.xavier0014.lyokoenergistics.reference.Reference;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RendererMaterializationScanner extends RendererModelLE{

	public RendererMaterializationScanner(){
		super(new ResourceLocation(Reference.MOD_id, "models/blocks/materializationscanner.png"), new ModelMaterializationScanner());
	}
	
}
