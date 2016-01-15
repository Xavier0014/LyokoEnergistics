package xavier0014.lyokoenergistics.init;

import xavier0014.lyokoenergistics.world.BiomeGenLyokoForest;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class ModBiome {
	
	public static BiomeGenBase biomeLyokoForest = new BiomeGenLyokoForest(34);
	
	public static void initBiome() {
		BiomeDictionary.registerBiomeType(biomeLyokoForest, Type.FOREST);
	}
	
}
