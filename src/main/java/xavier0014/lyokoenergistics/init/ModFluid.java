package xavier0014.lyokoenergistics.init;

import xavier0014.lyokoenergistics.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluid {
	
	public static Fluid DigitalSea;
	
	public static void initFluid() {
		//TexturePrefab stillIcon = new TexturePrefab("lyokoenergistics:digitalSeaStill",new ResourceLocation(Reference.MOD_id + ":" + "textures/blocks/digitalSeaStill") );
		//TexturePrefab flowingIcon = new TexturePrefab("lyokoenergistics:digitalSeaFlow",new ResourceLocation(Reference.MOD_id + ":" + "textures/blocks/digitalSeaFlow") );
		DigitalSea = new Fluid("digitalsea").setDensity(4000).setViscosity(500).setTemperature(286).setLuminosity(10).setUnlocalizedName("digitalSea");
		FluidRegistry.registerFluid(DigitalSea);
		DigitalSea = FluidRegistry.getFluid("digitalsea");
	}
}
