package xavier0014.lyokoenergistics.world;

import xavier0014.lyokoenergistics.init.ModBlock;
import xavier0014.lyokoenergistics.init.ModDimension;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterLyokoForet extends Teleporter {
	
	private final WorldServer worldServerInstance;
	
	public TeleporterLyokoForet(WorldServer server) {
		super(server);
		worldServerInstance = server;
	}
	
	public void placeInPortal(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_){
        if (this.worldServerInstance.provider.dimensionId != ModDimension.dimentionID) {
            if (!this.placeInExistingPortal(p_77185_1_, p_77185_2_, p_77185_4_, p_77185_6_, p_77185_8_)){
                this.makePortal(p_77185_1_);
                this.placeInExistingPortal(p_77185_1_, p_77185_2_, p_77185_4_, p_77185_6_, p_77185_8_);
            }
        }
        else{
            int i = MathHelper.floor_double(p_77185_1_.posX);
            int j = MathHelper.floor_double(p_77185_1_.posY) - 1;
            int k = MathHelper.floor_double(p_77185_1_.posZ);
            byte b0 = 1;
            byte b1 = 0;

//            for (int l = -2; l <= 2; ++l){
//                for (int i1 = -2; i1 <= 2; ++i1) {
//                    for (int j1 = -1; j1 < 0; ++j1){
//                        int k1 = i + i1 * b0 + l * b1;
//                        int l1 = j + j1;
//                        int i2 = k + i1 * b1 - l * b0;
//                        boolean flag = j1 < 0;
//                        this.worldServerInstance.setBlock(k1, l1, i2, ModBlock.lyokoGrass);
//                    }
//                }
//            }

            p_77185_1_.setLocationAndAngles((double)i, (double)j, (double)k, p_77185_1_.rotationYaw, 0.0F);
            p_77185_1_.motionX = p_77185_1_.motionY = p_77185_1_.motionZ = 0.0D;
        }
    }

}
