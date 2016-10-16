package xavier0014.lyokoenergistics.world;


import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenLyokoForest extends BiomeGenBase {

	public BiomeGenLyokoForest(int id) {
		super(id);
		this.setDisableRain();
		this.setBiomeName("Lyoko Forest");
		this.setColor(353825);
		this.setHeight(height_LowPlains);
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float p_76731_1_){
		return 0xfefbd0;
	}
}
