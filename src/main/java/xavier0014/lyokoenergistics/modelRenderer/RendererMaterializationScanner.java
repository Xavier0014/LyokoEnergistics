package xavier0014.lyokoenergistics.modelRenderer;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import xavier0014.lyokoenergistics.model.ModelMaterializationScanner;
import xavier0014.lyokoenergistics.reference.Reference;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RendererMaterializationScanner extends RendererModelLE{

	public RendererMaterializationScanner(){
		super(new ResourceLocation(Reference.MOD_id, "models/blocks/materializationscanner.png"), new ModelMaterializationScanner());
	}
	
	
	
}
