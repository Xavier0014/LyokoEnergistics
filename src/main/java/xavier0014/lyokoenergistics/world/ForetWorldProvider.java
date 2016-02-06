package xavier0014.lyokoenergistics.world;

import scala.reflect.internal.Trees.This;
import xavier0014.lyokoenergistics.init.ModBiome;
import xavier0014.lyokoenergistics.init.ModDimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class ForetWorldProvider extends WorldProvider{
	
	public void registerWorldChunkManager(){
		this.worldChunkMgr = new WorldChunkManagerHell(ModBiome.biomeLyokoForest, 1.2F);
		this.dimensionId = ModDimension.dimentionID;
		this.terrainType = WorldType.FLAT;
	}
	
	public IChunkProvider createChunkGeneration() {
		return new ForetChunkProvider(this.worldObj);
	}
	
	@Override
	public boolean canRespawnHere(){
		return false;
	}
	
	@Override
	public String getDimensionName() {
		return "LyokoForet";
	}
	
	public int getAverageGroundLevel(){
	return 1;
	}
	
	 public boolean isSurfaceWorld(){
	      return false;
	 }
	 
	 @Override
		public boolean isDaytime(){
			return true;
	}
	 
	 @Override
	 public ChunkCoordinates getSpawnPoint(){
		return new ChunkCoordinates(0, 60, 0);
	 }
	 
	 @Override
	 public ChunkCoordinates getRandomizedSpawnPoint(){
	        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(this.worldObj.getSpawnPoint());
	        return chunkcoordinates;
	    }
	 @Override
	 public ChunkCoordinates getEntrancePortalLocation() {
	        return new ChunkCoordinates(0, 60, 0);
	    }
}
