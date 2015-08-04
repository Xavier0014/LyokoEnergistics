package com.xavier0014.lyokoenergistics.modelRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.xavier0014.lyokoenergistics.entity.EntityLaserArrows;
import com.xavier0014.lyokoenergistics.model.ModelLaserArrows;
import com.xavier0014.lyokoenergistics.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderLaserArrows extends Render
{
    private static final ResourceLocation Textures = new ResourceLocation(Reference.MOD_id + "models/entity/LaserArrows.png");
    private static final String __OBFID = "CL_00000984";
    
	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_) {
		
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}
}