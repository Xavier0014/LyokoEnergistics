package xavier0014.lyokoenergistics.init;

import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import xavier0014.lyokoenergistics.items.*;
import xavier0014.lyokoenergistics.items.record.*;
import xavier0014.lyokoenergistics.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItem {
	
	public static final ItemLE matteringot = new ItemLE("matteringot");
	public static final ItemLE quantumprocessor = new ItemLE("quantumprocessor");
	
	public static final ItemLE digitalsabre = (ItemLE) new DigitalSabre("digitalsabre");
	public static final ItemLE laserarrows = (ItemLE) new LaserArrows("laserarrows");
	public static final ItemLE energyfields = (ItemLE) new EnergyFields("energyfields");
	public static final ItemLE fan = (ItemLE) new Fan("fan");
	
	public static final ItemLE basiccore = (ItemLE) new ItemLE("basiccore").setMaxStackSize(1);
	public static final ItemLE hardenedcore = (ItemLE) new ItemLE("hardenedcore").setMaxStackSize(1);
	public static final ItemLE reinforcedcore = (ItemLE) new ItemLE("reinforcedcore").setMaxStackSize(1);
	public static final ItemLE resonantcore = (ItemLE) new ItemLE("resonantcore").setMaxStackSize(1);
	
	public static final ItemLE basicspeed = (ItemLE) new ItemDescriptionLE("basicspeed", "speed 2.5x").setMaxStackSize(1);
	public static final ItemLE hardenedspeed = (ItemLE) new ItemDescriptionLE("hardenedspeed", "speed 10x").setMaxStackSize(1);
	public static final ItemLE reinforcedspeed = (ItemLE) new ItemDescriptionLE("reinforcedspeed", "speed 100x").setMaxStackSize(1);
	public static final ItemLE resonantspeed = (ItemLE) new ItemDescriptionLE("resonantspeed", "speed 400x").setMaxStackSize(1);
	
	public static final ItemLE basicstorage = (ItemLE) new ItemDescriptionLE("basicstorage", "energy 4x").setMaxStackSize(1);
	public static final ItemLE hardenedstorage = (ItemLE) new ItemDescriptionLE("hardenedstorage", "energy 16x").setMaxStackSize(1);
	public static final ItemLE reinforcedstorage = (ItemLE) new ItemDescriptionLE("reinforcedstorage", "energy 128x").setMaxStackSize(1);
	public static final ItemLE resonantstorage = (ItemLE) new ItemDescriptionLE("resonantstorage", "energy 512x").setMaxStackSize(1);
	
	public static final ItemLE basicefficacite = (ItemLE) new ItemDescriptionLE("basicefficacite", "").setMaxStackSize(1);
	public static final ItemLE hardenedefficacite = (ItemLE) new ItemDescriptionLE("hardenedefficacite", "").setMaxStackSize(1);
	public static final ItemLE reinforcedefficacite = (ItemLE) new ItemDescriptionLE("reinforcedefficacite", "").setMaxStackSize(1);
	public static final ItemLE resonantefficacite = (ItemLE) new ItemDescriptionLE("resonantefficacite", "").setMaxStackSize(1);
	
	public static final ItemLE virtualEye = new VirtualEye("virtualEye");
	
	public static final RecordLE UnMondeSansDanger = new RecordLE("unmondesansdanger","Code Lyoko - Un monde sans danger");
	public static final RecordLE senvoler = new RecordLE("senvoler","Code Lyoko - S'envoler");
	
	
	public static void init(){
		
		GameRegistry.registerItem(matteringot, "matteringot");
		GameRegistry.registerItem(UnMondeSansDanger, "UnMondeSansDanger");
		GameRegistry.registerItem(senvoler, "senvoler");
		GameRegistry.registerItem(quantumprocessor, "quantumprocessor");
		
		GameRegistry.registerItem(digitalsabre, "digitalsabre");
		GameRegistry.registerItem(laserarrows, "laserarrows");
		GameRegistry.registerItem(energyfields, "energyfields");
		GameRegistry.registerItem(fan, "fan");
		
		GameRegistry.registerItem(basiccore, "basiccore");
		GameRegistry.registerItem(hardenedcore, "hardenedcore");
		GameRegistry.registerItem(reinforcedcore, "reinforcedcore");
		GameRegistry.registerItem(resonantcore, "resonantcore");
		
		GameRegistry.registerItem(basicspeed, "basicspeed");
		GameRegistry.registerItem(hardenedspeed, "hardenedspeed");
		GameRegistry.registerItem(reinforcedspeed, "reinforcedspeed");
		GameRegistry.registerItem(resonantspeed, "resonantspeed");
		
		GameRegistry.registerItem(basicstorage, "basicstorage");
		GameRegistry.registerItem(hardenedstorage, "hardenedstorage");
		GameRegistry.registerItem(reinforcedstorage, "reinforcedstorage");
		GameRegistry.registerItem(resonantstorage, "resonantstorage");
		
		GameRegistry.registerItem(basicefficacite, "basicefficacite");
		GameRegistry.registerItem(hardenedefficacite, "hardenedefficacite");
		GameRegistry.registerItem(reinforcedefficacite, "reinforcedefficacite");
		GameRegistry.registerItem(resonantefficacite, "resonantefficacite");
		
		//GameRegistry.registerItem(virtualEye, "virtualEye");
	}

}
