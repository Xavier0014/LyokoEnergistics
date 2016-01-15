package xavier0014.lyokoenergistics.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xavier0014.lyokoenergistics.tileentity.TileLyokoForetMaster;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

public class LyokoForetMaster extends BlockLE{

	public LyokoForetMaster(String name) {
		super(name);
	}
	
	 @Override
	 public boolean hasTileEntity(int metadata){
	     return true;
	 }
	 
	@Override
	public TileEntity createTileEntity(World world, int metadata){
		return new TileLyokoForetMaster();
	}

}
