package xavier0014.lyokoenergistics.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xavier0014.lyokoenergistics.tileentity.TileBlockModelTest;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

public class BlockModelTest extends BlockModelLE{

	public BlockModelTest(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata){
		return new TileBlockModelTest();
	}

}
