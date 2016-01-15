package xavier0014.lyokoenergistics.world;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class ForetChunkProvider extends ChunkProviderGenerate{
	
	World worldObj;
	
	public ForetChunkProvider(World world) {
		super(world, 0, false);
		this.worldObj = world;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		Chunk chunk = new Chunk(this.worldObj, x, z);
		chunk.resetRelightChecks();

		return chunk;
	}

	@Override
	public void populate(IChunkProvider p_73153_1_, int p_73153_2_,int p_73153_3_) {
	}

	@Override
	public boolean unloadQueuedChunks() {
		return true;
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType p_73155_1_,int p_73155_2_, int p_73155_3_, int p_73155_4_) {
		return null;
	}

}
