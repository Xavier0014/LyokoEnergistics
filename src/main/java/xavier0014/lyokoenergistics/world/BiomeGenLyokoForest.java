package xavier0014.lyokoenergistics.world;


import xavier0014.lyokoenergistics.init.ModBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenLyokoForest extends BiomeGenBase {

	public BiomeGenLyokoForest(int id) {
		super(id);
		this.setDisableRain();
		this.topBlock = Blocks.air;
		this.fillerBlock = Blocks.air;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		
		this.theBiomeDecorator.treesPerChunk = 0;
		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.grassPerChunk = 0;
	}

}
