package xavier0014.lyokoenergistics.init;

import xavier0014.lyokoenergistics.world.ForetWorldProvider;
import net.minecraftforge.common.DimensionManager;

public class ModDimension {
	
	public static int dimentionID = 34;
	
	public static void initDimension(){
		DimensionManager.registerProviderType(dimentionID, ForetWorldProvider.class, false);
		DimensionManager.registerDimension(dimentionID, dimentionID);
	}
	
}
