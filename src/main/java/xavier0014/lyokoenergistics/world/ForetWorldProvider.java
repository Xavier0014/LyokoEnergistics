package xavier0014.lyokoenergistics.world;

import scala.reflect.internal.Trees.This;
import xavier0014.lyokoenergistics.init.ModBiome;
import xavier0014.lyokoenergistics.init.ModDimension;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldType;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class ForetWorldProvider extends WorldProvider{
	
	public ForetWorldProvider() {
		this.setSkyRenderer(new SkyRender());
		this.setCloudRenderer(new NullRender());
		
	}
	
	public void registerWorldChunkManager(){
		this.worldChunkMgr = new WorldChunkManagerHell(ModBiome.biomeLyokoForest, 1.2F);
		this.dimensionId = ModDimension.dimentionID;
		this.terrainType = WorldType.FLAT;
	}
	
	public IChunkProvider createChunkGeneration() {
		return new ForetChunkProvider(this.worldObj);
	}
	
	 @SideOnly(Side.CLIENT)
	    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_){
	        return Vec3.createVectorHelper(254, 251, 208);
	    }
	
//	 @SideOnly(Side.CLIENT)
//     public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_){
//	        return null;
//	 }
	 
	@Override
	public boolean canRespawnHere(){
		return false;
	}
	
	@Override
	public String getDimensionName() {
		return "LyokoForet";
	}
	
	public int getAverageGroundLevel(){
	return 50;
	}
	
	 @Override
	 public ChunkCoordinates getSpawnPoint(){
		return new ChunkCoordinates(0, 60, 0);
	 }
	 
	 public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_){
		return 0;
	    }
	 
	 public boolean isSurfaceWorld(){
	        return true;
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
