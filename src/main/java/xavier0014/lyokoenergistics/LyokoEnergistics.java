package xavier0014.lyokoenergistics;

import java.util.ArrayList;

import sun.util.logging.resources.logging;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import xavier0014.lyokoenergistics.handler.GuiHandler;
import xavier0014.lyokoenergistics.creativetabs.LECreativeTabs;
import xavier0014.lyokoenergistics.init.*;
import xavier0014.lyokoenergistics.packet.PacketMaterializationScanner;
import xavier0014.lyokoenergistics.packet.PacketSuperComputer;
import xavier0014.lyokoenergistics.packet.PacketTowerConsol;
import xavier0014.lyokoenergistics.proxy.IProxy;
import xavier0014.lyokoenergistics.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;



@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = "required-after:appliedenergistics2")
public class LyokoEnergistics {
	
	@Mod.Instance(Reference.MOD_ID)
	public static LyokoEnergistics instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PRO_CLASS, serverSide = Reference.SERVER_PRO_CLASS)
	public static IProxy proxy;
	
	public static CreativeTabs lyokotab = new LECreativeTabs("lyokoenergistics_tabs");
	
	public static SimpleNetworkWrapper network;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent envent){
		ModItem.init();
		ModBlock.init();
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		network.registerMessage(PacketMaterializationScanner.Handler.class, PacketMaterializationScanner.class, 0, Side.SERVER);
		network.registerMessage(PacketSuperComputer.Handler.class, PacketSuperComputer.class, 1, Side.SERVER);
		network.registerMessage(PacketTowerConsol.Handler.class, PacketTowerConsol.class, 2, Side.SERVER);
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}
	
	@Mod.EventHandler
	public void Init(FMLInitializationEvent envent){
		ModTileEntity.tileinit();
		ModCraftingRecipe.initCrafting();
		proxy.registerRender();
		
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent envent){
		
	}

}