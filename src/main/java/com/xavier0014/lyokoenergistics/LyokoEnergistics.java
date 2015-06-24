package com.xavier0014.lyokoenergistics;

import net.minecraft.creativetab.CreativeTabs;

import com.xavier0014.lyokoenergistics.creativetabs.LECreativeTabs;
import com.xavier0014.lyokoenergistics.init.*;
import com.xavier0014.lyokoenergistics.proxy.IProxy;
import com.xavier0014.lyokoenergistics.reference.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class LyokoEnergistics {
	
	@Mod.Instance(Reference.MOD_ID)
	public static LyokoEnergistics instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PRO_CLASS, serverSide = Reference.SERVER_PRO_CLASS)
	public static IProxy proxy;
	
	public static CreativeTabs lyokotab = new LECreativeTabs("lyokoenergistics_tabs");
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent envent){
		ModItem.init();
		ModBlock.init();
	}
	
	@Mod.EventHandler
	public void Init(FMLInitializationEvent envent){
		
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent envent){
		
	}

}