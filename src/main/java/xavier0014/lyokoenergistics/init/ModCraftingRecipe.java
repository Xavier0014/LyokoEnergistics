package xavier0014.lyokoenergistics.init;

import appeng.api.AEApi;
import appeng.api.definitions.Materials;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModCraftingRecipe {
	
	static Materials ae2_materials = AEApi.instance().materials();
	
	static ItemStack fluix = new ItemStack(ae2_materials.materialFluixCrystal.item(),1,7);
	static ItemStack Processor = new ItemStack(ae2_materials.materialEngProcessor.item(),1,24);
	static ItemStack diament = new ItemStack(Items.diamond);
	
	public static void initCrafting(){
		//quantum processor
		GameRegistry.addRecipe(new ItemStack(ModItem.quantumprocessor), "xyx","yzy","xyx",'x',diament ,'y',fluix,'z',Processor);
	}
}
