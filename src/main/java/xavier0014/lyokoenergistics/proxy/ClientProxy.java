package xavier0014.lyokoenergistics.proxy;


import xavier0014.lyokoenergistics.entity.EntityLaserArrows;
import xavier0014.lyokoenergistics.model.ModelLaserArrows;
import xavier0014.lyokoenergistics.model.ModelMaterializationScanner;
import xavier0014.lyokoenergistics.modelRenderer.RenderLaserArrows;
import xavier0014.lyokoenergistics.modelRenderer.RenderTowerScreen;
import xavier0014.lyokoenergistics.modelRenderer.RenderVirtualisationScanner;
import xavier0014.lyokoenergistics.modelRenderer.RendererMaterializationScanner;
import xavier0014.lyokoenergistics.modelRenderer.TESRInventoryRenderer;
import xavier0014.lyokoenergistics.tileentity.TileBlockModelTest;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import xavier0014.lyokoenergistics.tileentity.TileVirtualisationScanner;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	public static int tesrRenderId;

	@Override
	public void registerRender() {
		tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileBlockModelTest.class, new RenderTowerScreen());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMaterializationScanner.class, new RendererMaterializationScanner());
		ClientRegistry.bindTileEntitySpecialRenderer(TileVirtualisationScanner.class, new RenderVirtualisationScanner());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserArrows.class, new RenderLaserArrows());
	}
}
